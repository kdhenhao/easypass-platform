/**
 * Project Name:easypass-platform
 * File Name:CourseController.java
 * Package Name:cn.bluemobi.platform.controller.course
 * Date:2017年5月23日下午10:39:18
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.appCampus.AppCampus;
import cn.bluemobi.platform.entity.course.Classify;
import cn.bluemobi.platform.service.AppCampusService;
import cn.bluemobi.platform.service.CourseMajorClassifyService;

/**
 * Description: 课程专业分类<br/>
 * Date: 2017年5月23日 下午10:39:18 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Controller
@RequestMapping("admin/courseMajorClassify")
public class CourseMajorClassifyController extends PlatformBaseController {

    @Autowired
    private CourseMajorClassifyService courseService;
    
    @Autowired
    private AppCampusService appCampusService;

    @RequestMapping("/courseMajorClassifyList")
    public String areaManageList() {
        return "course/courseMajorClassifyList";
    }

    /**
     * 
     * Description: 查询所有一级分类<br/>
     *
     * @author oscarwang
     * @param draw
     * @param start
     * @param length
     * @param name
     * @return
     */
    @RequestMapping("/find")
    @ResponseBody
    public DatatablePage getCourseLevel1ByPage(Model model, @RequestParam("draw") Integer draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length, @RequestParam(value = "name", required = false) String name,
            @RequestParam("campusName") String campusName) {
    	List<AppCampus> campusList = appCampusService.getAllCampusList();
        model.addAttribute("campusList", campusList);
        return new DatatablePage(draw, courseService.getCourseClassifyByPage(start, length, name, campusName));
    }

    /**
     * 
     * Description: 删除<br/>
     *
     * @author oscarwang
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public MapDto deleteById(Long id) {
        return courseService.deleteById(id);
    }

    /**
     * 
     * Description: <br/>
     *
     * @author oscarwang
     * @param m
     * @param id
     * @return
     */
    @RequestMapping("/getClassifyById")
    public String getClassifyById(Model m, @RequestParam("id") Long id) {
        m.addAttribute("classify", courseService.getClassifyById(id));
        return "course/courseLevel1Detail";
    }

    /**
     * 
     * Description: 添加<br/>
     *
     * @author oscarwang
     * @param classify
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public MapDto add(Classify classify) {
        return courseService.add(classify);
    }

    /**
     * 
     * Description: 修改<br/>
     *
     * @author oscarwang
     * @param m
     * @param Classify
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public MapDto update(Classify classify) {
        return courseService.update(classify);
    }
}
