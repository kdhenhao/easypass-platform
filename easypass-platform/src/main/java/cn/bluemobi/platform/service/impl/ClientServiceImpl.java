/**
 * Project Name:lmExpress-platform
 * File Name:ClientServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年11月7日上午11:40:09
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.client.Client;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.ClientMapper;
import cn.bluemobi.platform.service.ClientService;

/**
 * Description: <br/>
 * Date: 2016年11月7日 上午11:40:09 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Page<Map<String, Object>> findClients(PageCondition cond) {
        Page<Map<String, Object>> page = PageUtils.startPage(cond);
        page.setList(clientMapper.findClients(cond.getMap()));
        return page;
    }

    @Override
    public MapDto saveOrUpdate(Client client, User user) {
        MapDto dto = new MapDto();
        if (client.getId() == null) {
            // 新增
            if (clientMapper.exists(client) > 0) {
                dto.errorMsg("该名称+手机号已经存在，不能重复添加");
            } else {
                client.setCreateBy(user.getId());
                clientMapper.insert(client);
                dto.putInData("id", client.getId());
                dto.putInData("clientName", client.getClientName());
            }
        } else {
            if (clientMapper.exists2(client) > 0) {
                dto.errorMsg("该名称+手机号已经存在，修改会导致重复");
            } else {
                client.setModifyBy(user.getId());
                clientMapper.update(client);
                dto.putInData("id", client.getId());
                dto.putInData("clientName", client.getClientName());
            }
        }
        return dto;
    }

    @Override
    public MapDto deleteById(Long id, User user) {
        clientMapper.deleteById(id);
        return new MapDto();
    }

    @Override
    public Map<String, Object> findSendersForSelect2(String q) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("incomplete_results", false);
        List<Map<String, Object>> list = clientMapper.findSendersForSelect2(q);
        map.put("total_count", list.size());
        map.put("items", list);
        return map;
    }

    @Override
    public Map<String, Object> findReceiverForSelect2(String q) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("incomplete_results", false);
        List<Map<String, Object>> list = clientMapper.findReceiverForSelect2(q);
        map.put("total_count", list.size());
        map.put("items", list);
        return map;
    }

    @Override
    public Map<String, Object> findClientById(Long id) {
        return clientMapper.findById(id);
    }
}
