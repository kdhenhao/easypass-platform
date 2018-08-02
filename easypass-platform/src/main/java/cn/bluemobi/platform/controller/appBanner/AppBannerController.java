/**
 * Project Name:easypass-platform
 * File Name:AppBannerController.java
 * Package Name:cn.bluemobi.platform.controller.appBanner
 * Date:2017年5月31日下午1:42:31
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.appBanner;

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
import cn.bluemobi.platform.entity.appBanner.AppBanner;
import cn.bluemobi.platform.entity.appCampus.AppCampus;
import cn.bluemobi.platform.entity.course.Course;
import cn.bluemobi.platform.service.AppBannerService;
import cn.bluemobi.platform.service.AppCampusService;
import cn.bluemobi.platform.service.CourseService;

/**
 * Description: appbanner管理<br/>
 * Date: 2017年5月31日 下午1:42:31 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Controller
@RequestMapping("admin/appBanner")
public class AppBannerController extends PlatformBaseController {
    @Autowired
    private AppBannerService appBannerService;

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private AppCampusService appCampusService;

    @RequestMapping("/toAppBannerPage")
    public String toAppBannerPage(Model model) {
        // 获取专业分类
        List<Course> courseList = courseService.getAllCourseList();
        List<AppCampus> campusList = appCampusService.getAllCampusList();
        model.addAttribute("courseList", courseList);
        model.addAttribute("campusList", campusList);
        return "appBanner/appBannerList";
    }

    /**
     * 查询所有
     */
    @RequestMapping("/findAppBannerList")
    @ResponseBody
    public DatatablePage findAppBannerList(@RequestParam("draw") Integer draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length, AppBanner appBanner) {
        return new DatatablePage(draw, appBannerService.findAppBannerListByPage(start, length, appBanner));
    }

    /**
     * 更新status状态
     */
    @RequestMapping("/updateAppBannerStatus")
    @ResponseBody
    public MapDto updateAppBannerStatus(@RequestParam("status") int status, @RequestParam("id") Long id) {
        return appBannerService.updateAppBannerStatus(status, id);
    }

    /**
     * 修改
     */
    @RequestMapping("updateAppBanner")
    @ResponseBody
    public MapDto updateAppBanner(AppBanner appBanner) {
        return appBannerService.updateAppBanner(appBanner);
    }

    /**
     * 添加
     */
    @RequestMapping("/addAppBanner")
    @ResponseBody
    public MapDto addAppBanner(AppBanner appBanner) {
        return appBannerService.addAppBanner(appBanner);
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteAppBannerById")
    @ResponseBody
    public MapDto deleteAppBannerById(@RequestParam("id") Long id) {
        return appBannerService.deleteAppBannerById(id);
    }
}
