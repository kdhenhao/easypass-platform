/**
 * Project Name:easypass-platform
 * File Name:AppUserMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年5月27日下午1:53:40
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.appUser.AppUser;
import cn.bluemobi.platform.vo.AppUserVO;

/**
 * Description: APP用户<br/>
 * Date: 2017年5月27日 下午1:53:40 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface AppUserMapper {

    List<AppUserVO> getAppUserList(Map<String, Object> map);

    int addAppUser(AppUser appUser);

    int updateAppUser(AppUser appUser);

    int enableAppUser(Long userId);

    int disableAppUser(Long userId);

    int resetAppUserPassword(Map<String, Object> map);

}
