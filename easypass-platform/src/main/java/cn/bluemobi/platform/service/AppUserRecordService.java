/**
 * Project Name:easypass-platform
 * File Name:AppUserRecordService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2017年7月16日下午8:04:36
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.appUser.AppUserRecord;

/**
 * Description: 用户记录 <br/>
 * Date: 2017年7月16日 下午8:04:36 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface AppUserRecordService {

    Page<AppUserRecord> getAppUserRecordByPage(Integer start, Integer length, String nickName);

}
