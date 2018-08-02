/**
 * Project Name:easypass-platform
 * File Name:AppUserService.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2017年5月27日下午1:35:49
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.utils.MD5Tools;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.appUser.AppUser;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.AppUserMapper;
import cn.bluemobi.platform.service.AppUserService;
import cn.bluemobi.platform.service.LogService;
import cn.bluemobi.platform.vo.AppUserVO;

/**
 * Description: APP用户 <br/>
 * Date: 2017年5月27日 下午1:35:49 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserMapper appUserMapper;

    // 添加日志
    @Autowired
    private LogService logService;

    @Autowired
    private HttpSession session;

    @Override
    public Page<AppUserVO> getAppUserByPage(Integer start, Integer length, AppUserVO appUser) {

        Page<AppUserVO> page = PageUtils.startPage(start, length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", appUser.getUserName());
        map.put("major", appUser.getMajor());
        map.put("email", appUser.getEmail());
        map.put("sex", appUser.getSex());
        map.put("status", appUser.getStatus());
        map.put("beginTime", appUser.getBeginTime());
        map.put("endTime", appUser.getEndTime());
        List<AppUserVO> appUserList = appUserMapper.getAppUserList(map);
        try {
            for (AppUserVO appUserVO : appUserList) {
                if (StringUtils.isNotBlank(appUserVO.getUserName())) {
                    appUserVO.setUserName(URLDecoder.decode(appUserVO.getUserName(), "UTF-8"));
                }
            }
        } catch (UnsupportedEncodingException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
        page.setList(appUserList);
        return page;
    }

    @Override
    public MapDto addAppUser(AppUser appUser) {

        MapDto mapDto = new MapDto();
        String password = MD5Tools.encode("123456");
        appUser.setPassword(password);
        int i = appUserMapper.addAppUser(appUser);
        if (i > 0) {
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "添加用户:" + appUser.getUserName(), "用户管理");
        } else {
            mapDto.setStatus(1);
        }
        return mapDto;
    }

    @Override
    public MapDto updateAppUser(AppUser appUser) {

        MapDto mapDto = new MapDto();
        int i = appUserMapper.updateAppUser(appUser);
        if (i > 0) {
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "修改用户:" + appUser.getUserName(), "用户管理");
        } else {
            mapDto.setStatus(1);
        }
        return mapDto;
    }

    @Override
    public MapDto enableAppUser(Long userId) {

        MapDto mapDto = new MapDto();
        int i = appUserMapper.enableAppUser(userId);
        if (i > 0) {
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null, "启用用户",
                    "用户管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败");
        }
        return mapDto;
    }

    @Override
    public MapDto disableAppUser(Long userId) {

        MapDto mapDto = new MapDto();
        int i = appUserMapper.disableAppUser(userId);
        if (i > 0) {
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null, "禁用用户",
                    "用户管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败");
        }
        return mapDto;
    }

    @Override
    public MapDto resetAppUserPassword(Long userId) {

        MapDto mapDto = new MapDto();
        String newPwd = MD5Tools.encode("123456");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("password", newPwd);
        map.put("id", userId);
        int i = appUserMapper.resetAppUserPassword(map);
        if (i > 0) {
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "重置用户密码", "用户管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败");
        }
        return mapDto;
    }

}
