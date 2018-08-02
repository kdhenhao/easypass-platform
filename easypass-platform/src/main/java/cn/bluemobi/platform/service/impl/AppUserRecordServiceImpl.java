/**
 * Project Name:easypass-platform
 * File Name:AppUserRecordServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2017年7月16日下午8:09:50
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.appUser.AppUserRecord;
import cn.bluemobi.platform.mapper.AppUserRecordMapper;
import cn.bluemobi.platform.service.AppUserRecordService;

/**
 * Description: <br/>
 * Date: 2017年7月16日 下午8:09:50 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Service
public class AppUserRecordServiceImpl implements AppUserRecordService {

    @Autowired
    private AppUserRecordMapper appUserRecordMapper;

    @Override
    public Page<AppUserRecord> getAppUserRecordByPage(Integer start, Integer length, String nickName) {

        Page<AppUserRecord> page = PageUtils.startPage(start, length);
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(nickName)) {
            map.put("nickName", nickName);
        }

        List<AppUserRecord> appUserList = appUserRecordMapper.getAppUserRecordList(map);
        try {
            for (AppUserRecord appUserRecord : appUserList) {
                if (StringUtils.isNotBlank(appUserRecord.getNickName())) {
                    appUserRecord.setNickName(URLDecoder.decode(appUserRecord.getNickName(), "UTF-8"));
                }

            }
        } catch (UnsupportedEncodingException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
        page.setList(appUserList);
        return page;
    }

}
