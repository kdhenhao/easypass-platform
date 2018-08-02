/**
 * Project Name:cwy-platform
 * File Name:ToolsConfigService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年12月6日下午2:31:15
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service;

import org.springframework.web.multipart.MultipartFile;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.entity.toolsConfig.AppAboutUs;
import cn.bluemobi.platform.entity.toolsConfig.ToolsConfig;

/**
 * 
 * Description: 配置管理<br/>
 * date: 2017年5月23日 下午3:19:38 <br/>
 *
 * @author oscarwang
 * @version
 */
public interface ToolsConfigService {

    /**
     * 查询预约管理
     */
    Page<ToolsConfig> getToolsConfigByPage(Integer start, Integer length, ToolsConfig toolsConfig, User user);

    ToolsConfig findConfigById(String id);

    MapDto updateConfig(ToolsConfig bro);

    AppAboutUs getAboutUs(String configType);

    MapDto updateAboutUs(AppAboutUs appAboutUs, String dir, MultipartFile file1, MultipartFile file2);

}
