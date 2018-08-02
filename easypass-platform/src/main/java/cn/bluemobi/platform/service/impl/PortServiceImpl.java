/**
 * Project Name:lmExpress-platform
 * File Name:PortServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年11月1日下午2:37:28
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
import cn.bluemobi.platform.entity.Option;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.PortMapper;
import cn.bluemobi.platform.service.PortService;

/**
 * Description: <br/>
 * Date: 2016年11月1日 下午2:37:28 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class PortServiceImpl implements PortService {

    @Autowired
    private PortMapper portMapper;

    @Override
    public Page<Map<String, Object>> findPorts(Integer start, Integer length) {
        Page<Map<String, Object>> page = PageUtils.startPage(start, length);
        page.setList(portMapper.findPorts());
        return page;
    }

    @Override
    public MapDto startById(Long id, User user) {
        portMapper.startById(id);
        return new MapDto();
    }

    @Override
    public MapDto stopById(Long id, User user) {
        portMapper.stopById(id);
        return new MapDto();
    }

    @Override
    public MapDto saveOrUpdate(Long id, String portName, User user) {
        MapDto dto = new MapDto();
        if (id == null) {
            if (portMapper.existPort(portName) > 0) {
                dto.errorMsg("该关口已经存在，不能重复添加");
            } else {
                portMapper.insertPort(portName, user.getId());
            }
        } else {
            if (portMapper.existPort2(portName, id) > 0) {
                dto.errorMsg("改名称修改后会和其他关口重复，无法修改");
            } else {
                portMapper.updatePort(portName, user.getId(), id);
            }
        }
        return dto;
    }

    @Override
    public List<Option> findPortsForSelect() {
        List<Option> list = portMapper.findPortsForSelect();
        return list;
    }

}
