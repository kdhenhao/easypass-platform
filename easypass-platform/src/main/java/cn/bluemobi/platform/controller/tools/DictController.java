/**
 * Project Name:lmExpress-platform
 * File Name:DictController.java
 * Package Name:cn.bluemobi.platform.controller.tools
 * Date:2016年11月19日下午2:04:59
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
import cn.bluemobi.platform.service.DictService;

/**
 * Description: <br/>
 * Date: 2016年11月19日 下午2:04:59 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/dict")
public class DictController extends PlatformBaseController {

    @Autowired
    private DictService dictService;

    @RequestMapping("/toDictPage")
    public String toDictPage(Model model) {
        model.addAllAttributes(dictService.findForWeb());
        model.addAllAttributes(dictService.findForDeppon());
        return "tools/dict";
    }

    @RequestMapping("/update")
    @ResponseBody
    public MapDto update(@RequestParam("webAddress") String webAddress, @RequestParam("webPhone") String webPhone,
            @RequestParam("webEmail") String webEmail, @RequestParam("webQrcode") String webQrcode,
            @RequestParam("webServiceTime") String webServiceTime, @RequestParam("webWechat") String webWechat,
            @RequestParam("webQQ") String webQQ) {
        return dictService.update(webAddress, webPhone, webEmail, webQrcode, webServiceTime, webWechat, webQQ,
                getUser());
    }

    @RequestMapping("/updateDeppon")
    @ResponseBody
    public MapDto updateDeppon(@RequestParam("depponCompany") String depponCompany,
            @RequestParam("depponSender") String depponSender, @RequestParam("depponPhone") String depponPhone,
            @RequestParam("depponAddress") String depponAddress) {
        return dictService.updateDeppon(depponCompany, depponSender, depponPhone, depponAddress, getUser());
    }

}
