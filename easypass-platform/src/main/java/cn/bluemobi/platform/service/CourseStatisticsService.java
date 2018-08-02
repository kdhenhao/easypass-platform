package cn.bluemobi.platform.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;

/**
 * Description: 课程课时订单统计<br/>
 * Date: 2017年5月24日 下午3:45:05 <br/>
 * 
 * @author zhangcong
 * @version
 * @see
 */
public interface CourseStatisticsService {
    Page<Map<String, Object>> findCourseStatistics(PageCondition cond);

    List<Map<String, Object>> findCourseForSelect();

    List<Map<String, Object>> findCourseHourNameForSelect(Integer courseId);
    
    BigDecimal findTotal(Map<String, Object> params);
    
    
}
