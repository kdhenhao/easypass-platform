
package cn.bluemobi.platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.platform.entity.order.OrderMain;
import cn.bluemobi.platform.mapper.OrderMapper;
import cn.bluemobi.platform.service.ImportService;

/**
 * Description: <br/>
 * Date: 2016年11月23日 上午11:00:57 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class ImportServiceImpl implements ImportService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Map<String, Object> importDeppon(Map<String, Object> map) {
        List<OrderMain> oms = orderMapper.findByOrderNo((String) map.get("orderNo"));
        if (oms.size() < 1) {
            map.put("success", 1);
            map.put("msg", "订单号（" + (String) map.get("orderNo") + "）在本系统不存在");
            return map;
        }
        // 更新数据库，建立关联
        OrderMain om = oms.get(0);
        map.put("receiverName", om.getReceiverName());
        map.put("palletName", om.getPalletName());
        map.put("id", om.getId());
        orderMapper.createRelDeppon(map);
        // 插入轨迹表
        orderMapper.createStep("快递已进入德邦物流系统，单号【" + (String) map.get("vasNo") + "】", om.getId());
        return map;
    }

    @Override
    public Map<String, Object> importYuantong(Map<String, Object> map) {
        List<OrderMain> oms = orderMapper.findByOrderNo((String) map.get("orderNo"));
        if (oms.size() < 1) {
            map.put("success", 1);
            map.put("msg", "订单号（" + (String) map.get("orderNo") + "）在本系统不存在");
            return map;
        }
        // 更新数据库，建立关联
        OrderMain om = oms.get(0);
        map.put("receiverName", om.getReceiverName());
        map.put("palletName", om.getPalletName());
        map.put("id", om.getId());
        orderMapper.createRelYuantong(map);
        // 插入轨迹表
        orderMapper.createStep("快递已进入圆通物流系统，单号【" + (String) map.get("vasNo") + "】", om.getId());
        return map;
    }

}
