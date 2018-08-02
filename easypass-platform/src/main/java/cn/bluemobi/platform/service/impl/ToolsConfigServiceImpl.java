/**
 * Project Name:cwy-platform
 * File Name:ToolsConfigImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2016年12月6日下午2:32:50
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.utils.DateUtils;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.entity.toolsConfig.AppAboutUs;
import cn.bluemobi.platform.entity.toolsConfig.ToolsConfig;
import cn.bluemobi.platform.mapper.ToolsConfigMapper;
import cn.bluemobi.platform.service.LogService;
import cn.bluemobi.platform.service.ToolsConfigService;
import cn.bluemobi.platform.utils.FileUploadUtils;

/**
 * 
 * Description: 配置管理<br/>
 * date: 2017年5月23日 下午3:18:38 <br/>
 *
 * @author oscarwang
 * @version
 */
@Service
@Transactional
public class ToolsConfigServiceImpl implements ToolsConfigService {

    @Autowired
    private ToolsConfigMapper toolsConfigMapper;

    // 添加日志
    @Autowired
    private LogService logService;

    @Autowired
    private HttpSession session;

    @Override
    public Page<ToolsConfig> getToolsConfigByPage(Integer start, Integer length, ToolsConfig toolsConfig, User user) {
        toolsConfig.setUserName(user.getUsername());
        Page<ToolsConfig> page = PageUtils.startPage(start, length);
        Map<String, Object> map = new HashMap<String, Object>();

        List<ToolsConfig> ToolsConfigList = toolsConfigMapper.getToolsConfigList(map, user);

        page.setList(ToolsConfigList);

        return page;
    }

    @Override
    public ToolsConfig findConfigById(String id) {
        ToolsConfig bro = toolsConfigMapper.findConfigById(id);
        return bro;
    }

    @Override
    public MapDto updateConfig(ToolsConfig bro) {
        bro.setModifyTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        bro.setModifyBy(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId());
        toolsConfigMapper.updateConfig(bro);
        logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null, "修改配置信息",
                "配置管理");
        return new MapDto();
    }

    @Override
    public MapDto updateAboutUs(AppAboutUs appAboutUs, String dir, MultipartFile file1, MultipartFile file2) {
        // appAboutUs.setModifyTime(DateUtils.format(new Date(), "yyyy-MM-dd
        // HH:mm:ss"));
        MapDto map;
        try {
            map = FileUploadUtils.uploadImg(dir, file1);
            if (null != map.getData()) {
                appAboutUs.setLogoImg((String) map.getData().get("img"));
            }
            map = FileUploadUtils.uploadImg(dir, file2);
            if (null != map.getData()) {
                appAboutUs.setQrCodeImg((String) map.getData().get("img"));
            }
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        toolsConfigMapper.updateAboutUs(appAboutUs);
        ToolsConfig bro = new ToolsConfig();
        bro.setId(1);
        bro.setModifyBy(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId());
        toolsConfigMapper.updateConfig(bro);

        logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null, "修改配置信息",
                "配置管理");
        return new MapDto();
    }

    @Override
    public AppAboutUs getAboutUs(String configType) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("configType", configType);
        return toolsConfigMapper.getAboutUs(map);
    }
}
