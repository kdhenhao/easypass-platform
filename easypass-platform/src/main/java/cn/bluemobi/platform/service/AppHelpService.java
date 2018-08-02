/**
 * Project Name:easypass-platform
 * File Name:AppHelpService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2017年6月23日下午4:53:21
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.appHelp.AppHelp;

/**
 * Description: <br/>
 * Date: 2017年6月23日 下午4:53:21 <br/>
 * 
 * @author wuya
 * @version
 * @see
 */
public interface AppHelpService {

    Page<AppHelp> findHelpList(Integer start, Integer length, AppHelp help);

    MapDto addHelp(AppHelp help);

    MapDto updateHelp(AppHelp help);

    MapDto deleteHelpById(Integer id);
}
