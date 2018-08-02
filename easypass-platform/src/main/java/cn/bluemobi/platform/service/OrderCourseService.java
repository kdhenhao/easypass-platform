/**
 * Project Name:easypass-platform
 * File Name:OrderCourseService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2017年6月1日下午3:48:47
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;

/**
 * Description: <br/>
 * Date: 2017年6月1日 下午3:48:47 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface OrderCourseService {

    Page<Map<String, Object>> findOrderCourseList(PageCondition cond);

    Page<Map<String, Object>> findOrderCourseDetail(Integer start, Integer length, String orderNo);

    MapDto deleteOrderById(Long orderId, String orderNo);

    MapDto updateStatus(Long orderId, Integer orderStatus);

    MapDto updateFileStatus(Long orderId, Integer status);

}
