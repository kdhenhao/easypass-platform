/**
 * Project Name:jrx
 * File Name:PropertiesUtils.java
 * Package Name:cn.bluemobi.jrx.core.util
 * Date:2015年8月13日下午1:48:20
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.core.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Description: <br/>
 * Date: 2015年8月13日 下午1:48:20 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class PropertiesUtils {

    public static final String DEFAULT_PROPERTY = "/conf/application.properties";

    public static String getProperty(String key, String filePath) {
        Properties pro = new Properties();
        try {
            pro.load(PropertiesUtils.class.getResourceAsStream(filePath));
            return pro.getProperty(key);
        } catch (IOException e) {
            return null;
        }
    }

    public static String getProperty(String key) {
        return getProperty(key, DEFAULT_PROPERTY);
    }
}
