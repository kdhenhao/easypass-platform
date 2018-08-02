/**
 * Project Name:lmExpress-platform
 * File Name:OrderServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年11月9日下午2:44:18
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.Dto;
import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.client.Client;
import cn.bluemobi.platform.entity.deppon.DepponRequest;
import cn.bluemobi.platform.entity.deppon.DepponResponse;
import cn.bluemobi.platform.entity.order.OrderMain;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.ClientMapper;
import cn.bluemobi.platform.mapper.OrderMapper;
import cn.bluemobi.platform.service.CommodityService;
import cn.bluemobi.platform.service.OrderService;

/**
 * Description: <br/>
 * Date: 2016年11月9日 下午2:44:18 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private CommodityService commodityService;

    @Override
    public MapDto createOrder(OrderMain order, User user) {
        MapDto dto = new MapDto();
        // 订单入库
        order.setCreateBy(user.getId());
        orderMapper.insertNewOrder(order);
        // 商品关联
        String commodityIds = order.getCommodityIds();
        String counts = order.getCounts();
        String[] commos = commodityIds.split(",");
        String[] cs = counts.split(",");
        for (int i = 0; i < commos.length; i++) {
            if (StringUtils.isNotEmpty(commos[i])) {
                Map<String, Object> commodity = commodityService.findById(commos[i]);
                commodity.put("count", cs[i]);
                commodity.put("orderId", order.getId());
                orderMapper.insertOrderCommodity(commodity);
            }
        }
        // 查看数据库，是否有身份证，有则自动填写
        List<Client> list = clientMapper.findReceiverByNameAndPhone(order);
        if (list.size() > 0) {
            Client cli = list.get(0);
            if (StringUtils.equals(cli.getHasPic(), "1")) {
                // 上传了身份证，拷贝到订单表中
                orderMapper.updateOrderPic(cli.getIdcardNo(), cli.getFontsidePic(), cli.getBacksidePic(),
                        cli.getMergesidePic(), order.getId());
            }
        }
        // 生成订单轨迹
        orderMapper.createStep("运单已录入龙门快递系统", order.getId());
        return dto;
    }

    @Override
    public Page<Map<String, Object>> findOrders(PageCondition cond) {
        Page<Map<String, Object>> page = PageUtils.startPage(cond);
        page.setList(orderMapper.findOrders(cond.getMap()));
        return page;
    }

    @Override
    public MapDto markPaied(String id, User user) {
        List<String> list = Arrays.asList(id.split(","));
        list.remove("");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", list);
        params.put("modifyBy", user.getId());
        orderMapper.markPaied(params);
        return new MapDto();
    }

    @Override
    public MapDto markUnPaied(String id, User user) {
        List<String> list = Arrays.asList(id.split(","));
        list.remove("");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", list);
        params.put("modifyBy", user.getId());
        orderMapper.markUnPaied(params);
        return new MapDto();
    }

    @Override
    public OrderMain findById(Long id) {
        return orderMapper.findById(id);
    }

    @Override
    public List<Map<String, Object>> findCommoditiesByOrderId(Long id) {
        return orderMapper.findCommoditiesByOrderId(id);
    }

    @Override
    public MapDto updateOrder(OrderMain order, User user) {
        MapDto dto = new MapDto();
        // 订单入库
        order.setModifyBy(user.getId());
        orderMapper.updateById(order);
        // 商品关联
        String updateIds = order.getUpdateIds();
        String commodityIds = order.getCommodityIds();
        String counts = order.getCounts();
        String[] commos = commodityIds.split(",");
        String[] updateId = updateIds.split(",");
        String[] cs = counts.split(",");
        // 先删除
        List<String> idlist = Arrays.asList(updateId);
        idlist.remove("");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", idlist);
        params.put("orderId", order.getId());
        orderMapper.deleteCommodity(params);
        // 再修改或者新增
        for (int i = 0; i < updateId.length; i++) {
            if (StringUtils.isNotBlank(updateId[i])) {
                orderMapper.updateCommodityCount(cs[i], updateId[i]);
            } else {
                if (StringUtils.isNotEmpty(commos[i])) {
                    Map<String, Object> commodity = commodityService.findById(commos[i]);
                    commodity.put("count", cs[i]);
                    commodity.put("orderId", order.getId());
                    orderMapper.insertOrderCommodity(commodity);
                }
            }
        }
        // 查看数据库，是否有身份证，有则自动填写
        List<Client> list = clientMapper.findReceiverByNameAndPhone(order);
        if (list.size() > 0) {
            Client cli = list.get(0);
            if (StringUtils.equals(cli.getHasPic(), "1")) {
                // 上传了身份证，拷贝到订单表中
                orderMapper.updateOrderPic(cli.getIdcardNo(), cli.getFontsidePic(), cli.getBacksidePic(),
                        cli.getMergesidePic(), order.getId());
            }
        }
        return dto;
    }

    @Override
    public Dto<OrderMain> findByOrderNo(String orderNo) {
        Dto<OrderMain> dto = new Dto<OrderMain>();
        List<OrderMain> list = orderMapper.findByOrderNo(orderNo);
        if (list.size() > 0) {
            dto.setData(list.get(0));
        } else {
            dto.errorMsg("木有");
        }
        return dto;
    }

    @Override
    public MapDto updateWeight(Long id, Double totalWeight, User user) {
        orderMapper.updateWeightById(totalWeight, user.getId(), id);
        return new MapDto();
    }

    @Override
    public void receiveDepponNotify(DepponRequest request, DepponResponse response) {
        String orderNo = request.getLogisticID();
        String status = request.getStatusType();
        if (StringUtils.equals(status, "WAITACCEPT")) {
            // 下订单后初始化状态
        } else if (StringUtils.equals(status, "ACCEPT")) {
            // 营业部已接受订单
        } else if (StringUtils.equals(status, "UNACCEPT")) {
            // 营业部不受理订单，具体原因，接口返回内容中有描述
        } else if (StringUtils.equals(status, "CANCELLED")) {
            // 客户通过第三方接入平台撤销订单

        } else if (StringUtils.equals(status, "GOT")) {
            // 营业部揽货成功并开单，生成运单号

        } else if (StringUtils.equals(status, "NOGET")) {
            // 营业部揽货失败

        } else if (StringUtils.equals(status, "SIGNSUCCESS")) {

        } else if (StringUtils.equals(status, "SIGNFAILED")) {

        }
    }

}
