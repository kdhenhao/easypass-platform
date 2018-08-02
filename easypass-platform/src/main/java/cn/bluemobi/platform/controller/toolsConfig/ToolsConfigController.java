/**
 * Project Name:easypass-platform
 * File Name:ToolsConfigController.java
 * Package Name:cn.bluemobi.platform.controller.toolsConfig
 * date: 2017年5月23日 下午3:16:42 <br/>
 * Copyright (c) 2017, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.controller.toolsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.toolsConfig.AppAboutUs;
import cn.bluemobi.platform.entity.toolsConfig.ToolsConfig;
import cn.bluemobi.platform.service.ToolsConfigService;

/**
 * 
 * Description: 配置管理<br/>
 * date: 2017年5月23日 下午3:16:42 <br/>
 *
 * @author oscarwang
 * @version
 */
@Controller
@RequestMapping("admin/toolsConfig")
public class ToolsConfigController extends PlatformBaseController {

    @Autowired
    private ToolsConfigService toolsConfigService;

    @RequestMapping("/toolsConfigList")
    public String toolsConfigList() {
        return "toolsConfig/toolsConfigList";
    }

    /**
     * 查询所有
     */
    @RequestMapping("/find")
    @ResponseBody
    public DatatablePage getToolsConfigList(@RequestParam("draw") Integer draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length, ToolsConfig toolsConfig) {
        return new DatatablePage(draw, toolsConfigService.getToolsConfigByPage(start, length, toolsConfig, getUser()));
    }

    /**
     * 跳转到编辑页面
     */
    @RequestMapping("/toEditPage")
    public String toEditPage(@RequestParam("id") String id, Model model, ToolsConfig bro) {
        model.addAttribute("toolsConfig", toolsConfigService.findConfigById(id));
        return "toolsConfig/toolsConfigEdit";
    }

    /**
     * 跳转到关于我们编辑页面
     */
    @RequestMapping("/toEditConfigPage")
    public String toEditConfigPage(@RequestParam("configType") String configType, Model model) {
        model.addAttribute("appAboutUs", toolsConfigService.getAboutUs(configType));
        if (configType.equals("aboutUs")) {
            return "toolsConfig/aboutUsEdit";
        } else {
            // model.addAttribute("contactUs",toolsConfigService.getContactUs(configType));
            return "toolsConfig/contactUsEdit";
        } 

    }

    @RequestMapping("/updateAboutUs")
    public String updateAboutUs(AppAboutUs appAboutUs, @RequestParam("dir") String dir,
            @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2) {
        toolsConfigService.updateAboutUs(appAboutUs, dir, file1, file2);
        return "redirect:/admin/toolsConfig/toolsConfigList";
    }

    @RequestMapping("/updateConfig")
    @ResponseBody
    public MapDto updateConfig(ToolsConfig bro) {
        return toolsConfigService.updateConfig(bro);
    }

}
