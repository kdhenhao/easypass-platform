/**
 * Project Name:easypass-platform
 * File Name:AppUserRecord.java
 * Package Name:cn.bluemobi.platform.controller.appUser
 * Date:2017年7月16日下午8:00:27
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.appUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.service.AppUserRecordService;

/**
 * Description: <br/>
 * Date: 2017年7月16日 下午8:00:27 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Controller
@RequestMapping("admin/appUserRecord")
public class AppUserRecordController extends PlatformBaseController {

    @Autowired
    private AppUserRecordService recordService;

    /**
     * 跳转用户列表页
     */
    @RequestMapping("/toAppUserRecordPage")
    public String toAppUserRecordPage(Model model) {

        return "appUser/appUserRecordList";
    }

    /**
     * 分页查询用户
     */
    @RequestMapping("/getAppUserRecordList")
    @ResponseBody
    public DatatablePage getAppUserList(@RequestParam("draw") int draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length,
            @RequestParam(value = "nickName", required = false) String nickName) {
        return new DatatablePage(draw, recordService.getAppUserRecordByPage(start, length, nickName));
    }
}
