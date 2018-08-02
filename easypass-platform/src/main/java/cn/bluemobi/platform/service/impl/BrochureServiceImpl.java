/**
 * Project Name:banma
 * File Name:BrochureServiceImpl.java
 * Package Name:cn.bluemobi.banma.service.impl
 * Date:2015年12月14日下午5:25:49
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.utils.DateUtils;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.entity.tools.Brochure;
import cn.bluemobi.platform.mapper.BrochureMapper;
import cn.bluemobi.platform.service.BrochureService;

/**
 * Description: <br/>
 * Date: 2015年12月14日 下午5:25:49 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class BrochureServiceImpl implements BrochureService {

    @Autowired
    private BrochureMapper brochureMapper;

    @Override
    public Page<Map<String, Object>> findBrochures(int start, int length) {
        Page<Map<String, Object>> page = PageUtils.startPage(start, length);
        page.setList(brochureMapper.findBrochures());
        return page;
    }

    @Override
    public Brochure findBrochureById(String id) {
        Brochure bro = brochureMapper.findBrochureById(id);
        // bro.setContent(StringEscapeUtils.escapeHtml(bro.getContent()));
        return bro;
    }

    @Override
    public MapDto updateBrochure(Brochure bro, User user) {
        bro.setModifyBy(user.getId().toString());
        bro.setModifyTm(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        brochureMapper.updateBrochure(bro);
        return new MapDto();
    }

    @Override
    public String findBrochureByTitle(String title) {
        String content = "";
        List<Brochure> bros = brochureMapper.findBrochureByTitle(title);
        if (CollectionUtils.isEmpty(bros)) {
            content = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"/></head><body>暂无内容</body></html>";
        } else {
            content = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"/></head><body>" + bros.get(0).getContent()
                    + "</body></html>";
        }
        return content;
    }
}
