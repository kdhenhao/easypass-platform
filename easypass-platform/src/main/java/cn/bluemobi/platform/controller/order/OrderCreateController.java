/**
 * Project Name:lmExpress-platform
 * File Name:OrderCreateController.java
 * Package Name:cn.bluemobi.platform.controller.order
 * Date:2016年11月8日下午2:15:45
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.order;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.client.Client;
import cn.bluemobi.platform.entity.commodity.Commodity;
import cn.bluemobi.platform.entity.order.OrderMain;
import cn.bluemobi.platform.service.AreaService;
import cn.bluemobi.platform.service.ClientService;
import cn.bluemobi.platform.service.CommodityService;
import cn.bluemobi.platform.service.EndpointService;
import cn.bluemobi.platform.service.OrderService;
import cn.bluemobi.platform.service.TaxService;
import cn.bluemobi.platform.utils.CounterConstants;

/**
 * Description: <br/>
 * Date: 2016年11月8日 下午2:15:45 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/orderCreate")
public class OrderCreateController extends PlatformBaseController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private TaxService taxService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private EndpointService endpointService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/toCreatePage")
    public String toCreatePage(Model model) {
        model.addAttribute("counters", CounterConstants.list);
        model.addAttribute("provinces", areaService.findProvinces());
        model.addAttribute("taxes", taxService.findTaxsForSelect());
        model.addAttribute("endpoints", endpointService.findPointsForSelect());
        return "order/orderAdd";
    }

    @RequestMapping("/findSenders")
    @ResponseBody
    public Map<String, Object> findSenders(@RequestParam("q") String q) {
        return clientService.findSendersForSelect2(q);
    }

    @RequestMapping("/findReceivers")
    @ResponseBody
    public Map<String, Object> findReceivers(@RequestParam("q") String q) {
        return clientService.findReceiverForSelect2(q);
    }

    @RequestMapping("/findClientById")
    @ResponseBody
    public Map<String, Object> findClientById(@RequestParam("id") Long id) {
        return clientService.findClientById(id);
    }

    /**
     * 同ClientController
     */
    @ResponseBody
    @RequestMapping("/saveOrUpdateSender")
    public MapDto saveOrUpdateSender(Client client) {
        client.setRole("sender");
        return clientService.saveOrUpdate(client, getUser());
    }

    /**
     * 同ClientController
     */
    @ResponseBody
    @RequestMapping("/saveOrUpdateReceiver")
    public MapDto saveOrUpdateReceiver(Client client) {
        client.setRole("receiver");
        return clientService.saveOrUpdate(client, getUser());
    }

    @ResponseBody
    @RequestMapping("/findCommodities")
    public DatatablePage findCommodities(@RequestParam("draw") int draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length, @RequestParam("taxNo") String taxNo,
            @RequestParam("cnName") String cnName, @RequestParam("barcode") String barcode,
            @RequestParam("brand") String brand) {
        return new DatatablePage(draw,
                commodityService.findCommoditiesOpen(start, length, taxNo, cnName, barcode, brand));
    }

    @RequestMapping("/saveOrUpdateCommodity")
    @ResponseBody
    public MapDto saveOrUpdateCommodity(Commodity comm) {
        return commodityService.saveOrUpdate(comm, getUser());
    }

    @RequestMapping("/createOrder")
    @ResponseBody
    public MapDto createOrder(OrderMain order) {
        return orderService.createOrder(order, getUser());
    }
}
