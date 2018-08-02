/**
 * Project Name:lmExpress-platform
 * File Name:PalletManageController.java
 * Package Name:cn.bluemobi.platform.controller.pallet
 * Date:2016年11月11日下午2:53:11
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.pallet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.core.utils.PlatformConstants;
import cn.bluemobi.platform.entity.order.OrderMain;
import cn.bluemobi.platform.service.FlightService;
import cn.bluemobi.platform.service.PalletService;
import cn.bluemobi.platform.utils.CounterConstants;
import cn.bluemobi.platform.utils.ImageUtils;

/**
 * Description: <br/>
 * Date: 2016年11月11日 下午2:53:11 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@RequestMapping("/admin/palletManage")
@Controller
public class PalletManageController extends PlatformBaseController {

    @Autowired
    private PalletService palletService;

    @Autowired
    private FlightService flightService;

    // @Autowired
    // private DictService dictService;

    @RequestMapping("/toPalletManage")
    public String toPalletManage() {
        return "pallet/palletList";
    }

    @ResponseBody
    @RequestMapping("/findPallets")
    public DatatablePage findPallets(@RequestParam("draw") int draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length, @RequestParam("flightNo") String flightNo,
            @RequestParam("palletName") String palletName, @RequestParam("status") String status) {
        return new DatatablePage(draw, palletService.findPallets(start, length, flightNo, palletName, status));
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public MapDto updateStatus(@RequestParam("id") Long id, @RequestParam("palletStatus") String status) {
        return palletService.updateStatus(id, status, getUser());
    }

    @RequestMapping("/findFlights")
    @ResponseBody
    public Map<String, Object> findFlights(@RequestParam("q") String q) {
        return flightService.findForSelect2(q);
    }

    @RequestMapping("/updateFlight")
    @ResponseBody
    public MapDto updateFlight(@RequestParam("id") Long id, @RequestParam("flightId") String flightId) {
        return palletService.updateFlight(id, flightId, getUser());
    }

    @RequestMapping("/toDetailPage")
    public String toDetailPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("orders", palletService.findOrdersByPallet(id));
        model.addAttribute("clearNoSum", palletService.findClearNoSum());
        return "pallet/palletDetail";
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public MapDto deleteById(@RequestParam("id") Long id) {
        return palletService.deleteById(id);
    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public MapDto addOrder(@RequestParam("orderNo") String orderNo, @RequestParam("id") Long id) {
        return palletService.addOrder(orderNo, id, getUser());
    }

    @RequestMapping("/deleteOrder")
    @ResponseBody
    public MapDto deleteOrder(@RequestParam("id") Long id) {
        return palletService.deleteOrder(id, getUser());
    }

    /**
     * 单个替换身份证
     */
    @RequestMapping("/replaceIdCard")
    @ResponseBody
    public MapDto replaceIdCard(@RequestParam("id") Long orderId) {
        return palletService.replaceIdCard(orderId, getUser());
    }

    /**
     * 一键替换身份证
     */
    @RequestMapping("/replaceAll")
    @ResponseBody
    public MapDto replaceAll(@RequestParam("id") Long palletId) {
        return palletService.replaceAll(palletId);
    }

    /**
     * 一键短信提醒
     */
    @RequestMapping("/sendSmsAll")
    @ResponseBody
    public MapDto sendSmsAll(@RequestParam("id") Long palletId) {
        return palletService.sendSmsAll(palletId);
    }

    /**
     * 一件分配清关单号
     */
    @RequestMapping("/allocateClearNo")
    @ResponseBody
    public MapDto allocateClearNo(@RequestParam("id") Long palletId) {
        return palletService.allocateClearNo(palletId);
    }

    /**
     * 检查是否都分配了清关单号
     */

    @RequestMapping("/checkClearNo")
    @ResponseBody
    public MapDto checkClearNo(@RequestParam("id") Long palletId) {
        return palletService.checkClearNo(palletId);
    }

    /**
     * 下载身份证压缩包
     */
    @RequestMapping("/downLoadIds")
    public void downLoadIds(@RequestParam("id") Long palletId, HttpServletResponse response) throws IOException {
        String palletName = palletService.findPalletName(palletId);
        List<OrderMain> oms = palletService.findOrderPicByPalletId(palletId);
        File zipFile = generateIdZIP(palletName, oms);
        // 准备流
        response.setCharacterEncoding("UTF-8");
        String fname = URLEncoder.encode(palletName + "Ids.zip", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fname);
        response.setContentType("application/zip");// 定义输出类型
        response.setContentLength((int) zipFile.length());
        // 写
        OutputStream out = response.getOutputStream();
        InputStream input = new FileInputStream(zipFile);
        int temp = 0;
        byte[] buf = new byte[1024];
        while ((temp = input.read(buf)) != -1) {
            out.write(buf, 0, temp);
        }
        input.close();
        out.flush();
        out.close();
    }

    /**
     * 将身份证图片重命名为订单号，并复制到临时目录下
     */
    private void copyIdToDir(File folder, String path, String orderNo, int i) throws IOException {
        String fileType = path.substring(path.lastIndexOf(".") + 1);
        String newFileName = i + "_" + orderNo + "." + fileType;
        FileUtils.copyFile(new File(PlatformConstants.IMG_SECURITY_PATH + File.separator + path),
                new File(folder, newFileName));
    }

    private File generateIdZIP(String palletName, List<OrderMain> oms) throws IOException {
        // 在临时目录下创建目录 托盘号Ids
        File folder = new File(System.getProperty("java.io.tmpdir") + File.separator + palletName + "Ids");
        if (folder.exists()) {
            FileUtils.cleanDirectory(folder);
        } else {
            folder.mkdirs();
        }
        // 循环将身份证图片移到目录下
        for (int i = 0; i < oms.size(); i++) {
            OrderMain om = oms.get(i);
            if (StringUtils.equals("0", om.getHasPic())) {
                continue;
            } else if (StringUtils.equals("1", om.getHasPic())) {
                copyIdToDir(folder, om.getMergesidePic(), om.getOrderNo(), i + 1);
            } else if (StringUtils.equals("2", om.getHasPic())) {
                copyIdToDir(folder, om.getFakeMergesidePic(), om.getOrderNo(), i + 1);
            }
        }
        // 将目录 压缩为zip文件 为了下载的时候显示进度，先压缩成文件获取大小后再写出去
        File zipFile = new File(System.getProperty("java.io.tmpdir") + File.separator + palletName + "Ids.zip");
        if (zipFile.exists()) {
            zipFile.delete();
        }
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        // 打包
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            addToZIP(zipOut, files[i]);
        }
        zipOut.close();
        return zipFile;
    }

    /**
     * 下载物品清单
     */
    @RequestMapping("/downLoadItems")
    public void downLoadItems(@RequestParam("id") Long palletId, HttpServletResponse response) throws IOException {
        String palletName = palletService.findPalletName(palletId);
        List<Map<String, Object>> items = palletService.findItemsByPalletId(palletId);
        // 生成excel
        HSSFWorkbook wb = generateItemsXLS(items);
        // 写出
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition",
                "attachment; filename=" + URLEncoder.encode(palletName + "Items.xls", "UTF8"));
        response.setContentType("application/vnd.ms-excel");
        wb.write(response.getOutputStream());
    }

    private HSSFWorkbook generateItemsXLS(List<Map<String, Object>> items) throws IOException {
        InputStream in = PalletManageController.class.getResourceAsStream("/templates/Items.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        HSSFSheet sheet = wb.getSheetAt(0);
        // 设置 列为纯文本
        DataFormat format = wb.createDataFormat();
        CellStyle cs = wb.createCellStyle();
        cs.setDataFormat(format.getFormat("@"));
        sheet.setDefaultColumnStyle(0, cs);
        sheet.setDefaultColumnStyle(3, cs);
        sheet.setDefaultColumnStyle(10, cs);
        // 写数据
        HSSFRow row = null;
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        for (int i = 0; i < items.size(); i++) {
            Map<String, Object> map = items.get(i);
            row = sheet.createRow(i + 1);
            HSSFCell c = null;
            // 第四步，创建单元格，并设置值
            c = row.createCell(0);
            c.setCellValue(nvl(map.get("orderNo")));
            c = row.createCell(1);
            c.setCellValue(nvl(map.get("senderName")));
            c = row.createCell(2);
            c.setCellValue(nvl(map.get("senderAddress")));
            c = row.createCell(3);
            c.setCellValue(nvl(map.get("taxNo")));
            c = row.createCell(4);
            c.setCellValue(nvl(map.get("cnName")));
            c = row.createCell(5);
            c.setCellValue(nvl(map.get("count")));
            c = row.createCell(6);
            c.setCellValue(nvl(map.get("grossWeight")));
            c = row.createCell(7);
            c.setCellValue(nvl(map.get("price")));
            if (StringUtils.equals("1", map.get("hasPic").toString())) {
                c = row.createCell(8);
                c.setCellValue(nvl(map.get("receiverName")));
                c = row.createCell(9);
                c.setCellValue(nvl(map.get("receiverDistrictCode")) + " " + getReceiverAddress(map));
                c = row.createCell(10);
                c.setCellValue(nvl(map.get("idcardNo")));
            } else if (StringUtils.equals("2", map.get("hasPic").toString())) {
                c = row.createCell(8);
                c.setCellValue(nvl(map.get("fakeReceiverName")));
                c = row.createCell(9);
                c.setCellValue(nvl(map.get("receiverFakeDistrictCode")) + " " + getReceiverAddress(map));
                c = row.createCell(10);
                c.setCellValue(nvl(map.get("fakeIdcardNo")));
            }
        }
        return wb;
    }

    private String nvl(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 拼接收件人地址,如果是替换的，取替换人地址
     */
    private String getReceiverAddress(Map<String, Object> map) {
        if (StringUtils.equals("1", map.get("hasPic").toString())) {
            String province = (String) map.get("receiverProvince");
            String city = (String) map.get("receiverCity");
            if (StringUtils.equals(province, city)) {
                return city + (String) map.get("receiverDistrict") + (String) map.get("receiverAddress");
            } else {
                return province + city + (String) map.get("receiverDistrict") + (String) map.get("receiverAddress");
            }
        } else if (StringUtils.equals("2", map.get("hasPic").toString())) {
            String province = (String) map.get("fakeReceiverProvince");
            String city = (String) map.get("fakeReceiverCity");
            if (StringUtils.equals(province, city)) {
                return city + (String) map.get("fakeReceiverDistrict") + (String) map.get("fakeReceiverAddress");
            } else {
                return province + city + (String) map.get("fakeReceiverDistrict")
                        + (String) map.get("fakeReceiverAddress");
            }
        } else {
            // 默认
            String province = (String) map.get("receiverProvince");
            String city = (String) map.get("receiverCity");
            if (StringUtils.equals(province, city)) {
                return city + (String) map.get("receiverDistrict") + (String) map.get("receiverAddress");
            } else {
                return province + city + (String) map.get("receiverDistrict") + (String) map.get("receiverAddress");
            }
        }
    }

    /**
     * 直接获取真实收货人地址
     */
    private String getReceiverAddress2(Map<String, Object> map) {
        String province = (String) map.get("receiverProvince");
        String city = (String) map.get("receiverCity");
        if (StringUtils.equals(province, city)) {
            return city + (String) map.get("receiverDistrict") + (String) map.get("receiverAddress");
        } else {
            return province + city + (String) map.get("receiverDistrict") + (String) map.get("receiverAddress");
        }
    }

    /**
     * 个人声明文件
     */
    @RequestMapping("/downDeclaration")
    public void downDeclaration(@RequestParam("id") Long palletId, HttpServletResponse response) throws IOException {
        String palletName = palletService.findPalletName(palletId);
        List<Map<String, Object>> orders = palletService.findDeclarationByPalletId(palletId);
        File zipFile = generateDeclarationZIP(palletName, orders);
        // 准备流
        response.setCharacterEncoding("UTF-8");
        String fname = URLEncoder.encode(palletName + "Declaration.zip", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fname);
        response.setContentType("application/zip");// 定义输出类型
        response.setContentLength((int) zipFile.length());
        // 写
        OutputStream out = response.getOutputStream();
        InputStream input = new FileInputStream(zipFile);
        int temp = 0;
        byte[] buf = new byte[1024];
        while ((temp = input.read(buf)) != -1) {
            out.write(buf, 0, temp);
        }
        input.close();
        out.flush();
        out.close();
    }

    /**
     * 生成声明文件zip
     */
    private File generateDeclarationZIP(String palletName, List<Map<String, Object>> orders) throws IOException {
        // 在临时目录下创建目录 托盘号Declaration
        File folder = new File(System.getProperty("java.io.tmpdir") + File.separator + palletName + "Declaration");
        if (folder.exists()) {
            FileUtils.cleanDirectory(folder);
        } else {
            folder.mkdirs();
        }
        // 循环将声明图片移到目录下
        for (int i = 0; i < orders.size(); i++) {
            Map<String, Object> om = orders.get(i);
            if (StringUtils.equals("0", (String) om.get("hasPic"))) {
                continue;
            } else if (StringUtils.equals("1", (String) om.get("hasPic"))) {
                String orderNo = (String) om.get("orderNo");
                String name = (String) om.get("receiverName");
                String idcard = (String) om.get("idcardNo");
                String goods = (String) om.get("goods");
                String totalPrice = om.get("totalPrice").toString();
                ImageUtils.copyToFolder(folder, orderNo, name, idcard, goods, totalPrice, i + 1);
            } else if (StringUtils.equals("2", (String) om.get("hasPic"))) {
                String orderNo = (String) om.get("orderNo");
                String name = (String) om.get("fakeReceiverName");
                String idcard = (String) om.get("fakeIdcardNo");
                String goods = (String) om.get("goods");
                String totalPrice = om.get("totalPrice").toString();
                ImageUtils.copyToFolder(folder, orderNo, name, idcard, goods, totalPrice, i + 1);
            }
        }
        // 将目录 压缩为zip文件 为了下载的时候显示进度，先压缩成文件获取大小后再写出去
        File zipFile = new File(System.getProperty("java.io.tmpdir") + File.separator + palletName + "Declaration.zip");
        if (zipFile.exists()) {
            zipFile.delete();
        }
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        // 打包
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            addToZIP(zipOut, files[i]);
        }
        zipOut.close();
        return zipFile;
    }

    /**
     * 下载清关文件
     */
    @RequestMapping("/downLoadCouriers")
    public void downLoadCouriers(@RequestParam("id") Long palletId, HttpServletResponse response) throws IOException {
        String palletName = palletService.findPalletName(palletId);
        // 生成excel
        List<Map<String, Object>> items = palletService.findItemsByPalletId(palletId);
        HSSFWorkbook wb = generateCouriersXLS(items);
        // 写出
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition",
                "attachment; filename=" + URLEncoder.encode(palletName + "Couriers.xls", "UTF8"));
        response.setContentType("application/vnd.ms-excel");
        wb.write(response.getOutputStream());
    }

    /**
     * 生成Couriers.xls
     */
    private HSSFWorkbook generateCouriersXLS(List<Map<String, Object>> items) throws IOException {
        InputStream in = PalletManageController.class.getResourceAsStream("/templates/Couriers.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        HSSFSheet sheet = wb.getSheetAt(0);
        // 设置 列为纯文本
        DataFormat format = wb.createDataFormat();
        CellStyle cs = wb.createCellStyle();
        cs.setDataFormat(format.getFormat("@"));
        sheet.setDefaultColumnStyle(1, cs);
        sheet.setDefaultColumnStyle(4, cs);
        sheet.setDefaultColumnStyle(5, cs);
        sheet.setDefaultColumnStyle(15, cs);
        sheet.setDefaultColumnStyle(20, cs);
        sheet.setDefaultColumnStyle(23, cs);
        sheet.setDefaultColumnStyle(24, cs);
        // 写数据
        HSSFRow row = null;
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        for (int i = 0; i < items.size(); i++) {
            Map<String, Object> map = items.get(i);
            row = sheet.createRow(i + 1);
            HSSFCell c = null;
            // 第四步，创建单元格，并设置值
            c = row.createCell(0);
            c.setCellValue(nvl(map.get("senderName")));
            c = row.createCell(1);
            c.setCellValue(nvl(map.get("senderPhone")));
            c = row.createCell(2);
            c.setCellValue(nvl(map.get("senderAddress")));
            if (StringUtils.equals("1", map.get("hasPic").toString())) {
                c = row.createCell(3);
                c.setCellValue(nvl(map.get("receiverName")));
                c = row.createCell(4);
                c.setCellValue(nvl(map.get("idcardNo")));
                c = row.createCell(5);
                c.setCellValue(nvl(map.get("receiverPhone")));
                c = row.createCell(6);
                c.setCellValue(nvl(map.get("receiverProvinceCode")));
                c = row.createCell(7);
                c.setCellValue(nvl(map.get("receiverDistrictCode")));
                c = row.createCell(8);
                c.setCellValue("");
                c = row.createCell(9);
                c.setCellValue(getReceiverAddress(map));
            } else if (StringUtils.equals("2", map.get("hasPic").toString())) {
                c = row.createCell(3);
                c.setCellValue(nvl(map.get("fakeReceiverName")));
                c = row.createCell(4);
                c.setCellValue(nvl(map.get("fakeIdcardNo")));
                c = row.createCell(5);
                c.setCellValue(nvl(map.get("fakeReceiverPhone")));
                c = row.createCell(6);
                c.setCellValue(nvl(map.get("fakeReceiverProvinceCode")));
                c = row.createCell(7);
                c.setCellValue(nvl(map.get("fakeReceiverDistrictCode")));
                c = row.createCell(8);
                c.setCellValue("");
                c = row.createCell(9);
                c.setCellValue(getReceiverAddress(map));
            }
            c = row.createCell(10);
            c.setCellValue(nvl(map.get("cnName")));
            c = row.createCell(11);
            c.setCellValue(nvl(map.get("count")));
            c = row.createCell(12);
            c.setCellValue(getPrice(map));
            c = row.createCell(13);
            c.setCellValue(nvl(map.get("grossWeight")));
            c = row.createCell(14);
            c.setCellValue(nvl(map.get("netWeight")));
            c = row.createCell(15);
            c.setCellValue(nvl(map.get("taxNo")));
            c = row.createCell(16);
            c.setCellValue(nvl(map.get("cnName")));
            c = row.createCell(17);
            c.setCellValue(nvl(map.get("brand")));
            c = row.createCell(18);
            c.setCellValue(nvl(map.get("unit")));
            c = row.createCell(19);
            c.setCellValue(nvl(map.get("count")));
            c = row.createCell(20);
            c.setCellValue(CounterConstants.map.get(nvl(map.get("counter"))));
            c = row.createCell(21);
            c.setCellValue(nvl(map.get("price")));
            c = row.createCell(22);
            c.setCellValue("601");
            c = row.createCell(23);
            c.setCellValue(nvl(map.get("barcode")));
            c = row.createCell(24);
            c.setCellValue(nvl(map.get("orderNo")));
        }
        return wb;
    }

    /**
     * 计算商品价格
     */
    private String getPrice(Map<String, Object> map) {
        int count = (Integer) map.get("count");
        if (map.get("price") != null) {
            BigDecimal b = (BigDecimal) map.get("price");
            BigDecimal b2 = b.multiply(new BigDecimal(count));
            return b2.toString();
        }
        return "";
    }

    /**
     * 下载清关文件
     */
    @RequestMapping("/downLoadCustomers")
    public void downLoadCustomers(@RequestParam("id") Long palletId, HttpServletResponse response) throws IOException {
        String palletName = palletService.findPalletName(palletId);
        List<Map<String, Object>> items = palletService.findDeclarationByPalletId(palletId);
        // 生成excel
        HSSFWorkbook wb = generateCustomsXLS(items);
        // 访问
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition",
                "attachment; filename=" + URLEncoder.encode(palletName + "Customs.xls", "UTF8"));
        response.setContentType("application/vnd.ms-excel");
        wb.write(response.getOutputStream());
    }

    /**
     * 生成custom.xls
     */
    private HSSFWorkbook generateCustomsXLS(List<Map<String, Object>> items) throws IOException {
        InputStream in = PalletManageController.class.getResourceAsStream("/templates/Customs.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        HSSFSheet sheet = wb.getSheetAt(0);
        // 设置 列为纯文本
        DataFormat format = wb.createDataFormat();
        CellStyle cs = wb.createCellStyle();
        cs.setDataFormat(format.getFormat("@"));
        sheet.setDefaultColumnStyle(0, cs);
        // 写数据
        HSSFRow row = null;
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        for (int i = 0; i < items.size(); i++) {
            Map<String, Object> map = items.get(i);
            row = sheet.createRow(i + 1);
            HSSFCell c = null;
            // 第四步，创建单元格，并设置值
            c = row.createCell(0);
            c.setCellValue(nvl(map.get("orderNo")));
            c = row.createCell(2);
            c.setCellValue(nvl(map.get("goods")));
            c = row.createCell(3);
            c.setCellValue(nvl(map.get("totalPrice")));
            if (StringUtils.equals("1", map.get("hasPic").toString())) {
                c = row.createCell(5);
                c.setCellValue(nvl(map.get("receiverName")));
            } else if (StringUtils.equals("2", map.get("hasPic").toString())) {
                c = row.createCell(5);
                c.setCellValue(nvl(map.get("fakeReceiverName")));
            }
        }
        return wb;
    }

    /**
     * 批量下载
     */
    @RequestMapping("/downLoadBatch")
    public void downLoadBatch(@RequestParam("id") Long palletId, HttpServletResponse response) throws IOException {
        // courier
        String palletName = palletService.findPalletName(palletId);
        // 生成 counrier excel
        List<Map<String, Object>> items = palletService.findItemsByPalletId(palletId);
        HSSFWorkbook couriersWb = generateCouriersXLS(items);
        File couriersXLS = new File(
                System.getProperty("java.io.tmpdir") + File.separator + palletName + "Couriers.xls");
        if (couriersXLS.exists()) {
            couriersXLS.delete();
        }
        couriersWb.write(new FileOutputStream(couriersXLS));
        // 生成 Items
        HSSFWorkbook itemsWb = generateItemsXLS(items);
        File itemsXLS = new File(System.getProperty("java.io.tmpdir") + File.separator + palletName + "Items.xls");
        if (itemsXLS.exists()) {
            itemsXLS.delete();
        }
        itemsWb.write(new FileOutputStream(itemsXLS));
        // 生成customer excel
        List<Map<String, Object>> declares = palletService.findDeclarationByPalletId(palletId);
        HSSFWorkbook customWb = generateCustomsXLS(items);
        File customerXLS = new File(System.getProperty("java.io.tmpdir") + File.separator + palletName + "Customs.xls");
        if (customerXLS.exists()) {
            customerXLS.delete();
        }
        customWb.write(new FileOutputStream(customerXLS));
        // declaration zip
        File declarationZIP = generateDeclarationZIP(palletName, declares);
        // ids
        List<OrderMain> oms = palletService.findOrderPicByPalletId(palletId);
        File idsZIP = generateIdZIP(palletName, oms);
        // 发票 invoice
        File invoiceZIP = generateInvoiceZIP(oms, palletName);
        // 打成压缩包
        // 将目录 压缩为zip文件 为了下载的时候显示进度，先压缩成文件获取大小后再写出去
        File zipFile = new File(System.getProperty("java.io.tmpdir") + File.separator + palletName + "batch.zip");
        if (zipFile.exists()) {
            zipFile.delete();
        }
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        // 打包
        addToZIP(zipOut, couriersXLS);
        addToZIP(zipOut, itemsXLS);
        addToZIP(zipOut, customerXLS);
        addToZIP(zipOut, declarationZIP);
        addToZIP(zipOut, idsZIP);
        addToZIP(zipOut, invoiceZIP);
        zipOut.close();
        // 准备流
        response.setCharacterEncoding("UTF-8");
        String fname = URLEncoder.encode(palletName + "batch.zip", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fname);
        response.setContentType("application/zip");// 定义输出类型
        response.setContentLength((int) zipFile.length());
        // 写
        OutputStream out = response.getOutputStream();
        InputStream input = new FileInputStream(zipFile);
        int temp = 0;
        byte[] buf = new byte[1024];
        while ((temp = input.read(buf)) != -1) {
            out.write(buf, 0, temp);
        }
        input.close();
        out.flush();
        out.close();
    }

    /**
     * 添加到压缩包
     */
    private void addToZIP(ZipOutputStream zipOut, File file) throws IOException {
        InputStream input = new FileInputStream(file);
        zipOut.putNextEntry(new ZipEntry(file.getName()));
        int temp = 0;
        byte[] buf = new byte[1024];
        while ((temp = input.read(buf)) != -1) {
            zipOut.write(buf, 0, temp);
        }
        input.close();
        zipOut.flush();
    }

    /**
     * 下载德邦物流 大客户导入excel
     */
    @RequestMapping("/downLoadDeppon")
    public void downLoadDeppon(@RequestParam("id") Long palletId, HttpServletResponse response) throws IOException {
        String palletName = palletService.findPalletName(palletId);
        List<Map<String, Object>> items = palletService.findDeclarationByPalletId(palletId);
        // 生成excel
        HSSFWorkbook wb = generateDepponXLS(items);
        // 访问
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition",
                "attachment; filename=" + URLEncoder.encode(palletName + "deppon.xls", "UTF8"));
        response.setContentType("application/vnd.ms-excel");
        wb.write(response.getOutputStream());
    }

    private HSSFWorkbook generateDepponXLS(List<Map<String, Object>> items) throws IOException {
        InputStream in = PalletManageController.class.getResourceAsStream("/templates/deppon.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        HSSFSheet sheet = wb.getSheetAt(0);
        // 设置 列为纯文本
        DataFormat format = wb.createDataFormat();
        CellStyle cs = wb.createCellStyle();
        cs.setDataFormat(format.getFormat("@"));
        sheet.setDefaultColumnStyle(0, cs);
        // 写数据
        HSSFRow row = null;
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        for (int i = 0; i < items.size(); i++) {
            Map<String, Object> map = items.get(i);
            row = sheet.createRow(i + 3);
            HSSFCell c = null;
            // 第四步，创建单元格，并设置值
            c = row.createCell(0);
            c.setCellValue(i + 1);
            c = row.createCell(1);
            c.setCellValue(nvl(map.get("orderNo")));
            c = row.createCell(2);
            // c.setCellValue(dictService.findByName("deppon_company"));
            c.setCellValue(nvl(map.get("senderName")));
            c = row.createCell(3);
            // c.setCellValue(dictService.findByName("deppon_sender"));
            c.setCellValue(nvl(map.get("senderName")));
            c = row.createCell(4);
            // c.setCellValue(dictService.findByName("deppon_phone"));
            c.setCellValue(nvl(map.get("senderPhone")));
            c = row.createCell(6);
            // c.setCellValue(dictService.findByName("deppon_address"));
            c.setCellValue(getSenderAddress(map));
            c = row.createCell(8);
            c.setCellValue(nvl(map.get("receiverName")));
            c = row.createCell(10);
            c.setCellValue(nvl(map.get("receiverPhone")));
            c = row.createCell(11);
            c.setCellValue(getReceiverAddress2(map));
            c = row.createCell(12);
            c.setCellValue("标准快递");
            c = row.createCell(13);
            c.setCellValue("月结");
            c = row.createCell(14);
            String goods = nvl(map.get("goods"));
            c.setCellValue(goods);

            // String[] infos = goods.split(",");
            // int count = 0;
            // for (String info : infos) {
            // if (StringUtils.isNotBlank(info)) {
            // String[] x = info.split(" ");
            // if (x.length == 3) {
            // count = count + Integer.parseInt(x[2]);
            // }
            // }
            // }
            c = row.createCell(15);
            c.setCellValue(1);
            c = row.createCell(17);
            c.setCellValue(nvl(map.get("totalWeight")));
            c = row.createCell(21);
            c.setCellValue("无");
        }
        return wb;
    }

    private String getSenderAddress(Map<String, Object> map) {
        return nvl(map.get("senderProvince")) + "-" + nvl(map.get("senderCity")) + "-" + nvl(map.get("senderDistrict"))
                + "-" + nvl(map.get("senderAddress"));
    }

    /**
     * 下载圆通快递 导入excel
     */
    @RequestMapping("/downloadYuantong")
    public void downloadYuantong(@RequestParam("id") Long palletId, HttpServletResponse response) throws IOException {
        String palletName = palletService.findPalletName(palletId);
        List<Map<String, Object>> items = palletService.findDeclarationByPalletId(palletId);
        // 生成excel
        HSSFWorkbook wb = generateYuantongXLS(items);
        // 访问
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition",
                "attachment; filename=" + URLEncoder.encode(palletName + "yuantong.xls", "UTF8"));
        response.setContentType("application/vnd.ms-excel");
        wb.write(response.getOutputStream());
    }

    private HSSFWorkbook generateYuantongXLS(List<Map<String, Object>> items) throws IOException {
        InputStream in = PalletManageController.class.getResourceAsStream("/templates/yuantong.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        HSSFSheet sheet = wb.getSheetAt(0);
        // 设置 列为纯文本
        DataFormat format = wb.createDataFormat();
        CellStyle cs = wb.createCellStyle();
        cs.setDataFormat(format.getFormat("@"));
        sheet.setDefaultColumnStyle(0, cs);
        // 写数据
        HSSFRow row = null;
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        for (int i = 0; i < items.size(); i++) {
            Map<String, Object> map = items.get(i);
            row = sheet.createRow(i + 1);
            HSSFCell c = null;
            // 第四步，创建单元格，并设置值
            c = row.createCell(1);
            c.setCellValue(nvl(map.get("receiverName")));
            c = row.createCell(3);
            c.setCellValue(nvl(map.get("receiverPhone")));
            c = row.createCell(4);
            c.setCellValue(getReceiverAddress2(map));
            c = row.createCell(5);
            c.setCellValue(nvl(map.get("orderNo")));
        }
        return wb;
    }

    @RequestMapping("/downloadBarcode")
    public void downloadBarcode(@RequestParam("id") Long palletId, HttpServletResponse response) throws IOException {
        String palletName = palletService.findPalletName(palletId);
        List<Map<String, Object>> items = palletService.findDeclarationByPalletId(palletId);
        // 在临时目录下创建目录 托盘号Declaration
        File folder = new File(System.getProperty("java.io.tmpdir") + File.separator + palletName + "barCodes");
        if (folder.exists()) {
            FileUtils.cleanDirectory(folder);
        } else {
            folder.mkdirs();
        }
        // 生成图片移到目录下
        for (int i = 0; i < items.size(); i++) {
            Map<String, Object> om = items.get(i);
            ImageUtils.createBarcode(folder, om, palletName, i);
        }
        // 将目录 压缩为zip文件 为了下载的时候显示进度，先压缩成文件获取大小后再写出去
        File zipFile = new File(System.getProperty("java.io.tmpdir") + File.separator + palletName + "barCodes.zip");
        if (zipFile.exists()) {
            zipFile.delete();
        }
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        // 打包
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            addToZIP(zipOut, files[i]);
        }
        zipOut.close();
        // 准备流
        response.setCharacterEncoding("UTF-8");
        String fname = URLEncoder.encode(palletName + "barCodes.zip", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fname);
        response.setContentType("application/zip");// 定义输出类型
        response.setContentLength((int) zipFile.length());
        // 写
        OutputStream out = response.getOutputStream();
        InputStream input = new FileInputStream(zipFile);
        int temp = 0;
        byte[] buf = new byte[1024];
        while ((temp = input.read(buf)) != -1) {
            out.write(buf, 0, temp);
        }
        input.close();
        out.flush();
        out.close();
    }

    @RequestMapping("/downLoadInvoice")
    public void downLoadInvoice(@RequestParam("id") Long palletId, HttpServletResponse response) throws IOException {
        String palletName = palletService.findPalletName(palletId);
        List<OrderMain> orders = palletService.findOrderPicByPalletId(palletId);
        File zipFile = generateInvoiceZIP(orders, palletName);
        // 准备流
        response.setCharacterEncoding("UTF-8");
        String fname = URLEncoder.encode(palletName + "invoice.zip", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fname);
        response.setContentType("application/zip");// 定义输出类型
        response.setContentLength((int) zipFile.length());
        // 写
        OutputStream out = response.getOutputStream();
        InputStream input = new FileInputStream(zipFile);
        int temp = 0;
        byte[] buf = new byte[1024];
        while ((temp = input.read(buf)) != -1) {
            out.write(buf, 0, temp);
        }
        input.close();
        out.flush();
        out.close();
    }

    private File generateInvoiceZIP(List<OrderMain> orders, String palletName) throws IOException {
        // 在临时目录下创建目录 托盘号
        File folder = new File(System.getProperty("java.io.tmpdir") + File.separator + palletName + "Invoice");
        if (folder.exists()) {
            FileUtils.cleanDirectory(folder);
        } else {
            folder.mkdirs();
        }
        // 生成图片移到目录下
        for (int i = 0; i < orders.size(); i++) {
            OrderMain om = orders.get(i);
            ImageUtils.createInvoice(folder, palletService.findGoodsByOrderId(om.getId()), om.getOrderNo(), i);
        }
        // 将目录 压缩为zip文件 为了下载的时候显示进度，先压缩成文件获取大小后再写出去
        File zipFile = new File(System.getProperty("java.io.tmpdir") + File.separator + palletName + "invoice.zip");
        if (zipFile.exists()) {
            zipFile.delete();
        }
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        // 打包
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            addToZIP(zipOut, files[i]);
        }
        zipOut.close();
        return zipFile;
    }
}
