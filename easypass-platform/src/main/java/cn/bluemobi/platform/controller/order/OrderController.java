/**
 * Project Name:lmExpress-platform
 * File Name:OrderController.java
 * Package Name:cn.bluemobi.platform.controller.order
 * Date:2016年11月8日下午2:16:02
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
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
import cn.bluemobi.platform.entity.order.OrderMain;
import cn.bluemobi.platform.service.AreaService;
import cn.bluemobi.platform.service.EndpointService;
import cn.bluemobi.platform.service.OrderService;
import cn.bluemobi.platform.service.TaxService;
import cn.bluemobi.platform.utils.CounterConstants;

/**
 * Description: <br/>
 * Date: 2016年11月8日 下午2:16:02 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/orderManage")
public class OrderController extends PlatformBaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private TaxService taxService;

    @Autowired
    private EndpointService endpointService;

    @RequestMapping("/toOrderPage")
    public String toOrderPage(Model model) {
        model.addAttribute("endpoints", endpointService.findPointsForSelect());
        return "order/orderList";
    }

    @ResponseBody
    @RequestMapping("/findOrders")
    public DatatablePage findOrders(PageCondition cond) {
        return new DatatablePage(cond, orderService.findOrders(cond));
    }

    @RequestMapping("/toEditPage")
    public String toEditPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("counters", CounterConstants.list);
        model.addAttribute("provinces", areaService.findProvinces());
        model.addAttribute("taxes", taxService.findTaxsForSelect());
        model.addAttribute("endpoints", endpointService.findPointsForSelect());
        OrderMain order = orderService.findById(id);
        model.addAttribute("citys", areaService.findByParent(order.getReceiverProvinceCode()));
        model.addAttribute("districts", areaService.findByParent(order.getReceiverCityCode()));
        model.addAttribute("order", order);
        model.addAttribute("commodities", orderService.findCommoditiesByOrderId(id));
        return "order/orderEdit";
    }

    @RequestMapping("/updateOrder")
    @ResponseBody
    public MapDto updateOrder(OrderMain order) {
        return orderService.updateOrder(order, getUser());
    }
}
