/**
 * Project Name:easypass-platform
 * File Name:AppUserController.java
 * Package Name:cn.bluemobi.platform.controller.appUser
 * Date:2017年5月27日上午11:59:19
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.appUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.appUser.AppUser;
import cn.bluemobi.platform.entity.course.Classify;
import cn.bluemobi.platform.service.AppUserService;
import cn.bluemobi.platform.service.CourseMajorClassifyService;
import cn.bluemobi.platform.vo.AppUserVO;

/**
 * Description: APP用户 <br/>
 * Date: 2017年5月27日 上午11:59:19 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Controller
@RequestMapping("admin/appUser")
public class AppUserController extends PlatformBaseController {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private CourseMajorClassifyService courseClassifyService;

    /**
     * 跳转用户列表页
     */
    @RequestMapping("/toAppUserPage")
    public String toAppUserPage(Model model) {
        // 获取专业分类
        List<Classify> classifyList = courseClassifyService.getAllClassifyList();
        model.addAttribute("classifyList", classifyList);
        return "appUser/appUserList";
    }

    /**
     * 分页查询用户
     */
    @RequestMapping("/getAppUserList")
    @ResponseBody
    public DatatablePage getAppUserList(@RequestParam("draw") int draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length, AppUserVO appUser) {
        return new DatatablePage(draw, appUserService.getAppUserByPage(start, length, appUser));
    }

    /**
     * 新增用户
     */
    @RequestMapping("/addAppUser")
    @ResponseBody
    public MapDto addAppUser(AppUser appUser) {
        return appUserService.addAppUser(appUser);
    }

    /**
     * 编辑用户
     */
    @RequestMapping("/updateAppUser")
    @ResponseBody
    public MapDto updateAppUser(AppUser appUser) {
        return appUserService.updateAppUser(appUser);
    }

    /**
     * 启用
     */
    @RequestMapping("/enableAppUser")
    @ResponseBody
    public MapDto enableAppUser(Long userId) {
        return appUserService.enableAppUser(userId);
    }

    /**
     * 禁用
     */
    @RequestMapping("/disableAppUser")
    @ResponseBody
    public MapDto disableAppUser(Long userId) {
        return appUserService.disableAppUser(userId);
    }

    /**
     * 重置密码
     */
    @RequestMapping("/resetAppUserPassword")
    @ResponseBody
    public MapDto resetPassword(Long userId) {
        return appUserService.resetAppUserPassword(userId);
    }
}
