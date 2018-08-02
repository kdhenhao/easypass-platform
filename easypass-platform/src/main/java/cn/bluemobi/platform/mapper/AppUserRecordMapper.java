/**
 * Project Name:easypass-platform
 * File Name:AppUserRecordMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年7月16日下午8:12:31
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.appUser.AppUserRecord;

/**
 * Description: <br/>
 * Date: 2017年7月16日 下午8:12:31 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface AppUserRecordMapper {

    List<AppUserRecord> getAppUserRecordList(Map<String, Object> map);

}
