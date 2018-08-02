/**
 * Project Name:banma
 * File Name:AreaServiceImpl.java
 * Package Name:cn.bluemobi.banma.service.impl
 * Date:2015年12月22日下午3:15:00
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.platform.mapper.AreaMapper;
import cn.bluemobi.platform.service.AreaService;

/**
 * Description: <br/>
 * Date: 2015年12月22日 下午3:15:00 <br/>
 * 
 * @author lvxh
 * @version
 * @see
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Map<String, Object>> findProvinces() {
        return areaMapper.findProvinces();
    }

    @Override
    public List<Map<String, Object>> findByParent(String string) {
        return areaMapper.findByParent(string);
    }
}
