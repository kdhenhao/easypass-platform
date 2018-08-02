/**
 * Project Name:lmExpress-platform
 * File Name:ClearNoServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年11月24日下午2:21:29
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.mapper.ClearNoMapper;
import cn.bluemobi.platform.service.ClearNoService;

/**
 * Description: <br/>
 * Date: 2016年11月24日 下午2:21:29 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class ClearNoServiceImpl implements ClearNoService {

    @Autowired
    private ClearNoMapper clearNoMapper;

    @Override
    public Page<Map<String, Object>> findClearNos(Integer start, Integer length, String orderNo, String clearNo,
            String status) {
        Page<Map<String, Object>> page = PageUtils.startPage(start, length);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderNo", orderNo);
        params.put("clearNo", clearNo);
        params.put("status", status);
        page.setList(clearNoMapper.findClearNos(params));
        return page;
    }

    @Override
    public Map<String, Object> importClearNo(Map<String, Object> map) {
        if (clearNoMapper.exist(map) > 0) {
            map.put("success", "1");
            map.put("msg", "该运单号已经导入到系统，重复导入");
        } else {
            clearNoMapper.insert(map);
        }
        return map;
    }

}
