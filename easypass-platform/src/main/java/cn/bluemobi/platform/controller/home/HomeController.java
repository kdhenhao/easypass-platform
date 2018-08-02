/**
 * Project Name:mfh-web
 * File Name:HomeController.java
 * Package Name:cn.bluemobi.web.controller.home
 * Date:2016年6月13日下午1:57:07
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.home;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bluemobi.platform.core.controller.PlatformBaseController;

/**
 * Description: 首页 <br/>
 * Date: 2016年6月13日 下午1:57:07 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
public class HomeController extends PlatformBaseController {

    /**
     * 跳转到首页
     */
    @RequestMapping("/index")
    public String index() {
        Subject sub = SecurityUtils.getSubject();
        if (sub.isAuthenticated()) {
            // 已登陆
            return "home/home";
        }
        return "home/login";
    }
}
