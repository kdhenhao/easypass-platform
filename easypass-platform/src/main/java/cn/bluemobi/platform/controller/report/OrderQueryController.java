/**
 * Project Name:easypass-platform Maven Webapp
 * File Name:OrderQueryController.java
 * Package Name:cn.bluemobi.platform.controller.report
 * Date:2017年8月3日下午3:27:24
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bluemobi.platform.controller.report;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.service.OrderQueryService;

/**
 * Description: <br/>
 * Date: 2017年8月3日 下午3:27:24 <br/>
 * 
 * @author Administrator
 * @version
 * @see
 */
@RequestMapping("admin/orderQuery")
@Controller
public class OrderQueryController extends PlatformBaseController {
    @Autowired
    private OrderQueryService orderQueryService;

    @RequestMapping("/toOrderQueryPage")
    public String toOrderQueryPage(Model model) {

        return "report/orderQuery";
    }

    @ResponseBody
    @RequestMapping("/findOrderQuerys")
    public DatatablePage findOrderQuerys(PageCondition cond, String startTime, String endTime) {
        cond.getMap().put("startTime", startTime);
        cond.getMap().put("endTime", endTime);
        return new DatatablePage(cond, orderQueryService.findOrderQuerys(cond));
    }

    @ResponseBody
    @RequestMapping("/findTotalPrice")
    BigDecimal findTotalPrice(Map<String, Object> map, String startTime, String endTime, String orderNo) {
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("orderNo", orderNo);
        return orderQueryService.findTotalPrice(map);
    }
}
