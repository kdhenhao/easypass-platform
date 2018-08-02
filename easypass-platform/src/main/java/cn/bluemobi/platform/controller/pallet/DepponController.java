/**
 * Project Name:lmExpress-platform
 * File Name:DepponController.java
 * Package Name:cn.bluemobi.platform.controller.pallet
 * Date:2016年11月23日上午9:54:06
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bluemobi.common.core.dto.ListDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.entity.deppon.DepponRequest;
import cn.bluemobi.platform.entity.deppon.DepponResponse;
import cn.bluemobi.platform.service.ImportService;
import cn.bluemobi.platform.service.OrderService;

/**
 * Description: <br/>
 * Date: 2016年11月23日 上午9:54:06 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/deppon")
public class DepponController extends PlatformBaseController {

    @Autowired
    private ImportService importService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/toImportPage")
    public String toImportPage() {
        return "pallet/depponImport";
    }

    @RequestMapping("/importExcel")
    @ResponseBody
    public ListDto<Map<String, Object>> uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
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
        int totalRows = sheet.getLastRowNum() + 1;// 最后一条记录行数
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();// 处理结果
        dto.setList(result);
        for (int i = 1; i < totalRows; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            String orderStatus = getValue(row.getCell(16));
            String vasNo = getValue(row.getCell(3));
            String vasDate = getValue(row.getCell(15));
            String orderNo = getValue(row.getCell(2));
            Map<String, Object> map = new HashMap<String, Object>();
            if (StringUtils.isEmpty(orderNo)) {
                map.put("success", 1);
                map.put("msg", "龙门运单号为空");
                result.add(map);
                continue;
            }
            if (StringUtils.isEmpty(vasNo)) {
                map.put("success", 1);
                map.put("msg", "德邦快递号为空");
                result.add(map);
                continue;
            }
            map.put("orderStatus", orderStatus);
            map.put("vasNo", vasNo);
            map.put("vasDate", vasDate);
            map.put("orderNo", orderNo);
            result.add(importService.importDeppon(map));
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

    /**
     * 德邦订单异步通知
     */
    @RequestMapping("/notifyUrl")
    @ResponseBody
    public DepponResponse notifyUrl(@RequestBody DepponRequest request) {
        DepponResponse response = new DepponResponse();
        try {
            orderService.receiveDepponNotify(request, response);
        } catch (Exception e) {
            response.setReason(e.getMessage());
            response.setResult("fail");
            response.setResultCode("9999");
        }
        return response;
    }
}
