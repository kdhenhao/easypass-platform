/**
 * Project Name:jrx
 * File Name:DateUtils.java
 * Package Name:cn.bluemobi.queue.util
 * Date:2015年8月27日下午4:40:56
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: <br/>
 * Date: 2015年8月27日 下午4:40:56 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class DateUtils {

    public static String format(Timestamp time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(time.getTime()));
    }

    public static String format(Date time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(time);
    }

    public static Date parse(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
