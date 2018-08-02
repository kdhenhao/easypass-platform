/**
 * Project Name:lmExpress-platform
 * File Name:PortController.java
 * Package Name:cn.bluemobi.platform.controller.flight
 * Date:2016年11月1日下午2:30:01
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.service.PortService;

/**
 * Description: <br/>
 * Date: 2016年11月1日 下午2:30:01 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/port")
public class PortController extends PlatformBaseController {

    @Autowired
    private PortService portService;

    @RequestMapping("/toPortPage")
    public String toPortPage() {
        return "flight/portList";
    }

    @ResponseBody
    @RequestMapping("/findPorts")
    public DatatablePage findPorts(@RequestParam("draw") int draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length) {
        return new DatatablePage(draw, portService.findPorts(start, length));
    }

    @RequestMapping("/startById")
    @ResponseBody
    public MapDto startById(@RequestParam("id") Long id) {
        return portService.startById(id, getUser());
    }

    @RequestMapping("/stopById")
    @ResponseBody
    public MapDto stopById(@RequestParam("id") Long id) {
        return portService.stopById(id, getUser());
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public MapDto saveOrUpdate(@RequestParam("id") Long id, @RequestParam("portName") String portName) {
        return portService.saveOrUpdate(id, portName, getUser());
    }
}
