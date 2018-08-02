/**
 * Project Name:cwy-platform
 * File Name:BrochureController.java
 * Package Name:cn.bluemobi.platform.controller.tools
 * Date:2016年10月27日下午4:10:51
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.tools.Brochure;
import cn.bluemobi.platform.service.BrochureService;

/**
 * Description: <br/>
 * Date: 2016年10月27日 下午4:10:51 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/brochure")
public class BrochureController extends PlatformBaseController {

    @Autowired
    private BrochureService brochureService;

    @RequestMapping("/toBrochurePage")
    public String toBrochurePage() {
        return "tools/brochureList";
    }

    @ResponseBody
    @RequestMapping("/findBrochures")
    public DatatablePage findBrochures(@RequestParam("draw") int draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length) {
        return new DatatablePage(draw, brochureService.findBrochures(start, length));
    }

    @RequestMapping("/toEditPage")
    public String toEditPage(@RequestParam("id") String id, Model model) {
        model.addAttribute("brochure", brochureService.findBrochureById(id));
        return "tools/brochureEdit";
    }

    @RequestMapping("/updateBrochure")
    @ResponseBody
    public MapDto updateBrochure(Brochure bro) {
        return brochureService.updateBrochure(bro, getUser());
    }
}
