/**
 * Project Name:lmExpress-platform
 * File Name:CommodityServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年11月3日下午3:42:36
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.commodity.Commodity;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.CommodityMapper;
import cn.bluemobi.platform.service.CommodityService;

/**
 * Description: <br/>
 * Date: 2016年11月3日 下午3:42:36 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public Page<Map<String, Object>> findCommodities(PageCondition cond) {
        Page<Map<String, Object>> page = PageUtils.startPage(cond);
        page.setList(commodityMapper.findCommodities(cond.getMap()));
        return page;
    }

    @Override
    public MapDto saveOrUpdate(Commodity comm, User user) {
        if (comm.getId() == null) {
            comm.setCreateBy(user.getId());
            commodityMapper.insert(comm);
        } else {
            comm.setModifyBy(user.getId());
            commodityMapper.update(comm);
        }
        return new MapDto();
    }

    @Override
    public Page<Map<String, Object>> findCommoditiesOpen(Integer start, Integer length, String taxNo, String cnName,
            String barcode, String brand) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("taxNo", taxNo);
        params.put("cnName", cnName);
        params.put("barcode", barcode);
        params.put("brand", brand);
        Page<Map<String, Object>> page = PageUtils.startPage(start, length);
        page.setList(commodityMapper.findCommodities(params));
        return page;
    }

    @Override
    public Map<String, Object> findById(String id) {
        return commodityMapper.findById(id);
    }
}
