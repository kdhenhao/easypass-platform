/**
 * Project Name:lmExpress-platform
 * File Name:DictServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年11月19日下午2:07:10
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.DictMapper;
import cn.bluemobi.platform.service.DictService;

/**
 * Description: <br/>
 * Date: 2016年11月19日 下午2:07:10 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public Map<String, Object> findForWeb() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("webAddress", dictMapper.findByName("web_address"));
        map.put("webEmail", dictMapper.findByName("web_email"));
        map.put("webWechat", dictMapper.findByName("web_wechat"));
        map.put("webQrcode", dictMapper.findByName("web_qrcode"));
        map.put("webPhone", dictMapper.findByName("web_phone"));
        map.put("webServiceTime", dictMapper.findByName("web_service_time"));
        map.put("webQQ", dictMapper.findByName("web_qq"));
        return map;
    }

    @Override
    public MapDto update(String webAddress, String webPhone, String webEmail, String webQrcode, String webServiceTime,
            String webWechat, String webQQ, User user) {
        dictMapper.updateByName(webAddress, "web_address");
        dictMapper.updateByName(webPhone, "web_phone");
        dictMapper.updateByName(webEmail, "web_email");
        dictMapper.updateByName(webQrcode, "web_qrcode");
        dictMapper.updateByName(webServiceTime, "web_service_time");
        dictMapper.updateByName(webWechat, "web_wechat");
        dictMapper.updateByName(webQQ, "web_qq");
        return new MapDto();
    }

    @Override
    public String findByName(String string) {
        return dictMapper.findByName(string);
    }

    @Override
    public Map<String, Object> findForDeppon() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("depponCompany", dictMapper.findByName("deppon_company"));
        map.put("depponPhone", dictMapper.findByName("deppon_phone"));
        map.put("depponSender", dictMapper.findByName("deppon_sender"));
        map.put("depponAddress", dictMapper.findByName("deppon_address"));
        return map;
    }

    @Override
    public MapDto updateDeppon(String depponCompany, String depponSender, String depponPhone, String depponAddress,
            User user) {
        dictMapper.updateByName(depponCompany, "deppon_company");
        dictMapper.updateByName(depponSender, "deppon_sender");
        dictMapper.updateByName(depponPhone, "deppon_phone");
        dictMapper.updateByName(depponAddress, "deppon_address");
        return new MapDto();
    }

}
