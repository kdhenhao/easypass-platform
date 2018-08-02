/**
 * Project Name:lmExpress-platform
 * File Name:FlightServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年11月1日下午5:27:43
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
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.FlightMapper;
import cn.bluemobi.platform.service.FlightService;

/**
 * Description: <br/>
 * Date: 2016年11月1日 下午5:27:43 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightMapper flightMapper;

    @Override
    public Page<Map<String, Object>> findFlights(PageCondition cond) {
        Page<Map<String, Object>> page = PageUtils.startPage(cond);
        page.setList(flightMapper.findFlights(cond.getMap()));
        return page;
    }

    @Override
    public MapDto saveOrUpdate(Long id, String flightNo, Long portId, String flightDate, User user) {
        if (id == null) {
            flightMapper.insertFlight(flightNo, flightDate, portId, user.getId());
        } else {
            flightMapper.updateFlight(flightNo, flightDate, portId, user.getId(), id);
        }
        return new MapDto();
    }

    @Override
    public MapDto updateStatus(Long id, String flightStatus, User user) {
        flightMapper.updateStatus(flightStatus, id, user.getId());
        return new MapDto();
    }

    @Override
    public Map<String, Object> findForSelect2(String q) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("incomplete_results", false);
        List<Map<String, Object>> list = flightMapper.findForSelect2(q);
        map.put("total_count", list.size());
        map.put("items", list);
        return map;
    }
}
