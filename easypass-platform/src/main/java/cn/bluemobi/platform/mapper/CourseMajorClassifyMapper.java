/**
 * Project Name:easypass-platform
 * File Name:CourseLevel1Mapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年5月23日下午11:14:44
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.course.Classify;

/**
 * Description: 课程一级分类<br/>
 * Date: 2017年5月23日 下午11:14:44 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface CourseMajorClassifyMapper {
    List<Classify> getAllClassifyList(Map<String, Object> map);

    Page<Classify> getCourseClassifyByPage(Integer start, Integer length, String name, String campusName);

    int deleteById(Long id);

    Classify detail(Long id);

    int add(Classify classify);

    String findCourseLevel1Name();

    int update(Classify classify);
}
