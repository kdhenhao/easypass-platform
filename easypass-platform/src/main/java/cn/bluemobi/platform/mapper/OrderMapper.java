/**
 * Project Name:lmExpress-platform
 * File Name:OrderMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2016年11月9日下午2:45:42
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
 * Date: 2016年11月9日 下午2:45:42 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface OrderMapper {

    void insertNewOrder(OrderMain order);

    void insertOrderCommodity(Map<String, Object> commodity);

    List<Map<String, Object>> findOrders(Map<String, Object> params);

    void markPaied(Map<String, Object> params);

    void markUnPaied(Map<String, Object> params);

    void updateOrderPic(String idcardNo, String fontsidePic, String backsidePic, String mergesidePic, Long id);

    OrderMain findById(Long id);

    List<Map<String, Object>> findCommoditiesByOrderId(Long id);

    void updateById(OrderMain order);

    void deleteCommodity(Map<String, Object> params);

    void updateCommodityCount(String string, String string2);

    List<OrderMain> findByOrderNo(String orderNo);

    void updateWeightById(Double totalWeight, Long id, Long id2);

    List<Map<String, Object>> findOrdersByPallet(Long id);

    int exist(String orderNo);

    void replaceIdCard(Client cli);

    List<OrderMain> findNoPicByPalletId(Long palletId);

    List<String> findNoPicPhone(Long palletId);

    List<OrderMain> findOrderPicByPalletId(Long palletId);

    void createRelDeppon(Map<String, Object> map);

    void createStep(String string, Long id);

    List<Long> findIdByPalletId(Long id);

    void createStepBatch(Map<String, Object> params);

    void createRelYuantong(Map<String, Object> map);

}
