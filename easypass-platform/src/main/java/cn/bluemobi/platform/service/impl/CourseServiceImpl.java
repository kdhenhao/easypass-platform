/**
 * Project Name:easypass-platform
 * File Name:CourseServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2017年5月24日下午3:45:34
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.course.Course;
import cn.bluemobi.platform.entity.course.CourseClassHour;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.CourseMapper;
import cn.bluemobi.platform.service.CourseService;
import cn.bluemobi.platform.service.LogService;
import cn.bluemobi.platform.utils.FileUploadUtils;
import cn.bluemobi.platform.vo.CourseClassHourVO;
import cn.bluemobi.platform.vo.CourseVO;

/**
 * Description: 课程<br/>
 * Date: 2017年5月24日 下午3:45:34 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    // 添加日志
    @Autowired
    private LogService logService;

    @Autowired
    private HttpSession session;

    @Override
    public Page<CourseVO> getCourseList(Integer start, Integer length, CourseVO course) {

        Page<CourseVO> page = PageUtils.startPage(start, length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("classifyId", course.getClassifyId());
        map.put("courseName", course.getCourseName());
        map.put("teacher", course.getTeacher());
        map.put("grade", course.getGrade());
        map.put("term", course.getTerm());
        map.put("tag", course.getTag());
        map.put("campusId", course.getCampusId());
        map.put("beginTime", course.getBeginTime());
        map.put("endTime", course.getEndTime());
        page.setList(courseMapper.getCourseList(map));
        return page;
    }

    @Override
    public Course getCourseById(Long id) {

        return courseMapper.getCourseById(id);
    }

    @Override
    public MapDto updateCourse(Course course, String dir, MultipartFile file, MultipartFile file2) {

        MapDto dto = new MapDto();
        MapDto map;
        try {
            map = FileUploadUtils.uploadImg(dir, file);
            if (null != map.getData()) {
                course.setPhoto((String) map.getData().get("img"));
            }
            map = FileUploadUtils.uploadImg(dir, file2);
            if (null != map.getData()) {
                course.setCoverImg((String) map.getData().get("img"));
            }
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        int i = courseMapper.updateCourse(course);
        if (i > 0) {
            dto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "更新课程:" + course.getCourseName(), "课程管理");
        } else {
            dto.errorMsg("数据操作失败");
        }
        return dto;
    }

    @Override
    public MapDto addCourse(Course course, String dir, MultipartFile file, MultipartFile file2) {
        MapDto dto = new MapDto();
        MapDto map;
        try {
            map = FileUploadUtils.uploadImg(dir, file);
            if (null != map.getData()) {
                course.setPhoto((String) map.getData().get("img"));
            }
            map = FileUploadUtils.uploadImg(dir, file2);
            if (null != map.getData()) {
                course.setCoverImg((String) map.getData().get("img"));
            }
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        int i = courseMapper.addCourse(course);
        if (i > 0) {
            dto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "添加课程:" + course.getCourseName(), "课程管理");
        } else {
            dto.errorMsg("数据操作失败");
        }
        return dto;
    }

    @Override
    public MapDto deleteCourseById(Long id) {

        MapDto dto = new MapDto();
        int i = courseMapper.deleteCourseById(id);
        if (i > 0) {
            dto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null, "删除课程",
                    "课程管理");

            // 删除课程，课程下面的课时相应删除
            int result = courseMapper.deleteCourseHourByCourseId(id);
            if (result > 0) {
                logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                        "删除课程下面的课时", "课程管理");
            }

        } else {
            dto.setStatus(1);
            dto.setMsg("数据操作失败");
        }
        return dto;
    }

    @Override
    public Page<CourseClassHourVO> getCourseClassHourList(Integer start, Integer length,
            CourseClassHourVO courseClassHour) {

        Page<CourseClassHourVO> page = PageUtils.startPage(start, length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("classHourName", courseClassHour.getClassHourName());
        map.put("courseId", courseClassHour.getCourseId());

        page.setList(courseMapper.getCourseClassHourList(map));
        return page;
    }

    @Override
    public CourseClassHour getCourseClassHourById(Long id) {

        return courseMapper.getCourseClassHourById(id);
    }

    @Override
    public MapDto updateCourseClassHour(CourseClassHour courseClassHour) {

        MapDto dto = new MapDto();

        int i = courseMapper.updateCourseClassHour(courseClassHour);
        if (i > 0) {
            // 更新课程时间
            courseMapper.updateCourseMoifyTime(courseClassHour.getCourseId());
            dto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "修改课时:" + courseClassHour.getClassHourName(), "课时管理");
        } else {
            dto.errorMsg("数据操作失败");
        }
        return dto;
    }

    @Override
    public MapDto addCourseClassHour(CourseClassHour courseClassHour) {
        MapDto dto = new MapDto();

        int i = courseMapper.addCourseClassHour(courseClassHour);
        if (i > 0) {
            // 更新课程时间
            courseMapper.updateCourseMoifyTime(courseClassHour.getCourseId());
            dto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "添加课时:" + courseClassHour.getClassHourName(), "课时管理");
        } else {
            dto.errorMsg("数据操作失败");
        }
        return dto;
    }

    @Override
    public MapDto deleteCourseClassHourById(Long id) {

        MapDto dto = new MapDto();
        int i = courseMapper.deleteCourseClassHourById(id);
        if (i > 0) {
            dto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null, "删除课时",
                    "课时管理");
        } else {
            dto.setStatus(1);
            dto.setMsg("数据操作失败");
        }
        return dto;
    }

    @Override
    public List<Course> getAllCourseList() {

        // Auto-generated method stub
        return courseMapper.getAllCourseList();
    }
}
