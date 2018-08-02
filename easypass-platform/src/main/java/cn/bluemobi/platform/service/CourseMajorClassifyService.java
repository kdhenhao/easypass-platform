/**
 * Project Name:easypass-platform
 * File Name:CourseService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2017年5月23日下午10:47:21
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.List;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.course.Classify;

/**
 * Description: 课程<br/>
 * Date: 2017年5月23日 下午10:47:21 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface CourseMajorClassifyService {

    List<Classify> getAllClassifyList();

    Page<Classify> getCourseClassifyByPage(Integer start, Integer length, String name, String campusName);

    MapDto deleteById(Long id);

    Classify getClassifyById(Long id);

    MapDto add(Classify classify);

    MapDto update(Classify classify);

}
