/**
 * Project Name:lmExpress-platform
 * File Name:OrderWeightController.java
 * Package Name:cn.bluemobi.platform.controller.order
 * Date:2016年11月11日上午11:01:50
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.Dto;
import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.entity.order.OrderMain;
import cn.bluemobi.platform.service.OrderService;

/**
 * Description: <br/>
 * Date: 2016年11月11日 上午11:01:50 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/orderWeight")
public class OrderWeightController extends PlatformBaseController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/toWeightPage")
    public String toWeightPage() {
        return "order/orderWeight";
    }

    @RequestMapping("/findByOrderNo")
    @ResponseBody
    public Dto<OrderMain> findByOrderNo(@RequestParam("orderNo") String orderNo) {
        return orderService.findByOrderNo(orderNo);
    }

    @RequestMapping("/updateWeight")
    @ResponseBody
    public MapDto updateWeight(@RequestParam("id") Long id, @RequestParam("totalWeight") Double totalWeight) {
        return orderService.updateWeight(id, totalWeight, getUser());
    }
}
