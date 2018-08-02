/**
 * Project Name:lmExpress-platform
 * File Name:ClientService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年11月7日上午11:40:00
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.entity.client.Client;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2016年11月7日 上午11:40:00 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface ClientService {

    Page<Map<String, Object>> findClients(PageCondition cond);

    MapDto saveOrUpdate(Client client, User user);

    MapDto deleteById(Long id, User user);

    Map<String, Object> findSendersForSelect2(String q);

    Map<String, Object> findReceiverForSelect2(String q);

    Map<String, Object> findClientById(Long id);

}
