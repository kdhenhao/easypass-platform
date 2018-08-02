/**
 * Project Name:easypass-platform
 * File Name:OrderCourseController.java
 * Package Name:cn.bluemobi.platform.controller.order
 * Date:2017年6月1日下午3:29:39
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.service.OrderCourseService;

/**
 * Description: 订单管理<br/>
 * Date: 2017年6月1日 下午3:29:39 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/orderCourse")
public class OrderCourseController extends PlatformBaseController {
    @Autowired
    private OrderCourseService orderCourseService;

    /**
     * 
     * Description: 跳转列表页面<br/>
     *
     * @author oscarwang
     * @param model
     * @return
     */
    @RequestMapping("/toOrderCoursePage")
    public String toOrderCoursePage(Model model) {
        return "order/orderCourseList";
    }

    /**
     * 
     * Description: 查询列表分页<br/>
     *
     * @author oscarwang
     * @param cond
     * @return
     */
    @RequestMapping("/findOrderCourseList")
    @ResponseBody
    public DatatablePage findOrderCourseList(PageCondition cond) {

        return new DatatablePage(cond, orderCourseService.findOrderCourseList(cond));
    }

    /**
     * 
     * Description: 单个订单详情<br/>
     *
     * @author oscarwang
     * @param cond
     * @return
     */
    @RequestMapping("/findOrderCourseDetail")
    @ResponseBody
    public DatatablePage findOrderCourseDetail(Integer draw, Integer start, Integer length, String orderNo) {
        return new DatatablePage(draw, orderCourseService.findOrderCourseDetail(start, length, orderNo));
    }

    @RequestMapping("/deleteOrderById")
    @ResponseBody
    public MapDto deleteOrderById(@RequestParam("orderId") Long orderId, @RequestParam("orderNo") String orderNo) {
        return orderCourseService.deleteOrderById(orderId, orderNo);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public MapDto updateStatus(@RequestParam("orderId") Long orderId,
            @RequestParam("orderStatus") Integer orderStatus) {
        return orderCourseService.updateStatus(orderId, orderStatus);
    }

    @RequestMapping("/updateFileStatus")
    @ResponseBody
    public MapDto updateFileStatus(@RequestParam("orderId") Long orderId, @RequestParam("status") Integer status) {
        return orderCourseService.updateFileStatus(orderId, status);
    }

}
