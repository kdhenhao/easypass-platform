package cn.bluemobi.platform.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.mapper.CourseStatisticsMapper;
import cn.bluemobi.platform.service.CourseStatisticsService;

/**
 * Description: 课程课时订单统计<br/>
 * Date: 2017年8月3日 下午3:23:16 <br/>
 * 
 * @author zhangcong
 * @version
 * @see
 */
@Service
@Transactional
public class CourseStatisticsServiceImpl implements CourseStatisticsService {
    @Autowired
    private CourseStatisticsMapper courseStatisticsMapper;

    @Override
    public Page<Map<String, Object>> findCourseStatistics(PageCondition cond) {
        Page<Map<String, Object>> page = PageUtils.startPage(cond);
        // List<Map<String,Object>> list =
        // courseStatisticsMapper.findCourseStatistics(cond.getMap());

        page.setList(courseStatisticsMapper.findCourseStatistics(cond.getMap()));

        return page;
    }

    @Override
    public List<Map<String, Object>> findCourseForSelect() {
        return courseStatisticsMapper.findCourseForSelect();
    }

    @Override
    public List<Map<String, Object>> findCourseHourNameForSelect(Integer courseId) {
        return courseStatisticsMapper.findCourseHourNameForSelect(courseId);
    }

    @Override
    public BigDecimal findTotal(Map<String, Object> params) {

        return courseStatisticsMapper.findTotal(params);
    }

}
