/**
 * Project Name:lmExpress-platform
 * File Name:YuantongController.java
 * Package Name:cn.bluemobi.platform.controller.pallet
 * Date:2016年11月23日下午4:51:43
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.pallet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bluemobi.common.core.dto.ListDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.service.ImportService;

/**
 * Description: <br/>
 * Date: 2016年11月23日 下午4:51:43 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@RequestMapping("/admin/yuantong")
@Controller
public class YuantongController extends PlatformBaseController {

    @Autowired
    private ImportService importService;

    @RequestMapping("/toImportPage")
    public String toImportPage() {
        return "pallet/yuantongImport";
    }

    @RequestMapping("/importExcel")
    @ResponseBody
    public ListDto<Map<String, Object>> importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        ListDto<Map<String, Object>> dto = new ListDto<Map<String, Object>>();
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
        HSSFSheet sheet = wb.getSheetAt(0);
        int totalRows = sheet.getLastRowNum() + 1;// 总记录数
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();// 处理结果
        dto.setList(result);
        for (int i = 2; i < totalRows; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            String vasNo = getValue(row.getCell(6));
            String orderNo = getValue(row.getCell(5));
            Map<String, Object> map = new HashMap<String, Object>();
            if (StringUtils.isEmpty(orderNo)) {
                map.put("success", 1);
                map.put("msg", "龙门运单号为空");
                result.add(map);
                continue;
            }
            if (StringUtils.isEmpty(vasNo)) {
                map.put("success", 1);
                map.put("msg", "圆通快递号为空");
                result.add(map);
                continue;
            }
            map.put("vasNo", vasNo);
            map.put("orderNo", orderNo);
            result.add(importService.importYuantong(map));
        }
        return dto;
    }

    /**
     * 获取excel cell值
     */
    private String getValue(HSSFCell cell) {
        if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return cell.getStringCellValue();
        }
    }
}
