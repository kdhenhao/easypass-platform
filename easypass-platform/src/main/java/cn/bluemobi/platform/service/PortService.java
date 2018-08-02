/**
 * Project Name:lmExpress-platform
 * File Name:PortService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年11月1日下午2:37:10
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.List;
import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.Option;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2016年11月1日 下午2:37:10 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface PortService {

    Page<Map<String, Object>> findPorts(Integer start, Integer length);

    MapDto startById(Long id, User user);

    MapDto stopById(Long id, User user);

    MapDto saveOrUpdate(Long id, String portName, User user);

    List<Option> findPortsForSelect();

}
