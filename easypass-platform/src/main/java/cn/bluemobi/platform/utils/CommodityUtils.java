/**
 * Project Name:lmExpress-platform
 * File Name:CommodityUtils.java
 * Package Name:cn.bluemobi.platform.utils
 * Date:2016年11月17日下午2:55:40
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.codec.binary.StringUtils;

/**
 * Description: <br/>
 * Date: 2016年11月17日 下午2:55:40 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class CommodityUtils {
    public static void main(String[] args) {
        File file = new File("D:\\projects\\imimpress\\lm express\\lm express\\商品列表.csv");
        File out = new File("D:\\projects\\imimpress\\lm express\\lm express\\商品.sql");
        InputStreamReader reader = null;
        OutputStreamWriter writer = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file), "utf-8");
            writer = new OutputStreamWriter(new FileOutputStream(out), "utf-8");
            BufferedReader buf = new BufferedReader(reader);
            String line = null;
            while ((line = buf.readLine()) != null) {
                if (line.length() > 0) {
                    String[] infos = line.split(",");
                    String enName = infos[1];
                    String cnName = infos[2];
                    String brand = infos[3];
                    String barcode = infos[4];
                    String grossWeight = infos[5];
                    String netWeight = infos[6];
                    String unit = infos[7];
                    String counter = infos[8];
                    String price = infos[9];
                    String taxCode = infos[10];
                    if (taxCode.length() == 7) {
                        taxCode = "0" + taxCode;
                    }
                    String sql = "insert into commodity (en_name,cn_name,brand,barcode,gross_weight,net_weight,unit,counter,price,tax_no) values( '"
                            + enName + "','" + cnName + "','" + brand + "',";
                    if (StringUtils.equals("NULL", barcode)) {
                        sql = sql + "null,";
                    } else {
                        sql = sql + "'" + barcode + "',";
                    }
                    sql = sql + grossWeight + "," + netWeight + ",'" + unit + "','" + counter + "'," + price + ",'"
                            + taxCode + "');\n";
                    writer.write(sql);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
