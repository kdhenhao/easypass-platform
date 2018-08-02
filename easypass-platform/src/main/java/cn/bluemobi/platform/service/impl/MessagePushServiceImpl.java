/**
 * Project Name:easypass-platform
 * File Name:MessagePushServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2017年6月26日下午5:44:31
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.message.MessagePush;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.MessagePushMapper;
import cn.bluemobi.platform.service.LogService;
import cn.bluemobi.platform.service.MessagePushService;
import cn.bluemobi.platform.utils.PushExample;

/**
 * Description: <br/>
 * Date: 2017年6月26日 下午5:44:31 <br/>
 * 
 * @author wuya
 * @version
 * @see
 */
@Service
@Transactional
public class MessagePushServiceImpl implements MessagePushService {

    @Autowired
    private MessagePushMapper msgMapper;

    @Autowired
    private LogService logService;

    @Autowired
    private HttpSession session;

    @Override
    public MapDto saveMsgPush(MessagePush push) {
        MapDto mapDto = new MapDto();
        int i = msgMapper.inertMsgPush(push);
        if (i > 0) { // 插入成功
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "新增推送,标题:" + push.getMsgHeader(), "推送消息管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败");
        }

        return mapDto;
    }

    @Override
    public MapDto deleteMsgPush(Integer id) {
        MapDto mapDto = new MapDto();
        int i = msgMapper.deleteMsgPush(id);
        if (i > 0) { // 删除成功
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null, "删除推送",
                    "推送消息管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败");
        }

        return mapDto;
    }

    @Override
    public MapDto updateMsgPush(MessagePush push) {
        MapDto mapDto = new MapDto();
        int i = msgMapper.updateMsgPush(push);
        if (i > 0) { // 修改成功
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "更新推送,标题:" + push.getMsgHeader(), "推送消息管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败错");
        }

        return mapDto;
    }

    @Override
    public Page<MessagePush> findPushes(PageCondition cond, Map<String, Object> map) {
        Page<MessagePush> page = PageUtils.startPage(cond);
        page.setList(msgMapper.findPushes(map));
        return page;
    }

    @Override
    public MapDto updatePushStatus(Integer id) {

        MapDto mapDto = new MapDto();
        int i = msgMapper.updatePushStatus(id);
        if (i > 0) { // 推送成功
            MessagePush msg = msgMapper.getMsgById(id);
            // 开始推送
            PushExample.testSendPush(null, msg.getMsgHeader(), msg.getId(), 0);
            mapDto.setStatus(0);

            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "推送消息:" + msg.getMsgHeader(), "推送消息管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败错");
        }

        return mapDto;
    }

}
