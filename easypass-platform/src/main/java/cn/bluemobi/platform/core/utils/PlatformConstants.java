/**
 * Project Name:jrx
 * File Name:BlueMobiConstants.java
 * Package Name:cn.bluemobi.jrx.core.util
 * Date:2015年7月13日上午10:25:41
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.bluemobi.common.core.utils.BlueMobiConstants;

/**
 * Description: <br/>
 * Date: 2015年7月13日 上午10:25:41 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class PlatformConstants implements ApplicationContextAware {

    public static ApplicationContext ctx;

    // 网站名
    public static final String SITE_NAME = PropertiesUtils.getProperty("SITE_NAME");

    // 网站主题风格
    public static final String THEME = PropertiesUtils.getProperty("THEME");

    // 图片服务path
    public static final String IMG_CONTEXT_PATH = PropertiesUtils.getProperty("IMG_CONTEXT_PATH");

    // 图片实际硬盘地址
    public static final String IMG_DISK_PATH = PropertiesUtils.getProperty("IMG_DISK_PATH");

    public static final String IMG_SECURITY_PATH = PropertiesUtils.getProperty("IMG_SECURITY_PATH");

    /**
     * 图片文件格式限制
     */
    public static final String[] IMAGE_FORMAT_ARRAY = new String[] { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };// 全站中引用的图片格式

    /**
     * 图片限制大小 文件的极限大小，以字节为单位，0为不限制。1MB:1*1024*1024
     */
    public static final int IMAGE_SIZE_LIMIT = 1 * 1024 * 1024;

    /**
     * 获取项目在磁盘上面的物理路径
     */
    public static final String WEB_SITE_ROOT_PATH = BlueMobiConstants.class.getResource("/").getPath()
            .replaceAll("%20", " ")
            .substring(0, BlueMobiConstants.class.getResource("/").getPath().replaceAll("%20", " ").indexOf("WEB-INF"));

    /**
     * 获取项目在磁盘上面的物理路径
     */
    public static final String WEB_CLASSPATH = BlueMobiConstants.class.getResource("/").getPath().replaceAll("%20",
            " ");

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
