/**
 * Project Name:easypass-platform
 * File Name:AppBannerMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年5月31日下午2:06:43
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.appCampus.AppCampus;
import cn.bluemobi.platform.vo.AppCampusVO;

/**
 * Description: appCampus管理 <br/>
 * 
 * @author justin
 * @version
 * @see
 */
public interface AppCampusMapper {

    List<AppCampusVO> findAppCampusListByPage(Map<String, Object> map);

    int updateAppCampus(AppCampus appCampus);

    int addAppCampus(AppCampus appCampus);

    int deleteAppCampusById(Long id);
    
    List<AppCampus> getAllCampusList();

}
