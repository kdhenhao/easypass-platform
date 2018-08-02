/**
 * Project Name:easypass-platform
 * File Name:AppBannerService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2017年5月31日下午1:59:47
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.appBanner.AppBanner;

/**
 * Description: <br/>
 * Date: 2017年5月31日 下午1:59:47 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface AppBannerService {

    Page<AppBanner> findAppBannerListByPage(Integer start, Integer length, AppBanner appBanner);

    MapDto updateAppBannerStatus(int status, Long id);

    MapDto updateAppBanner(AppBanner appBanner);

    MapDto addAppBanner(AppBanner appBanner);

    MapDto deleteAppBannerById(Long id);

}
