/**
 * Project Name:easypass-platform
 * File Name:MessagePushMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年6月26日下午4:54:47
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.message.MessagePush;

/**
 * Description: <br/>
 * Date: 2017年6月26日 下午4:54:47 <br/>
 * 
 * @author wuya
 * @version
 * @see
 */
public interface MessagePushMapper {

    int inertMsgPush(MessagePush push);

    int deleteMsgPush(Integer id);

    int updateMsgPush(MessagePush push);

    List<MessagePush> findPushes(Map<String, Object> map);

    int updatePushStatus(Integer id);

    MessagePush getMsgById(Integer id);

}
