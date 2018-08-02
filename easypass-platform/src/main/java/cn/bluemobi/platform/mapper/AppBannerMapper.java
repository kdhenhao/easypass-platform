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

import cn.bluemobi.platform.entity.appBanner.AppBanner;

/**
 * Description: appbanner管理 <br/>
 * Date: 2017年5月31日 下午2:06:43 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface AppBannerMapper {

    List<AppBanner> findAppBannerListByPage(Map<String, Object> map);

    void updateAppBannerStatus(int status, Long id);

    int updateAppBanner(AppBanner appBanner);

    int addAppBanner(AppBanner appBanner);

    int deleteAppBannerById(Long id);

}
