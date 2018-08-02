/**
 * Project Name:lmExpress-platform
 * File Name:CityUtils.java
 * Package Name:cn.bluemobi.platform.utils
 * Date:2016年11月4日上午10:35:49
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
 * Date: 2016年11月4日 上午10:35:49 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class CityUtils {
    public static void main(String[] args) {
        File file = new File("D:\\projects\\imimpress\\lm express\\lm express\\城市.txt");
        File out = new File("D:\\projects\\imimpress\\lm express\\lm express\\城市.sql");
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
                    int level = 0;
                    String parent = null;
                    if (code.indexOf("0000") > 0) {
                        level = 0;
                    } else if (code.indexOf("00") > 0) {
                        level = 1;
                        parent = code.substring(0, 2) + "0000";
                    } else {
                        level = 2;
                        parent = code.substring(0, 4) + "00";
                    }
                    if (level == 0) {
                        writer.write("insert into system_area (area_code,area_name,level,parent_code ) values ('" + code
                                + "','" + name + "'," + level + ",null);\n");
                    } else {
                        writer.write("insert into system_area (area_code,area_name,level,parent_code ) values ('" + code
                                + "','" + name + "'," + level + ",'" + parent + "');\n");
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
