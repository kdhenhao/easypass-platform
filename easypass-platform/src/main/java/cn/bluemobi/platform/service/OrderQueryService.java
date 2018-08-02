/**
 * Project Name:easypass-platform Maven Webapp
 * File Name:OrderQueryMapper.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2017年8月3日下午3:20:50
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service;

import java.math.BigDecimal;
import java.util.Map;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;

/**
 * Description:订单查询模块 <br/>
 * Date: 2017年8月3日 下午3:20:50 <br/>
 * 
 * @author zhangcong
 * @version
 * @see
 */
public interface OrderQueryService {
    Page<Map<String, Object>> findOrderQuerys(PageCondition cond);

    BigDecimal findTotalPrice(Map<String, Object> params);
}
