/**
 * Project Name:easypass-platform
 * File Name:CourseController.java
 * Package Name:cn.bluemobi.platform.controller.course
 * Date:2017年5月24日下午3:44:00
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.appCampus.AppCampus;
import cn.bluemobi.platform.entity.course.Classify;
import cn.bluemobi.platform.entity.course.Course;
import cn.bluemobi.platform.service.AppCampusService;
import cn.bluemobi.platform.service.CourseMajorClassifyService;
import cn.bluemobi.platform.service.CourseService;
import cn.bluemobi.platform.vo.CourseVO;

/**
 * Description: 课程 <br/>
 * Date: 2017年5月24日 下午3:44:00 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Controller
@RequestMapping("admin/course")
public class CourseController extends PlatformBaseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseMajorClassifyService courseClassifyService;
    
    @Autowired
    private AppCampusService appCampusService;

    /**
     * 跳转课程列表
     */
    @RequestMapping("/toCoursePage")
    public String toCoursePage(Model model) {
        // 获取专业分类
        List<Classify> classifyList = courseClassifyService.getAllClassifyList();
        model.addAttribute("classifyList", classifyList);
        return "course/courseList";
    }

    /**
     * 获取课程列表
     */
    @RequestMapping("/getCourseList")
    @ResponseBody
    public DatatablePage getCourseList(@RequestParam("draw") Integer draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length, CourseVO course) {
        return new DatatablePage(draw, courseService.getCourseList(start, length, course));
    }

    /**
     * 跳转新增或编辑课程页面
     */
    @RequestMapping("/toEditCoursePage")
    public String toEditCoursePage(Model model, @RequestParam(value = "id", required = false) Long id) {
        // 跳转修改页面
        if (null != id) {
            model.addAttribute("course", courseService.getCourseById(id));
        } else {// 跳转新增页面
            model.addAttribute("course", null);
        }
        List<Classify> classifyList = courseClassifyService.getAllClassifyList();
        model.addAttribute("classifyList", classifyList);
        List<AppCampus> campusList = appCampusService.getAllCampusList();
        model.addAttribute("campusList", campusList);
        return "course/addOrEditCourse";
    }

    /**
     * 修改课程
     */
    @RequestMapping("/updateCourse")
    public String updateCourse(Course course, @RequestParam("dir") String dir,
            @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2) {
        courseService.updateCourse(course, dir, file1, file2);
        return "redirect:/admin/course/toCoursePage";
    }

    /**
     * 新增课程
     */
    @RequestMapping("/addCourse")
    public String addCourse(Course course, @RequestParam("dir") String dir, @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2) {
        courseService.addCourse(course, dir, file1, file2);
        return "redirect:/admin/course/toCoursePage";
    }

    /**
     * 删除课程
     */
    @RequestMapping("/deleteCourseById")
    @ResponseBody
    public MapDto deleteCourseById(Long id) {
        return courseService.deleteCourseById(id);
    }
}
