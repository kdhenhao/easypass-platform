/**
 * Project Name:easypass-platform
 * File Name:CourseController.java
 * Package Name:cn.bluemobi.platform.controller.course
 * Date:2017年5月24日下午3:44:00
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.course.CourseClassHour;
import cn.bluemobi.platform.service.CourseService;
import cn.bluemobi.platform.vo.CourseClassHourVO;

/**
 * Description: 课时<br/>
 * Date: 2017年5月24日 下午3:44:00 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Controller
@RequestMapping("admin/courseClassHour")
public class CourseClassHourController extends PlatformBaseController {
    @Autowired
    private CourseService courseService;

    /**
     * 跳转课时列表
     */
    @RequestMapping("/toCourseClassHourPage")
    public String toCourseClassHourPage(Model model, @RequestParam(value = "id", required = false) Long courseId) {
        model.addAttribute("courseId", courseId);
        return "course/courseClassHourList";
    }

    /**
     * 获取课时列表
     */
    @RequestMapping("/getCourseClassHourList")
    @ResponseBody
    public DatatablePage getCourseClassHourList(@RequestParam("draw") Integer draw,
            @RequestParam("start") Integer start, @RequestParam("length") Integer length,
            CourseClassHourVO courseClassHour) {
        return new DatatablePage(draw, courseService.getCourseClassHourList(start, length, courseClassHour));
    }

    /**
     * 跳转新增或编辑课时页面
     */
    @RequestMapping("/toEditCourseClassHourPage")
    public String toEditCourseClassHourPage(Model model, @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "courseId") Long courseId) {
        if (null != id) {// 编辑课时
            model.addAttribute("courseClassHour", courseService.getCourseClassHourById(id));
        } else {// 新增课时
            model.addAttribute("courseClassHour", null);
        }
        // 新增或编辑都需要传课程id
        model.addAttribute("courseId", courseId);
        return "course/addOrEditCourseClassHour";
    }

    /**
     * 修改课时
     */
    @RequestMapping("/updateCourseClassHour")
    public String updateCourseClassHour(CourseClassHour courseClassHour) {
        courseService.updateCourseClassHour(courseClassHour);
        return "redirect:/admin/courseClassHour/toCourseClassHourPage?id=" + courseClassHour.getCourseId();
    }

    /**
     * 新增课时
     */
    @RequestMapping("/addCourseClassHour")
    public String addCourseClassHour(CourseClassHour courseClassHour) {
        courseService.addCourseClassHour(courseClassHour);
        return "redirect:/admin/courseClassHour/toCourseClassHourPage?id=" + courseClassHour.getCourseId();
    }

    /**
     * 删除课时
     */
    @RequestMapping("/deleteCourseClassHourById")
    @ResponseBody
    public MapDto deleteCourseClassHourById(Long id) {
        return courseService.deleteCourseClassHourById(id);
    }
}
