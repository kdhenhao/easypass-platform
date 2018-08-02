package cn.bluemobi.platform.controller.report;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.service.CourseStatisticsService;

@RequestMapping("admin/CourseStatistics")
@Controller
public class CourseStatisticsController extends PlatformBaseController {
    @Autowired
    private CourseStatisticsService courseStatisticsService;

    @RequestMapping("/toCourseStatisticsPage")
    public String toCourseStatisticsPage(Model model) {

        model.addAttribute("course", courseStatisticsService.findCourseForSelect());
        
        return "report/courseStatistics";
    }

    @ResponseBody
    @RequestMapping("/findCourseStatistics")
    public DatatablePage findCourseStatistics(PageCondition cond, String beginTime, String endTime) {
    	cond.getMap().put("beginTime", beginTime);
        cond.getMap().put("endTime", endTime);
        
        return new DatatablePage(cond, courseStatisticsService.findCourseStatistics(cond));
    }

    @ResponseBody
    @RequestMapping("/findCourseHourNameForSelect")
    public List<Map<String, Object>> findCourseHourNameForSelect(Integer courseId) {
        return courseStatisticsService.findCourseHourNameForSelect(courseId);
    }
    
    @ResponseBody
    @RequestMapping("/findTotalPrice")
    BigDecimal findTotalPrice(Map<String, Object> map, String beginTime, String endTime, String orderNo, String courseFlag, String courseName, String courseHourName) {
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("orderNo", orderNo);
        map.put("courseFlag", courseFlag);
        map.put("courseName", courseName);
        map.put("courseHourName", courseHourName);
        
        return courseStatisticsService.findTotal(map);
    }
    
   

}
