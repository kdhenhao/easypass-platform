/**
 * Project Name:mfh-common
 * File Name:ValidUtils.java
 * Package Name:cn.bluemobi.common.utils
 * Date:2016年6月14日下午2:51:32
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: <br/>
 * Date: 2016年6月14日 下午2:51:32 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class ValidUtils {

    /**
     * 检查是否是正确的邮箱格式
     */
    public static boolean isEmail(String email) {
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 检查是否是正确的手机号
     */
    public static boolean isMobilePhone(String phone) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
