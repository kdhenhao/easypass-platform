package cn.bluemobi.platform.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CourseStatisticsMapper {
    List<Map<String, Object>> findCourseStatistics(Map<String, Object> params);

    BigDecimal findTotal(Map<String, Object> params);

    List<Map<String, Object>> findCourseForSelect();

    List<Map<String, Object>> findCourseHourNameForSelect(Integer id);
    
    

}
