/**
 * Project Name:lmExpress-platform
 * File Name:EndpointService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年11月4日下午2:03:35
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.List;
import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.endpoint.Endpoint;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2016年11月4日 下午2:03:35 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface EndpointService {

    Page<Map<String, Object>> findPoints(Integer start, Integer length);

    MapDto startById(Long id, User user);

    MapDto stopById(Long id, User user);

    MapDto saveOrUpdate(Endpoint end, User user);

    List<Map<String, Object>> findPointsForSelect();

}
