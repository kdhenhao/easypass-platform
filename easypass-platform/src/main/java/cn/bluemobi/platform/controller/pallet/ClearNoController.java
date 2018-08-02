/**
 * Project Name:lmExpress-platform
 * File Name:ClearNoController.java
 * Package Name:cn.bluemobi.platform.controller.pallet
 * Date:2016年11月24日下午2:07:57
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.pallet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bluemobi.common.core.dto.ListDto;
import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.utils.DateUtils;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.service.ClearNoService;

/**
 * Description: <br/>
 * Date: 2016年11月24日 下午2:07:57 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/clearNo")
public class ClearNoController extends PlatformBaseController {

    @Autowired
    private ClearNoService clearNoService;

    @Autowired
    private EhCacheCacheManager cacheManager;

    @Autowired
    @Qualifier("executor")
    private TaskExecutor taskExecutor;

    @RequestMapping("/toListPage")
    public String toListPage() {
        return "pallet/clearNoList";
    }

    @ResponseBody
    @RequestMapping("/findClearNos")
    public DatatablePage findClearNos(@RequestParam("draw") int draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length, @RequestParam("orderNo") String orderNo,
            @RequestParam("clearNo") String clearNo, @RequestParam("status") String status) {
        return new DatatablePage(draw, clearNoService.findClearNos(start, length, orderNo, clearNo, status));
    }

    @RequestMapping("/toImportPage")
    public String toImportPage() {
        return "pallet/clearNoImport";
    }

    @RequestMapping("/importExcel")
    @ResponseBody
    public MapDto importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        MapDto dto = new MapDto();
        if (file == null || file.isEmpty()) {
            dto.errorMsg("文件不能为空");
            return dto;
        }
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(file.getInputStream());
        } catch (Exception e) {
            dto.errorMsg("excel文件无法读取，请检查文件是否正常");
            return dto;
        }
        Long taskId = System.currentTimeMillis();
        // 启动一个线程后台处理
        Cache cache = cacheManager.getCache("commonCache");
        taskExecutor.execute(new ImportTask(taskId, clearNoService, wb, cache));
        dto.putInData("taskId", taskId);
        return dto;
    }

    @RequestMapping("/findStatus")
    @ResponseBody
    public MapDto findStatus(@RequestParam("taskId") Long taskId) {
        MapDto dto = new MapDto();
        Cache cache = cacheManager.getCache("commonCache");
        ValueWrapper vw = cache.get(taskId + "count");
        if (vw != null) {
            dto.putInData("count", (Integer) vw.get());
        }
        vw = cache.get(taskId + "flag");
        if (vw != null) {
            dto.putInData("flag", (String) vw.get());
        }
        return dto;
    }

    @RequestMapping("/findImportResult")
    @ResponseBody
    public ListDto<Map<String, Object>> findImportResult(@RequestParam("taskId") Long taskId) {
        ListDto<Map<String, Object>> dto = new ListDto<Map<String, Object>>();
        Cache cache = cacheManager.getCache("commonCache");
        ValueWrapper vw = cache.get(taskId + "result");
        if (vw != null) {
            dto.setList((List<Map<String, Object>>) vw.get());
        }
        cache.evict(taskId + "result");
        return dto;
    }

    private static class ImportTask implements Runnable {
        private Long taskId;

        private ClearNoService clearNoService;

        private HSSFWorkbook wb;

        private Cache cache;

        public ImportTask(Long taskId, ClearNoService clearNoService, HSSFWorkbook wb, Cache cache) {
            super();
            this.taskId = taskId;
            this.clearNoService = clearNoService;
            this.wb = wb;
            this.cache = cache;
        }

        @Override
        public void run() {
            HSSFSheet sheet = wb.getSheetAt(0);
            int totalRows = sheet.getLastRowNum() + 1;// 最后一条记录行数
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();// 处理结果
            for (int i = 1; i < totalRows; i++) {
                // 读excel数据
                HSSFRow row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                if (i % 10 == 0) {
                    // 将处理进度放入缓存
                    cache.put(taskId + "count", i);
                }
                String clearNo = getValue(row.getCell(0));
                String clearCompany = getValue(row.getCell(1));
                String custom = getValue(row.getCell(2));
                String time = getValue(row.getCell(3));
                Map<String, Object> map = new HashMap<String, Object>();
                if (StringUtils.isEmpty(clearNo)) {
                    map.put("success", 1);
                    map.put("msg", "分运单号为空");
                    result.add(map);
                    continue;
                }
                map.put("clearNo", clearNo);
                map.put("clearCompany", clearCompany);
                map.put("custom", custom);
                map.put("time", time);
                result.add(clearNoService.importClearNo(map));
            }
            // 处理完将数据放到缓存
            cache.put(taskId + "flag", "true");
            cache.put(taskId + "result", result);
        }

        /**
         * 获取excel cell值
         */
        private String getValue(HSSFCell cell) {
            if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 用于转化为日期格式
                    Date d = cell.getDateCellValue();
                    return DateUtils.format(d, "yyyy-MM-dd HH:mm:ss");
                } else {
                    // 用于格式化数字，只保留数字的整数部分
                    DecimalFormat df = new DecimalFormat("########");
                    return df.format(cell.getNumericCellValue());
                }
            } else {
                return cell.getStringCellValue();
            }
        }
    }
}
