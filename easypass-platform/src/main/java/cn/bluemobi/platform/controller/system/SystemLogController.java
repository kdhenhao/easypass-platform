/**
 * Project Name:banma
 * File Name:SystemLogController.java
 * Package Name:cn.bluemobi.banma.controller.backend.system
 * Date:2015年12月16日下午3:01:42
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.service.LogService;

/**
 * Description: <br/>
 * Date: 2015年12月16日 下午3:01:42 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/log")
public class SystemLogController extends PlatformBaseController {

    @Autowired
    private LogService logService;

    @RequestMapping("/toLogPage")
    public String toLogPage(Model model) {
        return "system/logList";
    }

    @RequestMapping("/findLogs")
    @ResponseBody
    public DatatablePage findLogs(@RequestParam("draw") int draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length, @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate, @RequestParam("username") String username,
            @RequestParam("optType") String optType) {
        return new DatatablePage(draw, logService.findLogsPage(start, length, startDate, endDate, username, optType));
    }
}
