/**
 * Project Name:easypass-platform Maven Webapp
 * File Name:VideoClickImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2017年8月3日下午12:57:58
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.mapper.VideoClickMapper;
import cn.bluemobi.platform.service.VideoClickService;

/**
 * Description:点击量查询 <br/>
 * Date: 2017年8月3日 下午12:57:58 <br/>
 * 
 * @author Administrator
 * @version
 * @see
 */
@Service
@Transactional
public class VideoClickServiceImpl implements VideoClickService {

    @Autowired
    private VideoClickMapper videoClickMapper;

    @Override
    public Page<Map<String, Object>> findVideoClicks(PageCondition cond) {
        Page<Map<String, Object>> page = PageUtils.startPage(cond);
        page.setList(videoClickMapper.findVideoClick(cond.getMap()));
        return page;
    }

}
