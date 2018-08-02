/**
 * Project Name:easypass-platform
 * File Name:AppUserService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2017年5月27日下午1:32:51
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.appUser.AppUser;
import cn.bluemobi.platform.vo.AppUserVO;

/**
 * Description: APP用户<br/>
 * Date: 2017年5月27日 下午1:32:51 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface AppUserService {

    Page<AppUserVO> getAppUserByPage(Integer start, Integer length, AppUserVO appUser);

    MapDto addAppUser(AppUser appUser);

    MapDto updateAppUser(AppUser appUser);

    MapDto enableAppUser(Long userId);

    MapDto disableAppUser(Long userId);

    MapDto resetAppUserPassword(Long userId);

}
