/**
 * Project Name:lmExpress-platform
 * File Name:OrderService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年11月9日下午2:44:06
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.List;
import java.util.Map;

import cn.bluemobi.common.core.dto.Dto;
import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.entity.deppon.DepponRequest;
import cn.bluemobi.platform.entity.deppon.DepponResponse;
import cn.bluemobi.platform.entity.order.OrderMain;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2016年11月9日 下午2:44:06 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface OrderService {

    /**
     * 录单
     */
    MapDto createOrder(OrderMain order, User user);

    /**
     * 查询
     * 
     * @param endpointPay
     */
    Page<Map<String, Object>> findOrders(PageCondition cond);

    MapDto markPaied(String id, User user);

    MapDto markUnPaied(String id, User user);

    OrderMain findById(Long id);

    List<Map<String, Object>> findCommoditiesByOrderId(Long id);

    MapDto updateOrder(OrderMain order, User user);

    Dto<OrderMain> findByOrderNo(String orderNo);

    MapDto updateWeight(Long id, Double totalWeight, User user);

    /**
     * 收到德邦异步回调
     */
    void receiveDepponNotify(DepponRequest request, DepponResponse response);

}
