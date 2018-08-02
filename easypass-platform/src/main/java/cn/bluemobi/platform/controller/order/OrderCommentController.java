/**
 * Project Name:easypass-platform
 * File Name:OrderCommentController.java
 * Package Name:cn.bluemobi.platform.controller.order
 * Date:2017年6月2日下午12:02:57
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.service.OrderCommentService;

/**
 * Description: 订单评论<br/>
 * Date: 2017年6月2日 下午12:02:57 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/orderComment")
public class OrderCommentController extends PlatformBaseController {

    @Autowired
    private OrderCommentService orderCommentService;

    @RequestMapping("/toOrderCommentPage")
    public String toOrderCommentPage() {
        return "order/orderCommentList";
    }

    /**
     * 获取列表
     */
    @RequestMapping("/findOrderCommentList")
    @ResponseBody
    public DatatablePage findOrderCommentList(PageCondition cond) {
    	
        return new DatatablePage(cond, orderCommentService.findOrderCommentList(cond));
    }

    @RequestMapping("/deleteOrderCommentById")
    @ResponseBody
    public MapDto deleteOrderCommentById(@RequestParam("id") Long id) {
        return orderCommentService.deleteOrderCommentById(id);
    }
}
