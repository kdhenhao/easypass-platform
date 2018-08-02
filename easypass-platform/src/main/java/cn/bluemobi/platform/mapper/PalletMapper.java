/**
 * Project Name:lmExpress-platform
 * File Name:PalletMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2016年11月14日上午11:04:39
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2016年11月14日 上午11:04:39 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface PalletMapper {

    void insert(Map<String, Object> pallet);

    void updateOrderStatusOrderConfiged(Map<String, Object> pallet);

    List<Map<String, Object>> findPallets(Map<String, Object> params);

    void updatePalletStatus(String status, Long id, Long id2);

    void updateOrderStatus(String status, Long id, Long id2);

    void updateFlight(String flightId, Long id, Long id2);

    void deleteById(Long id);

    void releaseOrder(Long id);

    int hasOrder(String orderNo, Long id);

    void addOrder(String orderNo, Long id, Long id2);

    void releaseOrderByOrderId(Long id);

    String findNameById(Long palletId);

    List<Map<String, Object>> findItemsByPalletId(Long palletId);

    List<Map<String, Object>> findDeclarationByPalletId(Long palletId);

    void updateOrderStatusOrderConfigedOneByOne(Map<String, Object> pallet);

    List<Long> findOrderIds(Long palletId);

}
