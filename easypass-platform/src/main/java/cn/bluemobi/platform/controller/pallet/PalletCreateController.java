/**
 * Project Name:lmExpress-platform
 * File Name:PalletCreateController.java
 * Package Name:cn.bluemobi.platform.controller.pallet
 * Date:2016年11月11日下午2:51:21
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.pallet;

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
import cn.bluemobi.platform.service.PalletService;

/**
 * Description: <br/>
 * Date: 2016年11月11日 下午2:51:21 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@RequestMapping("/admin/palletCreate")
@Controller
public class PalletCreateController extends PlatformBaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PalletService palletService;

    @RequestMapping("/toCreatePage")
    public String toCreatePage() {
        return "pallet/palletCreate";
    }

    @RequestMapping("/findByOrderNo")
    @ResponseBody
    public Dto<OrderMain> findByOrderNo(@RequestParam("orderNo") String orderNo) {
        return orderService.findByOrderNo(orderNo);
    }

    @RequestMapping("/createPallet")
    @ResponseBody
    public MapDto createPallet(@RequestParam("ids") String ids) {
        return palletService.createPallet(ids, getUser());
    }
}
