/**
 * Project Name:lmExpress-platform
 * File Name:AreaController.java
 * Package Name:cn.bluemobi.platform.controller.tools
 * Date:2016年11月7日下午2:59:47
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.tools;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.service.AreaService;

/**
 * Description: <br/>
 * Date: 2016年11月7日 下午2:59:47 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@RequestMapping("/admin/area")
@Controller
public class AreaController extends PlatformBaseController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("/findByParent")
    @ResponseBody
    public List<Map<String, Object>> findByParent(@RequestParam("parent") String parent) {
        return areaService.findByParent(parent);
    }
}
