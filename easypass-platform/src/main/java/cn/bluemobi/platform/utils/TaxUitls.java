/**
 * Project Name:lmExpress-platform
 * File Name:TaxUitls.java
 * Package Name:cn.bluemobi.platform.utils
 * Date:2016年11月2日下午3:19:27
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

/**
 * Description: <br/>
 * Date: 2016年11月2日 下午3:19:27 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class TaxUitls {
    public static void main(String[] args) {
        File file = new File("D:\\projects\\imimpress\\lm express\\lm express\\税号.txt");
        File out = new File("D:\\projects\\imimpress\\lm express\\lm express\\税号.sql");
        InputStreamReader reader = null;
        OutputStreamWriter writer = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            writer = new OutputStreamWriter(new FileOutputStream(out));
            BufferedReader buf = new BufferedReader(reader);
            String line = null;
            while ((line = buf.readLine()) != null) {
                if (line.length() > 0) {
                    String[] infos = line.split(" ");
                    String code = infos[0];
                    String name = infos[1];
                    int i = 0;
                    if (name.indexOf("－") < 0) {
                        i = 0;
                    } else {
                        String[] dashs = name.split("");
                        int j = 0;
                        for (String d : dashs) {
                            if (d.equals("－")) {
                                j = j + 1;
                            }
                        }
                        i = j;
                    }
                    if (infos.length <= 2) {
                        // 第一级
                        writer.write(
                                "insert into tax_number (tax_no,tax_name,tax_unit,tax_unit_code,tax_price,tax_percent,level) values ('"
                                        + infos[0] + "','" + infos[1] + "',null,null,null,null," + i + ");\n");
                    } else {
                        writer.write(
                                "insert into tax_number (tax_no,tax_name,tax_unit,tax_unit_code,tax_price,tax_percent,level) values ('"
                                        + infos[0] + "','" + infos[1] + "','" + infos[2] + "','" + infos[3] + "','"
                                        + infos[4] + "','" + infos[5] + "'," + i + ");\n");
                    }
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
