/**
 * Project Name:lmExpress-platform
 * File Name:PalletService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年11月14日上午11:03:49
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.List;
import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.order.OrderMain;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2016年11月14日 上午11:03:49 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface PalletService {

    MapDto createPallet(String ids, User user);

    Page<Map<String, Object>> findPallets(Integer start, Integer length, String flightNo, String palletName,
            String status);

    MapDto updateStatus(Long id, String status, User user);

    MapDto updateFlight(Long id, String flightId, User user);

    List<Map<String, Object>> findOrdersByPallet(Long id);

    MapDto deleteById(Long id);

    MapDto addOrder(String orderNo, Long id, User user);

    MapDto deleteOrder(Long id, User user);

    MapDto replaceIdCard(Long orderId, User user);

    MapDto replaceAll(Long palletId);

    MapDto sendSmsAll(Long palletId);

    List<OrderMain> findOrderPicByPalletId(Long palletId);

    String findPalletName(Long palletId);

    List<Map<String, Object>> findItemsByPalletId(Long palletId);

    List<Map<String, Object>> findDeclarationByPalletId(Long palletId);

    int findClearNoSum();

    MapDto allocateClearNo(Long palletId);

    MapDto checkClearNo(Long palletId);

    List<Map<String, Object>> findGoodsByOrderId(Long long1);

}
