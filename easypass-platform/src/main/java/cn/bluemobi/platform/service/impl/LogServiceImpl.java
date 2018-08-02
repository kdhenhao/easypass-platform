/**
 * Project Name:banma
 * File Name:LogServiceImpl.java
 * Package Name:cn.bluemobi.banma.service.impl
 * Date:2015年12月16日下午3:07:33
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.mapper.LogMapper;
import cn.bluemobi.platform.service.LogService;

/**
 * Description: <br/>
 * Date: 2015年12月16日 下午3:07:33 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public Page<Map<String, Object>> findLogsPage(int start, int length, String startDate, String endDate,
            String username, String optType) {
        Page<Map<String, Object>> page = PageUtils.startPage(start, length);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("start", startDate);
        params.put("end", endDate);
        params.put("username", username);
        params.put("optType", optType);
        page.setList(logMapper.findLogs(params));
        return page;
    }

    @Override
    public void addLog(Long userId, Long memberId, String message, String model) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        logMapper.insertLog(userId, memberId, message, model, now);
    }

}
