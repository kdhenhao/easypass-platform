/**
 * Project Name:lmExpress-platform
 * File Name:NewsServiceImple.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年10月31日下午4:34:06
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.NewsMapper;
import cn.bluemobi.platform.service.NewsService;

/**
 * Description: <br/>
 * Date: 2016年10月31日 下午4:34:06 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public Page<Map<String, Object>> findNews(Integer start, Integer length) {
        Page<Map<String, Object>> page = PageUtils.startPage(start, length);
        page.setList(newsMapper.findNews());
        return page;
    }

    @Override
    public MapDto saveOrUpdateNews(Long id, String title, String content, User user) {
        if (id == null) {
            newsMapper.insertNews(title, content, user.getId());
        } else {
            newsMapper.updateNews(title, content, user.getId(), id);
        }
        return new MapDto();
    }

    @Override
    public MapDto publishOrupdateNews(Long id, String title, String content, User user) {
        if (id == null) {
            newsMapper.insertNews2(title, content, user.getId());
        } else {
            newsMapper.updateNews2(title, content, user.getId(), id);
        }
        return new MapDto();
    }

    @Override
    public MapDto deleteById(Long id, User user) {
        newsMapper.deleteById(id);
        return new MapDto();
    }

    @Override
    public MapDto publishById(Long id, User user) {
        newsMapper.publishById(id, user.getId());
        return new MapDto();
    }

    @Override
    public Map<String, Object> findById(Long id) {
        return newsMapper.findById(id);
    }

}
