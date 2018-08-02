/**
 * Project Name:easypass-platform
 * File Name:MessagePushController.java
 * Package Name:cn.bluemobi.platform.controller.message
 * Date:2017年6月26日下午3:32:29
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.message.MessagePush;
import cn.bluemobi.platform.service.MessagePushService;

/**
 * Description: <br/>
 * Date: 2017年6月26日 下午3:32:29 <br/>
 * 
 * @author wuya
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/message")
public class MessagePushController extends PlatformBaseController {

    @Autowired
    private MessagePushService msgService;

    @RequestMapping("/toMessagePushPage")
    public String toMessagePushPage() {
        return "message/messageList";
    }

    @RequestMapping("/findPushes")
    @ResponseBody
    public DatatablePage findPushes(PageCondition cond) {
        return new DatatablePage(cond, msgService.findPushes(cond, cond.getMap()));
    }

    @ResponseBody
    @RequestMapping("/addPush")
    public MapDto addPush(MessagePush push) {

        return msgService.saveMsgPush(push);
    }

    @ResponseBody
    @RequestMapping("/updatePush")
    public MapDto updatePush(MessagePush push) {

        return msgService.updateMsgPush(push);
    }

    @ResponseBody
    @RequestMapping("/deleteMsgById")
    public MapDto deleteMsgById(Integer id) {

        return msgService.deleteMsgPush(id);
    }

    /**
     * 
     * Description: 所有用户推送<br/>
     *
     * @author oscarwang
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePushStatus")
    public MapDto updatePushStatus(Integer id) {

        return msgService.updatePushStatus(id);
    }
}
