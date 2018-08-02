/**
 * Project Name:easypass-platform
 * File Name:AppHelpMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年6月23日下午4:29:32
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.appHelp.AppHelp;

/**
 * Description: <br/>
 * Date: 2017年6月23日 下午4:29:32 <br/>
 * 
 * @author wuya
 * @version
 * @see
 */
public interface AppHelpMapper {

    int insertHelp(AppHelp help);

    List<AppHelp> findHelpList(Map<String, Object> data);

    int deleteHelpById(Integer id);

    int updedateHelp(AppHelp help);
}
