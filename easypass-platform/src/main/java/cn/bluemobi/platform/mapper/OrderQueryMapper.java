/**
 * Project Name:easypass-platform Maven Webapp
 * File Name:OrderQueryMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年8月3日下午3:19:21
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bluemobi.platform.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2017年8月3日 下午3:19:21 <br/>
 * 
 * @author zhangcong
 * @version
 * @see
 */
public interface OrderQueryMapper {
    List<Map<String, Object>> findOrderQuery(Map<String, Object> params);

    BigDecimal findTotalPrice(Map<String, Object> params);

}
