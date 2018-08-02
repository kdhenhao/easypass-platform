/**
 * Project Name:lmExpress-platform
 * File Name:PointOrder.java
 * Package Name:cn.bluemobi.platform.controller.endpoint
 * Date:2016年11月9日下午4:43:58
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.endpoint;

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
import cn.bluemobi.platform.service.EndpointService;
import cn.bluemobi.platform.service.OrderService;

/**
 * Description: <br/>
 * Date: 2016年11月9日 下午4:43:58 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/pointOrder")
public class PointOrder extends PlatformBaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EndpointService endpointService;

    @RequestMapping("/toOrderPage")
    public String toOrderPage(Model model) {
        model.addAttribute("endpoints", endpointService.findPointsForSelect());
        return "endpoint/orderList";
    }

    @ResponseBody
    @RequestMapping("/findOrders")
    public DatatablePage findOrders(PageCondition cond) {
        return new DatatablePage(cond, orderService.findOrders(cond));
    }

    @ResponseBody
    @RequestMapping("/markPaied")
    public MapDto markPaied(@RequestParam("id") String id) {
        return orderService.markPaied(id, getUser());
    }

    @ResponseBody
    @RequestMapping("/markUnPaied")
    public MapDto markUnPaied(@RequestParam("id") String id) {
        return orderService.markUnPaied(id, getUser());
    }
}
