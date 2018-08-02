/**
 * Project Name:demo
 * File Name:SystemUserController.java
 * Package Name:cn.bluemobi.demo.controller.backend.system
 * Date:2015年12月9日下午3:27:20
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.service.UserService;

/**
 * Description: <br/>
 * Date: 2015年12月9日 下午3:27:20 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/systemUser")
public class SystemUserController extends PlatformBaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toUserPage")
    public String toUserPage(Model model) {
        model.addAttribute("roles", userService.findAllRoles());
        return "system/userList";
    }

    @RequestMapping("/findUsers")
    @ResponseBody
    public DatatablePage findUsers(PageCondition cond) {
        return new DatatablePage(cond, userService.findAllUsers(cond));
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public MapDto saveUser(User user) {
        return userService.saveOrUpdate(user, getUser());
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public MapDto deleteById(@RequestParam("id") String id) {
        return userService.deleteById(id, getUser());
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public MapDto updatePwd(@RequestParam("username") String username, @RequestParam("currentPwd") String currentPwd,
            @RequestParam("newPwd") String newPwd) {
        return userService.updatePwd(username, currentPwd, newPwd, getUser());
    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    public MapDto resetPassword(@RequestParam("id") String id, @RequestParam("name") String name) {
        return userService.resetPassword(id, name, getUser());
    }
}
