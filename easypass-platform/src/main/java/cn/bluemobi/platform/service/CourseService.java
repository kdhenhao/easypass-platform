/**
 * Project Name:easypass-platform
 * File Name:CourseService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2017年5月24日下午3:45:05
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.course.Course;
import cn.bluemobi.platform.entity.course.CourseClassHour;
import cn.bluemobi.platform.vo.CourseClassHourVO;
import cn.bluemobi.platform.vo.CourseVO;

/**
 * Description: 课程模块<br/>
 * Date: 2017年5月24日 下午3:45:05 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface CourseService {

    /**
     * 
     * Description: 课程<br/>
     *
     * @author oscarwang
     * @param start
     * @param length
     * @param course
     * @return
     */
    Page<CourseVO> getCourseList(Integer start, Integer length, CourseVO course);

    Course getCourseById(Long id);

    MapDto updateCourse(Course course, String dir, MultipartFile file1, MultipartFile file2);

    MapDto addCourse(Course course, String dir, MultipartFile file1, MultipartFile file2);

    MapDto deleteCourseById(Long id);

    /**
     * 
     * Description: 课时<br/>
     *
     * @author oscarwang
     * @param start
     * @param length
     * @param course
     * @return
     */
    Page<CourseClassHourVO> getCourseClassHourList(Integer start, Integer length, CourseClassHourVO courseClassHour);

    CourseClassHour getCourseClassHourById(Long id);

    MapDto updateCourseClassHour(CourseClassHour courseClassHour);

    MapDto addCourseClassHour(CourseClassHour courseClassHour);

    MapDto deleteCourseClassHourById(Long id);

    /**
     * 
     * Description: 获取所有课程<br/>
     *
     * @author oscarwang
     * @return
     */
    List<Course> getAllCourseList();

}
