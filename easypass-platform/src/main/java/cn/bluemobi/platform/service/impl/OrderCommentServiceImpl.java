/**
 * Project Name:easypass-platform
 * File Name:OrderCommentServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2017年6月2日下午1:38:26
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import cn.bluemobi.platform.mapper.OrderCommentMapper;
import cn.bluemobi.platform.service.LogService;
import cn.bluemobi.platform.service.OrderCommentService;

/**
 * Description: <br/>
 * Date: 2017年6月2日 下午1:38:26 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Service
public class OrderCommentServiceImpl implements OrderCommentService {

    @Autowired
    private OrderCommentMapper orderCommentMapper;

    // 添加日志
    @Autowired
    private LogService logService;

    @Autowired
    private HttpSession session;

    @Override
    public Page<Map<String, Object>> findOrderCommentList(PageCondition cond) {

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
                    map.put("userName", name);

                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Page<Map<String, Object>> page = PageUtils.startPage(cond);
        List<Map<String, Object>> listMap = orderCommentMapper.findOrderCommentList(cond.getMap());
        try {
            for (Map<String, Object> map1 : listMap) {
                Object name = map1.get("userName");
                String userName = "";
                if (name != null) {
                    userName = name.toString();
                }
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
    public MapDto deleteOrderCommentById(Long id) {

        MapDto mapDto = new MapDto();
        int i = orderCommentMapper.deleteOrderCommentById(id);
        if (i > 0) {
            mapDto.setStatus(0);
            mapDto.setMsg("删除成功");
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null, "删除评论",
                    "评论管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("删除失败");
        }
        return mapDto;
    }

}
