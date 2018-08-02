/**
 * Project Name:lmExpress-platform
 * File Name:ClientController.java
 * Package Name:cn.bluemobi.platform.controller.client
 * Date:2016年11月4日下午4:04:18
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.client;

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
import cn.bluemobi.platform.entity.client.Client;
import cn.bluemobi.platform.service.AreaService;
import cn.bluemobi.platform.service.ClientService;

/**
 * Description: <br/>
 * Date: 2016年11月4日 下午4:04:18 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@RequestMapping("/admin/client")
@Controller
public class ClientController extends PlatformBaseController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private ClientService clientService;

    @RequestMapping("/toClientPage")
    public String toClientPage(Model model) {
        model.addAttribute("provinces", areaService.findProvinces());
        return "client/clientList";
    }

    @ResponseBody
    @RequestMapping("/findClients")
    public DatatablePage findClients(PageCondition cond) {
        return new DatatablePage(cond, clientService.findClients(cond));
    }

    @ResponseBody
    @RequestMapping("/saveOrUpdateSender")
    public MapDto saveOrUpdateSender(Client client) {
        client.setRole("sender");
        return clientService.saveOrUpdate(client, getUser());
    }

    @ResponseBody
    @RequestMapping("/saveOrUpdateReceiver")
    public MapDto saveOrUpdateReceiver(Client client) {
        client.setRole("receiver");
        return clientService.saveOrUpdate(client, getUser());
    }

    @ResponseBody
    @RequestMapping("/deleteById")
    public MapDto deleteById(@RequestParam("id") Long id) {
        return clientService.deleteById(id, getUser());
    }
}
