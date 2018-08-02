/**
 * Project Name:lmExpress-platform
 * File Name:ClientMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2016年11月7日上午11:40:51
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.client.Client;
import cn.bluemobi.platform.entity.order.OrderMain;

/**
 * Description: <br/>
 * Date: 2016年11月7日 上午11:40:51 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface ClientMapper {

    List<Map<String, Object>> findClients(Map<String, Object> params);

    int exists(Client client);

    int exists2(Client client);

    void insert(Client client);

    void update(Client client);

    void deleteById(Long id);

    List<Map<String, Object>> findSendersForSelect2(String q);

    List<Map<String, Object>> findReceiverForSelect2(String q);

    Map<String, Object> findById(Long id);

    List<Client> findReceiverByNameAndPhone(OrderMain order);

    List<Client> findReceiverForCheat(Long palletId);

}
