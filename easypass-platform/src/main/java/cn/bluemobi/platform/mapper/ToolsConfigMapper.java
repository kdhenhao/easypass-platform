/**
 * Project Name:easypass-platform
 * File Name:ToolsConfigMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年5月23日下午15:17:17
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.entity.toolsConfig.AppAboutUs;
import cn.bluemobi.platform.entity.toolsConfig.ToolsConfig;

/**
 * 
 * Description: 配置管理<br/>
 * date: 2017年5月23日 下午3:15:24 <br/>
 *
 * @author oscarwang
 * @version
 */
public interface ToolsConfigMapper {

    /**
     * 查询所有
     */
    List<ToolsConfig> getToolsConfigList(Map<String, Object> map, User user);

    ToolsConfig findConfigById(String id);

    /**
     * 编辑
     */
    int updateConfig(ToolsConfig bro);

    AppAboutUs getAboutUs(Map<String, Object> map);

    int updateAboutUs(AppAboutUs appAboutUs);
}
