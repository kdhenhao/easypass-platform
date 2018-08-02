/**
 * Project Name:easypass-platform
 * File Name:CourseLevel1ServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2017年5月23日下午11:20:30
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.course.Classify;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.CourseMajorClassifyMapper;
import cn.bluemobi.platform.service.CourseMajorClassifyService;
import cn.bluemobi.platform.service.LogService;

/**
 * Description: 课程专业分类<br/>
 * Date: 2017年5月23日 下午11:20:30 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Service
@Transactional
public class CourseMajorClassifyServiceImpl implements CourseMajorClassifyService {

    @Autowired
    private CourseMajorClassifyMapper courseLevel1Mapper;
    //添加日志
    @Autowired
    private LogService logService;

    @Autowired
    private HttpSession session;

    @Override
    public List<Classify> getAllClassifyList() {

        // Auto-generated method stub
        return courseLevel1Mapper.getAllClassifyList(null);
    }

    @Override
    public Page<Classify> getCourseClassifyByPage(Integer start, Integer length, String name, String campusName) {

        Page<Classify> page = PageUtils.startPage(start, length);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", name);
        map.put("campusName", campusName);
        List<Classify> roomList = courseLevel1Mapper.getAllClassifyList(map);

        page.setList(roomList);
        return page;
    }

    @Override
    public MapDto deleteById(Long id) {

        MapDto dto = new MapDto();
        int i = courseLevel1Mapper.deleteById(id);
        if (i > 0) {
            dto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null, "删除专业",
                    "专业管理");
        } else {
            dto.setStatus(1);
            dto.setMsg("数据操作失败");
        }
        return dto;
    }

    @Override
    public Classify getClassifyById(Long id) {

        // Auto-generated method stub
        return null;
    }

    @Override
    public MapDto add(Classify classify) {

        MapDto mapDto = new MapDto();
        int i = courseLevel1Mapper.add(classify);
        if (i > 0) {
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "添加专业:" + classify.getName(), "专业管理");
        } else {
            mapDto.setStatus(1);
        }
        return mapDto;
    }

    @Override
    public MapDto update(Classify classify) {

        MapDto mapDto = new MapDto();
        int i = 0;
        if (null != classify) {
            classify.setLevel(1);
        }
        if (classify.getId() != null) {
            i = courseLevel1Mapper.update(classify);
        } else {
            i = courseLevel1Mapper.add(classify);
        }

        if (i > 0) {
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "更新专业:" + classify.getName(), "专业管理");
        } else {
            mapDto.setStatus(1);
        }
        return mapDto;
    }

}
