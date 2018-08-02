/**
 * Project Name:easypass-platform Maven Webapp
 * File Name:OrderQueryServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2017年8月3日下午3:23:16
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.mapper.OrderQueryMapper;
import cn.bluemobi.platform.service.OrderQueryService;

/**
 * Description: 订单查询<br/>
 * Date: 2017年8月3日 下午3:23:16 <br/>
 * 
 * @author zhangcong
 * @version
 * @see
 */
@Service
@Transactional
public class OrderQueryServiceImpl implements OrderQueryService {
    @Autowired
    private OrderQueryMapper orderQueryMapper;

    @Override
    public Page<Map<String, Object>> findOrderQuerys(PageCondition cond) {
        Page<Map<String, Object>> page = PageUtils.startPage(cond);
        page.setList(orderQueryMapper.findOrderQuery(cond.getMap()));
        return page;
    }

    /**
     * 
     * 简单描述该方法的实现功能（可选）.获取表单所显示的总价格之和
     * 
     * @see cn.bluemobi.platform.service.OrderQueryService#findTotalPrice(java.util.Map)
     */
    @Override
    public BigDecimal findTotalPrice(Map<String, Object> map) {
        return orderQueryMapper.findTotalPrice(map);
    }

}
