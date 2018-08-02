/**
 * Project Name:lmExpress-platform
 * File Name:EndpointMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2016年11月4日下午2:04:37
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.endpoint.Endpoint;

/**
 * Description: <br/>
 * Date: 2016年11月4日 下午2:04:37 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface EndpointMapper {

    List<Map<String, Object>> findPoints();

    void startById(Long id, Long id2);

    void stopById(Long id, Long id2);

    void insert(Endpoint end);

    void update(Endpoint end);

    List<Map<String, Object>> findPointsForSelect();

}
