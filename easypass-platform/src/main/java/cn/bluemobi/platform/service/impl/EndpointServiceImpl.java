/**
 * Project Name:lmExpress-platform
 * File Name:EndpointServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年11月4日下午2:03:53
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.endpoint.Endpoint;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.EndpointMapper;
import cn.bluemobi.platform.service.EndpointService;

/**
 * Description: <br/>
 * Date: 2016年11月4日 下午2:03:53 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class EndpointServiceImpl implements EndpointService {

    @Autowired
    private EndpointMapper endpointMapper;

    @Override
    public Page<Map<String, Object>> findPoints(Integer start, Integer length) {
        Page<Map<String, Object>> page = PageUtils.startPage(start, length);
        page.setList(endpointMapper.findPoints());
        return page;
    }

    @Override
    public MapDto startById(Long id, User user) {
        endpointMapper.startById(id, user.getId());
        return new MapDto();
    }

    @Override
    public MapDto stopById(Long id, User user) {
        endpointMapper.stopById(id, user.getId());
        return new MapDto();
    }

    @Override
    public MapDto saveOrUpdate(Endpoint end, User user) {
        if (end.getId() == null) {
            end.setCreateBy(user.getId());
            endpointMapper.insert(end);
        } else {
            end.setModifyBy(user.getId());
            endpointMapper.update(end);
        }
        return new MapDto();
    }

    @Override
    public List<Map<String, Object>> findPointsForSelect() {
        return endpointMapper.findPointsForSelect();
    }

}
