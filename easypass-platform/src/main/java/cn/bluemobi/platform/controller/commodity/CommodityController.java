/**
 * Project Name:lmExpress-platform
 * File Name:CommodityController.java
 * Package Name:cn.bluemobi.platform.controller.commodity
 * Date:2016年11月2日下午4:16:15
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.commodity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.commodity.Commodity;
import cn.bluemobi.platform.service.CommodityService;
import cn.bluemobi.platform.service.TaxService;
import cn.bluemobi.platform.utils.CounterConstants;

/**
 * Description: <br/>
 * Date: 2016年11月2日 下午4:16:15 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/commodity")
public class CommodityController extends PlatformBaseController {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private TaxService taxService;

    @RequestMapping("/toCommodityPage")
    public String toCommodityPage(Model model) {
        model.addAttribute("counters", CounterConstants.list);
        model.addAttribute("taxes", taxService.findTaxsForSelect());
        return "commodity/commodityList";
    }

    @ResponseBody
    @RequestMapping("/findCommodities")
    public DatatablePage findCommodities(PageCondition cond) {
        return new DatatablePage(cond, commodityService.findCommodities(cond));
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public MapDto saveOrUpdate(Commodity comm) {
        return commodityService.saveOrUpdate(comm, getUser());
    }
}
