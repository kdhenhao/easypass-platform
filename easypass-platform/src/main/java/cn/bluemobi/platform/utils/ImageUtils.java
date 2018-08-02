/**
 * Project Name:lmExpress-platform
 * File Name:ImageUtils.java
 * Package Name:cn.bluemobi.platform.utils
 * Date:2016年11月21日下午2:57:01
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import cn.bluemobi.common.utils.DateUtils;

/**
 * Description: <br/>
 * Date: 2016年11月21日 下午2:57:01 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class ImageUtils {

    private static final Font headFont = loadFont("SIMSUN.TTC", 32);

    private static final Font itemFont = loadFont("SIMSUN.TTC", 28);

    private static final Font smallFont = loadFont("SIMSUN.TTC", 15);

    private static final Font custome1 = loadFont("panwanqiong.ttf", 72);

    private static final Font custome2 = loadFont("elephant.ttf", 72);

    private static final Font custome3 = loadFont("yingna.ttf", 72);

    private static final Font invoiceXs = loadFont("LUCON.TTF", 15);

    private static final Font invoiceSm = loadFont("LUCON.TTF", 18);

    private static final Font invoiceLg = loadFont("LUCON.TTF", 25);

    public static void main(String[] args) throws IOException {
        // BufferedImage image =
        // ImageIO.read(ImageUtils.class.getResourceAsStream("/templates/invoice/invoiceDay.jpg"));
        // BufferedImage dest = new BufferedImage(image.getWidth(),
        // image.getHeight(), BufferedImage.TYPE_INT_RGB);
        // // 画原图
        // Graphics2D g = dest.createGraphics();
        // g.drawImage(image, 0, 0, null);
        // g.setColor(Color.black);// 设置字体颜色
        // // g.setBackground(Color.white);
        //
        // g.setFont(invoiceSm);
        // g.drawString("$45.00 × 1 = $45.00", 120, 255);
        // g.setFont(invoiceXs);
        // g.drawString("reparedStatement pstmt = conn", 120, 255 + 20);
        // // 货物信息
        // g.setFont(invoiceSm);
        // g.drawString("$45.00 × 1 = $45.00", 120, 255 + 20 + 20);
        // g.setFont(invoiceXs);
        // g.drawString("reparedStatement pstmt = conn", 120, 255 + 20 + 40);
        //
        // g.setFont(invoiceLg);
        // g.drawString("$45", 330, 400);
        // g.drawString("$45", 330, 450);
        // g.drawString("$45", 330, 500);
        // ImageIO.write(dest, "jpg", new File("D:\\out3.jpg"));
        NumberFormat nf = new DecimalFormat("$##.00");
        System.out.println(nf.format(new BigDecimal("111.99")));
    }

    /**
     * 获取自定义字体
     */
    public static Font loadFont(String fontFileName, float fontSize) {
        try {
            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT,
                    ImageUtils.class.getResourceAsStream("/fonts/" + fontFileName));
            Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
            return dynamicFontPt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 复制声明文件到目录下
     */
    public static void copyToFolder(File folder, String orderNo, String name, String idcard, String goods,
            String totalPrice, int index) throws IOException {
        BufferedImage image = ImageIO.read(ImageUtils.class.getResourceAsStream("/templates/declaration.jpg"));
        BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = dest.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setBackground(Color.white);
        g.setColor(Color.black);// 设置字体颜色
        g.setFont(headFont);
        g.drawString(name, 250, 258);
        g.drawString(idcard, 250, 296);
        g.drawString("$" + totalPrice + " AUD", 250, 338);
        g.drawString("", 250, 376);

        g.setFont(itemFont);
        if (StringUtils.isNotEmpty(goods)) {
            List<String> list = Arrays.asList(goods.split(","));
            list.remove("");
            for (int i = 0; i < list.size(); i++) {
                g.drawString(list.get(i), 140, 530 + 28 * i);
            }
        }

        int i = (new Random()).nextInt(2);
        if (i == 0) {
            g.setFont(custome1);
        } else if (i == 1) {
            g.setFont(custome2);
        } else {
            g.setFont(custome3);
        }
        g.drawString(name, 140, 1480);
        g.setFont(itemFont);
        g.drawString(DateUtils.format(new Date(), "yyyy年MM月dd日"), 140, 1520);
        g.dispose();
        ImageIO.write(dest, "jpg", new File(folder, index + "_" + orderNo + ".jpg"));
    }

    /**
     * 获取大号的条码号
     */
    public static BufferedImage getBarCodeBig(String barcode) throws IOException {
        Code128Bean bean = new Code128Bean();
        final int dpi = 160;// 打印机分辨率
        bean.setModuleWidth(0.25);
        bean.setHeight(12);
        // Set up the canvas provider for monochrome JPEG output
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        bean.generateBarcode(canvas, barcode);
        canvas.finish();
        BufferedImage bi = canvas.getBufferedImage();
        return bi;
        // ImageIO.write(bi, "jpg", new File("D:\\out.jpg"));
    }

    /**
     * 获取小号的条码号
     */
    public BufferedImage getBarCodeSmall(String barcode) throws IOException {
        Code128Bean bean = new Code128Bean();
        final int dpi = 160;// 打印机分辨率
        bean.setModuleWidth(0.24);
        bean.setHeight(10);
        // Set up the canvas provider for monochrome JPEG output
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        bean.generateBarcode(canvas, barcode);
        canvas.finish();
        BufferedImage bi = canvas.getBufferedImage();
        return bi;
        // ImageIO.write(bi, "jpg", new File("D:\\out.jpg"));
    }

    /**
     * 生成清关条码
     * 
     * @param folder
     */
    public static void createBarcode(File folder, Map<String, Object> om, String palletName, int i) throws IOException {
        BufferedImage image = ImageIO.read(ImageUtils.class.getResourceAsStream("/templates/miandan.png"));
        BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        // 画原图
        Graphics2D g = dest.createGraphics();
        g.drawImage(image, 0, 0, null);
        // 画大条码
        BufferedImage barcode1 = getBarCodeBig(nvl(om.get("clearNo")));
        g.drawImage(barcode1, 30, 105, null);
        // 画小条码
        BufferedImage barcode2 = getBarCodeBig(nvl(om.get("orderNo")));
        g.drawImage(barcode2, 30, 350, null);
        // 托盘名称
        g.setColor(Color.black);// 设置字体颜色
        g.setBackground(Color.white);
        g.setFont(headFont);
        g.drawString(palletName, 40, 210);
        // 货物信息
        String goods = nvl(om.get("goods"));
        if (StringUtils.isNotEmpty(goods)) {
            g.setFont(smallFont);
            String[] gs = goods.split(",");
            int y = 240;
            int x = 80;
            for (String str : gs) {
                g.drawString(str, x, y);
                x = 20;
                y = y + 20;
            }
        }
        // 重量
        g.setFont(headFont);
        g.drawString(nvl(om.get("totalWeight")), 285, 300);
        // 收件人
        g.setFont(smallFont);
        g.drawString(nvl(om.get("receiverName")), 70, 440);
        g.drawString(nvl(om.get("receiverPhone")), 200, 435);
        g.drawString(getReceiverAddress(om), 20, 470);
        // 寄件人
        g.drawString(nvl(om.get("senderName")), 70, 490);
        g.drawString(nvl(om.get("senderPhone")), 200, 490);
        g.drawString(getSenderAddress(om), 20, 515);
        // 下标
        g.setFont(itemFont);
        g.drawString(String.valueOf(i + 1), 8, 560);
        ImageIO.write(dest, "png", new File(folder, (i + 1) + "_" + nvl(om.get("orderNo")) + ".png"));
    }

    private static String nvl(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 直接获取真实收货人地址
     */
    private static String getReceiverAddress(Map<String, Object> map) {
        String province = (String) map.get("receiverProvince");
        String city = (String) map.get("receiverCity");
        if (StringUtils.equals(province, city)) {
            return city + (String) map.get("receiverDistrict") + (String) map.get("receiverAddress");
        } else {
            return province + city + (String) map.get("receiverDistrict") + (String) map.get("receiverAddress");
        }
    }

    private static String getSenderAddress(Map<String, Object> map) {
        return nvl(map.get("senderProvince")) + " " + nvl(map.get("senderCity")) + " " + nvl(map.get("senderDistrict"))
                + " " + nvl(map.get("senderAddress"));
    }

    /**
     * 创建发票文件
     */
    public static void createInvoice(File folder, List<Map<String, Object>> goods, String orderNo, int i)
            throws IOException {
        int j = new Random().nextInt(7);
        String[] files = { "invoiceChem.jpg", "invoiceDay.jpg", "invoiceGG.jpg", "invoiceTorry.jpg", "invoiceWool1.jpg",
                "invoiceWool2.jpg", "invoiceWool3.jpg" };
        BufferedImage image = ImageIO.read(ImageUtils.class.getResourceAsStream("/templates/invoice/" + files[j]));
        BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        // 画原图
        Graphics2D g = dest.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setColor(Color.black);// 设置字体颜色
        // g.setBackground(Color.white);
        for (int k = 0; k < goods.size(); k++) {
            Map<String, Object> comm = goods.get(k);
            g.setFont(invoiceSm);
            g.drawString(generateStr1(comm), 120, 255 + k * 40);
            g.setFont(invoiceXs);
            g.drawString(nvl(comm.get("enName")), 120, 255 + k * 40 + 20);
        }
        g.setFont(invoiceLg);
        String totalPrice = generateTotalPrice(goods);
        g.drawString(totalPrice, 330, 400);
        g.drawString("$0.00", 330, 450);
        g.drawString(totalPrice, 330, 500);
        ImageIO.write(dest, "jpg", new File(folder, (i + 1) + "_" + orderNo + ".jpg"));
    }

    /**
     * 计算订单总价
     */
    private static String generateTotalPrice(List<Map<String, Object>> goods) {
        BigDecimal p = BigDecimal.ZERO;
        for (Map<String, Object> map : goods) {
            int count = (int) map.get("count");
            if (map.get("price") != null) {
                BigDecimal big = (BigDecimal) map.get("price");
                p = p.add(big.multiply(new BigDecimal(count)));
            }
        }
        NumberFormat nf = new DecimalFormat("$##.00");
        return nf.format(p);
    }

    /**
     * 生成物品字符串
     */
    private static String generateStr1(Map<String, Object> comm) {
        int count = (int) comm.get("count");
        String price = "$0.00";
        if (comm.get("price") != null) {
            BigDecimal big = (BigDecimal) comm.get("price");
            NumberFormat nf = new DecimalFormat("$##.00");
            price = nf.format(big);
            BigDecimal totalPrice = big.multiply(new BigDecimal(count));
            return price + " × " + count + " = " + nf.format(totalPrice);
        } else {
            return "";
        }
    }

}
