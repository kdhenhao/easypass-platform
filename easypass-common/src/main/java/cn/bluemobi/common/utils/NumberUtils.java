/**
 * Project Name:jrx
 * File Name:NumberUtils.java
 * Package Name:cn.bluemobi.queue.util
 * Date:2015年8月18日上午11:34:37
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.common.utils;

/**
 * Description: <br/>
 * Date: 2015年8月18日 上午11:34:37 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class NumberUtils {
    public static final String[] CHINESE_NO_SMALL = new String[] { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九",
            "十" };

    public static final String[] CHINESE_NO_BIG = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖",
            "拾" };

    public static final String[] unit = { "", "十", "百", "千", "万", "十", "百", "千", "亿", "十" };

    public static String getChineseSmall(Integer i) {
        String str = "";
        StringBuffer sb = new StringBuffer(String.valueOf(i));
        sb = sb.reverse();
        int r = 0;
        int l = 0;
        for (int j = 0; j < sb.length(); j++) {
            /**
             * 当前数字
             */
            r = Integer.valueOf(sb.substring(j, j + 1));

            if (j != 0)
                /**
                 * 上一个数字
                 */
                l = Integer.valueOf(sb.substring(j - 1, j));

            if (j == 0) {
                if (r != 0 || sb.length() == 1)
                    str = CHINESE_NO_SMALL[r];
                continue;
            }

            if (j == 1 || j == 2 || j == 3 || j == 5 || j == 6 || j == 7 || j == 9) {
                if (r != 0)
                    str = CHINESE_NO_SMALL[r] + unit[j] + str;
                else if (l != 0)
                    str = CHINESE_NO_SMALL[r] + str;
                continue;
            }

            if (j == 4 || j == 8) {
                str = unit[j] + str;
                if ((l != 0 && r == 0) || r != 0)
                    str = CHINESE_NO_SMALL[r] + str;
                continue;
            }
        }
        return str;
    }
}
