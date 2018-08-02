/**
 * Project Name:jrx
 * File Name:BaseController.java
 * Package Name:cn.bluemobi.jrx.core.web
 * Date:2015年7月10日下午4:03:52
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.core.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 最顶层的controller<br/>
 * Date: 2015年7月10日 下午4:03:52 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class BaseController {

    /**
     * 返回ip地址
     */
    protected String getRemortIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null) {
            return request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 返回项目路径
     */
    protected String getContextPath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
                + "/";
        return basePath;
    }

    /**
     * 返回域名
     */
    protected String getDomain(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }
}
