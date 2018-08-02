/**
 * Project Name:lmExpress-platform
 * File Name:FlightService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年11月1日下午5:27:34
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2016年11月1日 下午5:27:34 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface FlightService {

    Page<Map<String, Object>> findFlights(PageCondition cond);

    MapDto saveOrUpdate(Long id, String flightNo, Long portId, String flightDate, User user);

    MapDto updateStatus(Long id, String flightStatus, User user);

    Map<String, Object> findForSelect2(String q);

}
