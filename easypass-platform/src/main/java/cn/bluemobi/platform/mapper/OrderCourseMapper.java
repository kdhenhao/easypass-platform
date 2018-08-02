/**
 * Project Name:easypass-platform
 * File Name:OrderCourseMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年6月1日下午3:57:57
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2017年6月1日 下午3:57:57 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface OrderCourseMapper {

    List<Map<String, Object>> findOrderCourseList(Map<String, Object> map);

    List<Map<String, Object>> findOrderCourseDetail(Map<String, Object> map);

    int deleteOrderById(Long orderId);

    /**
     * 
     * Description: 根据订单编号删除订单明细<br/>
     *
     * @author oscarwang
     * @param orderId
     * @return
     */
    int deleteOrderDetail(String orderNo);

    int updateStatus(Long orderId, Integer orderStatus);

    int updateFileStatus(Long orderId, Integer orderStatus);

}
