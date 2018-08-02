/**
 * Project Name:easypass-platform
 * File Name:CourseMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年5月24日下午3:45:48
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.course.Course;
import cn.bluemobi.platform.entity.course.CourseClassHour;
import cn.bluemobi.platform.vo.CourseClassHourVO;
import cn.bluemobi.platform.vo.CourseVO;

/**
 * Description: <br/>
 * Date: 2017年5月24日 下午3:45:48 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface CourseMapper {

    /**
     * 
     * Description: 课程<br/>
     *
     * @author oscarwang
     * @param map
     * @return
     */
    List<CourseVO> getCourseList(Map<String, Object> map);

    Course getCourseById(Long id);

    int updateCourse(Course course);

    int addCourse(Course course);

    int deleteCourseById(Long id);

    /**
     * 
     * Description: 删除课程，相应删除课程下面的课时<br/>
     *
     * @author oscarwang
     * @param courseId
     * @return
     */
    int deleteCourseHourByCourseId(Long courseId);

    /**
     * 
     * Description: 课时<br/>
     *
     * @author oscarwang
     * @param map
     * @return
     */
    List<CourseClassHourVO> getCourseClassHourList(Map<String, Object> map);

    CourseClassHour getCourseClassHourById(Long id);

    int updateCourseClassHour(CourseClassHour courseClassHour);

    int addCourseClassHour(CourseClassHour courseClassHour);

    /**
     * 
     * Description: 更新课程时间<br/>
     *
     * @author oscarwang
     * @param courseId
     * @return
     */
    int updateCourseMoifyTime(Integer courseId);

    int deleteCourseClassHourById(Long id);

    List<Course> getAllCourseList();

}
