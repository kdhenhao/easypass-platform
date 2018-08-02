/**
 * Project Name:easypass-platform Maven Webapp
 * File Name:VideoClickController.java
 * Package Name:cn.bluemobi.platform.controller.report
 * Date:2017年8月3日下午1:19:15
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bluemobi.platform.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.service.VideoClickService;

/**
 * Description: <br/>
 * Date: 2017年8月3日 下午1:19:15 <br/>
 * 
 * @author Administrator
 * @version
 * @see
 */
@RequestMapping("/admin/report")
@Controller
public class VideoClickController extends PlatformBaseController {

    @Autowired
    private VideoClickService videoClickService;

    @RequestMapping("/toVideoClickPage")
    public String toVideoClickPage(Model model) {

        return "report/videoClick";
    }

    @ResponseBody
    @RequestMapping("/findVideoClicks")
    public DatatablePage findVideoClicks(PageCondition cond) {
        return new DatatablePage(cond, videoClickService.findVideoClicks(cond));
    }

}
