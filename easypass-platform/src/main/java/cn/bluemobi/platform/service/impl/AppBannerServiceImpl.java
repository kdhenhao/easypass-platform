/**
 * Project Name:easypass-platform
 * File Name:AppBannerServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2017年5月31日下午2:01:49
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.appBanner.AppBanner;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.AppBannerMapper;
import cn.bluemobi.platform.service.AppBannerService;
import cn.bluemobi.platform.service.LogService;

/**
 * Description: appbanner管理 <br/>
 * Date: 2017年5月31日 下午2:01:49 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Service
public class AppBannerServiceImpl implements AppBannerService {

    @Autowired
    private AppBannerMapper appBannerMapper;
    //添加日志
    @Autowired
    private LogService logService;

    @Autowired
    private HttpSession session;

    @Override
    public Page<AppBanner> findAppBannerListByPage(Integer start, Integer length, AppBanner appBanner) {

        Page<AppBanner> page = PageUtils.startPage(start, length);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("status", appBanner.getStatus());

        List<AppBanner> appBannerList = appBannerMapper.findAppBannerListByPage(map);
        page.setList(appBannerList);
        return page;
    }

    @Override
    public MapDto updateAppBannerStatus(int status, Long id) {

        MapDto dto = new MapDto();
        appBannerMapper.updateAppBannerStatus(status, id);
        logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                "更新banner状态", "banner管理");
        return dto;
    }

    @Override
    public MapDto updateAppBanner(AppBanner appBanner) {

        MapDto dto = new MapDto();
        int i = appBannerMapper.updateAppBanner(appBanner);
        if (i > 0) {
            dto.setStatus(0);
            dto.setMsg("编辑成功");
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "修改banner:" + appBanner.getTitle(), "banner管理");
        } else {
            dto.setStatus(1);
            dto.setMsg("编辑失败");
        }
        return dto;
    }

    @Override
    public MapDto addAppBanner(AppBanner appBanner) {

        MapDto mapDto = new MapDto();
        int i = appBannerMapper.addAppBanner(appBanner);
        if (i > 0) {
            mapDto.setStatus(0);
            mapDto.setMsg("数据添加成功");
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "添加banner:" + appBanner.getTitle(), "banner管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("数据添加失败");
        }
        return mapDto;
    }

    @Override
    public MapDto deleteAppBannerById(Long id) {

        MapDto dto = new MapDto();
        int i = appBannerMapper.deleteAppBannerById(id);
        if (i > 0) {
            dto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "删除banner", "banner管理");
        } else {
            dto.setStatus(1);
            dto.setMsg("数据删除失败");
        }
        return dto;
    }

}
