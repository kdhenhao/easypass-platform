/**
 * Project Name:lmExpress-platform
 * File Name:PalletServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年11月14日上午11:03:59
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.utils.DateUtils;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.client.Client;
import cn.bluemobi.platform.entity.order.OrderMain;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.ClearNoMapper;
import cn.bluemobi.platform.mapper.ClientMapper;
import cn.bluemobi.platform.mapper.FlightMapper;
import cn.bluemobi.platform.mapper.OrderMapper;
import cn.bluemobi.platform.mapper.PalletMapper;
import cn.bluemobi.platform.service.PalletService;
import cn.bluemobi.platform.service.SequenceService;
import cn.bluemobi.platform.utils.SmsUtils;

/**
 * Description: <br/>
 * Date: 2016年11月14日 上午11:03:59 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class PalletServiceImpl implements PalletService {

    @Autowired
    private PalletMapper palletMapper;

    @Autowired
    private ClearNoMapper clearNoMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private FlightMapper flihtMapper;

    @Override
    public MapDto createPallet(String ids, User user) {
        if (StringUtils.isEmpty(ids)) {
            return MapDto.ERROR;
        }
        Map<String, Object> pallet = new HashMap<String, Object>();
        String palletName = generateName();
        pallet.put("palletName", palletName);
        pallet.put("createBy", user.getId());
        palletMapper.insert(pallet);
        // 批量 更新订单
        // pallet.put("ids", Arrays.asList(ids.split(",")));
        // palletMapper.updateOrderStatusOrderConfiged(pallet);
        // 一个个 加顺序 更新订单
        int sort = 0;
        for (String id : ids.split(",")) {
            pallet.put("orderId", id);
            pallet.put("palletSort", sort);
            palletMapper.updateOrderStatusOrderConfigedOneByOne(pallet);
            sort = sort + 1;
            orderMapper.createStep("运单已加入托盘 【" + palletName + "】", Long.valueOf(id));
        }
        return new MapDto();
    }

    private String generateName() {
        String today = DateUtils.format(new Date(), "yyyy-MM-dd");
        String seq = sequenceService.nextSeq(today);
        if (seq.equals("1")) {
            return today;
        } else {
            return today + "(" + seq + ")";
        }
    }

    @Override
    public Page<Map<String, Object>> findPallets(Integer start, Integer length, String flightNo, String palletName,
            String status) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("flightNo", flightNo);
        params.put("palletName", palletName);
        params.put("status", status);
        Page<Map<String, Object>> page = PageUtils.startPage(start, length);
        page.setList(palletMapper.findPallets(params));
        return page;
    }

    @Override
    public MapDto updateStatus(Long id, String status, User user) {
        palletMapper.updatePalletStatus(status, user.getId(), id);
        palletMapper.updateOrderStatus(status, user.getId(), id);
        // 批量生成运单轨迹
        List<Long> ids = orderMapper.findIdByPalletId(id);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);
        params.put("remark", getRemarkByStatus(status));
        orderMapper.createStepBatch(params);
        return new MapDto();
    }

    private String getRemarkByStatus(String status) {
        if (StringUtils.equals(status, "orderReceived")) {
            return "运单已从网点接收";
        } else if (StringUtils.equals(status, "orderReached")) {
            return "运单已抵达仓库";
        } else if (StringUtils.equals(status, "orderConfiged")) {
            return "运单已配置货物";
        } else if (StringUtils.equals(status, "flightConfiged")) {
            return "货物已配置航班";
        } else if (StringUtils.equals(status, "flighting")) {
            return "航班飞往中国中";
        } else if (StringUtils.equals(status, "clearancing")) {
            return "中国机场清关中";
        } else if (StringUtils.equals(status, "clearanced")) {
            return "清关完成等待送货";
        } else if (StringUtils.equals(status, "sending")) {
            return "中国快递配送中";
        }
        return "";
    }

    @Override
    public MapDto updateFlight(Long id, String flightId, User user) {
        palletMapper.updateFlight(flightId, user.getId(), id);
        palletMapper.updateOrderStatus("flightConfiged", user.getId(), id);
        // 批量生成运单轨迹
        List<Long> ids = orderMapper.findIdByPalletId(id);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);
        params.put("remark", "运单已配置航班 【" + flihtMapper.findFlightNameById(flightId) + "】");
        orderMapper.createStepBatch(params);
        return new MapDto();
    }

    @Override
    public List<Map<String, Object>> findOrdersByPallet(Long id) {
        return orderMapper.findOrdersByPallet(id);
    }

    @Override
    public MapDto deleteById(Long id) {
        palletMapper.deleteById(id);
        palletMapper.releaseOrder(id);
        // 批量生成运单轨迹
        List<Long> ids = orderMapper.findIdByPalletId(id);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);
        params.put("remark", "运单已从托盘中移出");
        orderMapper.createStepBatch(params);
        return new MapDto();
    }

    @Override
    public MapDto addOrder(String orderNo, Long id, User user) {
        MapDto dto = new MapDto();
        if (orderMapper.exist(orderNo) < 1) {
            dto.errorMsg("订单不存在");
        } else {
            if (palletMapper.hasOrder(orderNo, id) > 0) {
                dto.errorMsg("该订单已经添加过了，请勿重复添加");
            } else {
                palletMapper.addOrder(orderNo, user.getId(), id);
                String palletName = palletMapper.findNameById(id);
                orderMapper.createStep("运单已加入托盘 【" + palletName + "】", id);
            }
        }
        return dto;
    }

    @Override
    public MapDto deleteOrder(Long id, User user) {
        palletMapper.releaseOrderByOrderId(id);
        // 记录轨迹
        orderMapper.createStep("运单已从托盘中移出", id);
        return new MapDto();
    }

    @Override
    public MapDto replaceIdCard(Long orderId, User user) {
        MapDto dto = new MapDto();
        OrderMain om = orderMapper.findById(orderId);
        List<Client> clis = clientMapper.findReceiverForCheat(om.getPalletId());
        if (clis.size() < 1) {
            dto.errorMsg("数据库中没有足够的身份证可以用来替换，请先上传一些");
            return dto;
        }
        Client cli = clis.get(0);
        cli.setOrderId(orderId);
        orderMapper.replaceIdCard(cli);
        return dto;
    }

    @Override
    public MapDto replaceAll(Long palletId) {
        MapDto dto = new MapDto();
        int count = 0;
        List<OrderMain> oms = orderMapper.findNoPicByPalletId(palletId);
        if (oms.size() == 0) {
            dto.errorMsg("不需要替换");
            return dto;
        }
        for (OrderMain om : oms) {
            List<Client> clis = clientMapper.findReceiverForCheat(om.getPalletId());
            if (clis.size() < 1) {
                dto.errorMsg("已成功替换" + count + "个订单，但数据库中没有足够的身份证可以用来替换，请先上传一些");
                return dto;
            }
            count = count + 1;
            Client cli = clis.get(0);
            cli.setOrderId(om.getId());
            orderMapper.replaceIdCard(cli);
        }
        return dto;
    }

    @Override
    public MapDto sendSmsAll(Long palletId) {
        MapDto dto = new MapDto();
        List<String> phones = orderMapper.findNoPicPhone(palletId);
        if (phones.size() < 1) {
            dto.errorMsg("没有人需要提醒");
            return dto;
        }
        String result = SmsUtils.sendSms(StringUtils.join(phones, ","), "");
        if (SmsUtils.isSuccess(result)) {

        } else if (SmsUtils.isPoor(result)) {
            dto.errorMsg("短信平台余额不足，请联系平台充值");
        } else {
            dto.errorMsg("短信发送失败：" + result);
        }
        return dto;
    }

    @Override
    public List<OrderMain> findOrderPicByPalletId(Long palletId) {
        return orderMapper.findOrderPicByPalletId(palletId);
    }

    @Override
    public String findPalletName(Long palletId) {
        return palletMapper.findNameById(palletId);
    }

    @Override
    public List<Map<String, Object>> findItemsByPalletId(Long palletId) {
        return palletMapper.findItemsByPalletId(palletId);
    }

    @Override
    public List<Map<String, Object>> findDeclarationByPalletId(Long palletId) {
        return palletMapper.findDeclarationByPalletId(palletId);
    }

    @Override
    public int findClearNoSum() {
        return clearNoMapper.countNotUsed();
    }

    @Override
    public synchronized MapDto allocateClearNo(Long palletId) {
        MapDto dto = new MapDto();
        List<Long> orderIds = clearNoMapper.findOrdersNotAllocated(palletId);
        List<String> usableNos = clearNoMapper.findNotUsed100();
        if (usableNos.size() < orderIds.size()) {
            dto.errorMsg("有" + orderIds.size() + "个订单需要分配清关号，但只有" + usableNos.size() + "个可用，请导入新的清关号");
            return dto;
        }
        for (int i = 0; i < orderIds.size(); i++) {
            clearNoMapper.allocate(usableNos.get(i), orderIds.get(i));
            clearNoMapper.markAllocated(usableNos.get(i));
        }
        dto.putInData("count", clearNoMapper.countNotUsed());
        return dto;
    }

    @Override
    public MapDto checkClearNo(Long palletId) {
        MapDto dto = new MapDto();
        List<Long> orderIds = clearNoMapper.findOrdersNotAllocated(palletId);
        if (orderIds.size() > 0) {
            dto.errorMsg("还有订单没有分配清关单号，请点击“一键分配惠州清关单号”，已分配过的不会重新分配");
            return dto;
        }
        return dto;
    }

    @Override
    public List<Map<String, Object>> findGoodsByOrderId(Long long1) {
        return orderMapper.findCommoditiesByOrderId(long1);
    }
}
