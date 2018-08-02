/**
 * Project Name:lmExpress-platform
 * File Name:TaxServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年11月3日上午11:18:27
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.commodity.Tax;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.TaxMapper;
import cn.bluemobi.platform.service.TaxService;

/**
 * Description: <br/>
 * Date: 2016年11月3日 上午11:18:27 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class TaxServiceImpl implements TaxService {

    @Autowired
    private EhCacheCacheManager cacheManager;

    @Autowired
    private TaxMapper taxMapper;

    @Override
    public Page<Map<String, Object>> findTaxs(PageCondition cond) {
        Page<Map<String, Object>> page = PageUtils.startPage(cond);
        page.setList(taxMapper.findTaxs(cond.getMap()));
        return page;
    }

    @Override
    public MapDto lockById(Long id, User user) {
        taxMapper.lockById(id, user.getId());
        return new MapDto();
    }

    @Override
    public MapDto unLockById(Long id, User user) {
        taxMapper.unLockById(id, user.getId());
        return new MapDto();
    }

    @Override
    public MapDto saveOrUpdate(Tax tax, User user) {
        MapDto dto = new MapDto();
        if (tax.getId() == null) {
            if (taxMapper.hasCode(tax.getTaxNo()) > 0) {
                dto.errorMsg("该税号已经存在，不能重复添加");
            } else {
                String name = tax.getTaxName();
                int level = 0;
                if (name.indexOf("000000") > 0) {
                    level = 0;
                } else if (name.indexOf("0000") > 0) {
                    level = 1;
                } else if (name.indexOf("00") > 0) {
                    level = 2;
                } else {
                    level = 3;
                }
                tax.setLevel(level);
                tax.setCreateBy(user.getId());
                taxMapper.insert(tax);
            }
        } else {
            if (taxMapper.hasCode2(tax.getTaxNo(), tax.getId()) > 0) {
                dto.errorMsg("该税号已经存在，修改后会导致重复");
            } else {
                tax.setModifyBy(user.getId());
                taxMapper.update(tax);
            }
        }
        return dto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Tax> findTaxsForSelect() {
        ValueWrapper vw = cacheManager.getCache("shortTermCache").get("allTaxes");
        if (vw == null || vw.get() == null) {
            List<Tax> list = taxMapper.findTaxForSelect();
            cacheManager.getCache("shortTermCache").put("allTaxes", list);
            return list;
        } else {
            return (List<Tax>) vw.get();
        }
    }

}
