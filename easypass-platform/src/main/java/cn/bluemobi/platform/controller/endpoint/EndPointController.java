/**
 * Project Name:lmExpress-platform
 * File Name:EndPointController.java
 * Package Name:cn.bluemobi.platform.controller.endpoint
 * Date:2016年11月4日下午2:00:25
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.endpoint.Endpoint;
import cn.bluemobi.platform.service.EndpointService;

/**
 * Description: <br/>
 * Date: 2016年11月4日 下午2:00:25 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@RequestMapping("/admin/endpoint")
@Controller
public class EndPointController extends PlatformBaseController {

    @Autowired
    private EndpointService endpointService;

    @RequestMapping("/toEndpointPage")
    public String toEndpointPage() {
        return "endpoint/endpointList";
    }

    @ResponseBody
    @RequestMapping("/findPoints")
    public DatatablePage findPoints(@RequestParam("draw") int draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length) {
        return new DatatablePage(draw, endpointService.findPoints(start, length));
    }

    @RequestMapping("/startById")
    @ResponseBody
    public MapDto startById(@RequestParam("id") Long id) {
        return endpointService.startById(id, getUser());
    }

    @RequestMapping("/stopById")
    @ResponseBody
    public MapDto stopById(@RequestParam("id") Long id) {
        return endpointService.stopById(id, getUser());
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public MapDto saveOrUpdate(Endpoint end) {
        return endpointService.saveOrUpdate(end, getUser());
    }
}
