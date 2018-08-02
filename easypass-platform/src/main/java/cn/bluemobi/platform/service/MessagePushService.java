/**
 * Project Name:easypass-platform
 * File Name:MessagePushService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2017年6月26日下午5:43:20
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.entity.message.MessagePush;

/**
 * Description: <br/>
 * Date: 2017年6月26日 下午5:43:20 <br/>
 * 
 * @author wuya
 * @version
 * @see
 */
public interface MessagePushService {

    MapDto saveMsgPush(MessagePush push);

    MapDto deleteMsgPush(Integer id);

    MapDto updateMsgPush(MessagePush push);

    Page<MessagePush> findPushes(PageCondition cond, Map<String, Object> map);

    /**
     * 
     * Description: 推送消息<br/>
     *
     * @author oscarwang
     * @param id
     * @return
     */
    MapDto updatePushStatus(Integer id);

}
