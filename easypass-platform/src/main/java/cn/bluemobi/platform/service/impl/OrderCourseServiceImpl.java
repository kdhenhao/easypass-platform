/**
 * Project Name:easypass-platform
 * File Name:OrderCourseServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2017年6月1日下午3:56:46
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.OrderCourseMapper;
import cn.bluemobi.platform.service.LogService;
import cn.bluemobi.platform.service.OrderCourseService;

/**
 * Description: 订单管理 <br/>
 * Date: 2017年6月1日 下午3:56:46 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Service
public class OrderCourseServiceImpl implements OrderCourseService {

    @Autowired
    private OrderCourseMapper orderCourseMapper;

    // 添加日志
    @Autowired
    private LogService logService;

    @Autowired
    private HttpSession session;

    @Override
    public Page<Map<String, Object>> findOrderCourseList(PageCondition cond) {

        Map<String, Object> map = cond.getMap();
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        // try...catch放在循环外面
        try {
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object object = map.get(key);
                String userName = (String) object;
                if ("userName".equals(key)) {
                    String name = "";
                    if (StringUtils.isNotBlank(userName)) {
                        name = URLEncoder.encode(userName, "UTF-8");
                    }
                    if (StringUtils.isNotBlank(name)) {
                        name = URLEncoder.encode(name, "UTF-8");
                        name = URLDecoder.decode(name, "UTF-8");
                    }

                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Page<Map<String, Object>> page = PageUtils.startPage(cond);
        List<Map<String, Object>> listMap = orderCourseMapper.findOrderCourseList(cond.getMap());
        try {
            for (Map<String, Object> map1 : listMap) {
                String userName = "";
                if (map1.get("userName") != null) {
                    userName = map1.get("userName").toString();
                }
                // String userName = map1.get("userName").toString();
                // map1.put("userName", URLDecoder.decode(userName, "UTF-8"));
                if (StringUtils.isNotBlank(userName)) {
                    map1.put("userName", URLDecoder.decode(userName, "UTF-8"));
                } else {
                    map1.put("userName", "");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        page.setList(listMap);
        return page;
    }

    @Override
    public Page<Map<String, Object>> findOrderCourseDetail(Integer start, Integer length, String orderNo) {

        Page<Map<String, Object>> page = PageUtils.startPage(start, length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);
        page.setList(orderCourseMapper.findOrderCourseDetail(map));
        return page;
    }

    @Override
    public MapDto deleteOrderById(Long orderId, String orderNo) {

        MapDto mapDto = new MapDto();
        int i = orderCourseMapper.deleteOrderById(orderId);
        orderCourseMapper.deleteOrderDetail(orderNo);
        if (i > 0) {
            mapDto.setStatus(0);
            mapDto.setMsg("删除成功");
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null, "删除订单",
                    "订单管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("删除失败");
        }
        return mapDto;
    }

    @Override
    public MapDto updateStatus(Long orderId, Integer orderStatus) {

        MapDto mapDto = new MapDto();
        int i = orderCourseMapper.updateStatus(orderId, orderStatus);
        if (i > 0) {
            mapDto.setStatus(0);
            mapDto.setMsg("更新成功");
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "修改订单状态", "订单管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("更新失败");
        }
        return mapDto;
    }

    @Override
    public MapDto updateFileStatus(Long orderId, Integer orderStatus) {

        MapDto mapDto = new MapDto();
        int i = orderCourseMapper.updateFileStatus(orderId, orderStatus);
        if (i > 0) {
            mapDto.setStatus(0);
            mapDto.setMsg("更新成功");
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "修改订单资料状态", "订单管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("更新失败");
        }
        return mapDto;
    }

}
