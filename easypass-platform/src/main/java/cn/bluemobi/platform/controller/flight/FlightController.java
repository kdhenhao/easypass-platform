/**
 * Project Name:lmExpress-platform
 * File Name:FlightController.java
 * Package Name:cn.bluemobi.platform.controller.flight
 * Date:2016年11月1日下午2:28:35
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.flight;

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
import cn.bluemobi.platform.service.FlightService;
import cn.bluemobi.platform.service.PortService;

/**
 * Description: <br/>
 * Date: 2016年11月1日 下午2:28:35 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@RequestMapping("/admin/flight")
@Controller
public class FlightController extends PlatformBaseController {

    @Autowired
    private PortService portService;

    @Autowired
    private FlightService flightService;

    @RequestMapping("/toFlightPage")
    public String toFlightPage(Model model) {
        model.addAttribute("ports", portService.findPortsForSelect());
        return "flight/flightList";
    }

    @ResponseBody
    @RequestMapping("/findFlights")
    public DatatablePage findFlights(PageCondition cond) {
        return new DatatablePage(cond, flightService.findFlights(cond));
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public MapDto saveOrUpdate(@RequestParam("id") Long id, @RequestParam("flightNo") String flightNo,
            @RequestParam("portId") Long portId, @RequestParam("flightDate") String flightDate) {
        return flightService.saveOrUpdate(id, flightNo, portId, flightDate, getUser());
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public MapDto updateStatus(@RequestParam("id") Long id, @RequestParam("flightStatus") String flightStatus) {
        return flightService.updateStatus(id, flightStatus, getUser());
    }
}
