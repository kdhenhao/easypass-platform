/**
 * Project Name:easypass-platform
 * File Name:AppHelpController.java
 * Package Name:cn.bluemobi.platform.controller.appHelp
 * Date:2017年6月23日下午3:40:44
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.appHelp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.appHelp.AppHelp;
import cn.bluemobi.platform.service.AppHelpService;

/**
 * Description: <br/>
 * Date: 2017年6月23日 下午3:40:44 <br/>
 * 
 * @author wuya
 * @version
 * @see
 */
@Controller
@RequestMapping("admin/appHelp")
public class AppHelpController extends PlatformBaseController {

    @Autowired
    private AppHelpService helpService;

    @RequestMapping("/toHelpListPage")
    public String toHelpListPage() {

        return "help/appHelp";
    }

    @ResponseBody
    @RequestMapping("/getHelpList")
    public DatatablePage getHelpList(PageCondition cond, AppHelp help) {
        return new DatatablePage(cond, helpService.findHelpList(cond.getStart(), cond.getLength(), help));
    }

    @ResponseBody
    @RequestMapping("/addHelp")
    public MapDto addHelp(AppHelp help) {

        return helpService.addHelp(help);
    }

    @ResponseBody
    @RequestMapping("/updateHelp")
    public MapDto updateHelp(AppHelp help) {

        return helpService.updateHelp(help);
    }

    @ResponseBody
    @RequestMapping("/deleteHelpById")
    public MapDto deleteHelpById(Integer id) {

        return helpService.deleteHelpById(id);
    }

}
