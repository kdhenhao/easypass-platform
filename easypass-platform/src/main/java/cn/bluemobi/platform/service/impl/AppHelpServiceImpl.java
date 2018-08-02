/**
 * Project Name:easypass-platform
 * File Name:AppHelpServiceImpl.java
 * Package Name:cn.bluemobi.platform.service.impl
 * Date:2017年6月23日下午5:07:54
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.appHelp.AppHelp;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.AppHelpMapper;
import cn.bluemobi.platform.service.AppHelpService;
import cn.bluemobi.platform.service.LogService;

/**
 * Description: <br/>
 * Date: 2017年6月23日 下午5:07:54 <br/>
 * 
 * @author wuya
 * @version
 * @see
 */
@Transactional
@Service
public class AppHelpServiceImpl implements AppHelpService {

    @Autowired
    private AppHelpMapper helpMapper;

    @Autowired
    private LogService logService;

    @Autowired
    private HttpSession session;

    @Override
    public Page<AppHelp> findHelpList(Integer start, Integer length, AppHelp help) {
        Page<AppHelp> page = PageUtils.startPage(start, length); // 手动设置分页
        Map<String, Object> map = new HashMap<>();
        map.put("helpHeader", help.getHelpHeader());
        List<AppHelp> helps = helpMapper.findHelpList(map);
        page.setList(helps);
        return page;
    }

    @Override
    public MapDto addHelp(AppHelp help) {
        MapDto mapDto = new MapDto();
        int i = helpMapper.insertHelp(help);
        if (i > 0) { // 插入成功
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "添加帮助,序号:" + help.getHelpSeq(), "使用帮助管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败");
        }

        return mapDto;
    }

    @Override
    public MapDto updateHelp(AppHelp help) {
        MapDto mapDto = new MapDto();
        int i = helpMapper.updedateHelp(help);
        if (i > 0) { // 修改成功
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "修改帮助信息,序号:" + help.getHelpSeq(), "使用帮助管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败");
        }

        return mapDto;
    }

    @Override
    public MapDto deleteHelpById(Integer id) {
        MapDto mapDto = new MapDto();
        int i = helpMapper.deleteHelpById(id);
        if (i > 0) { // 修改成功
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "删除帮助信息", "使用帮助管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败");
        }

        return mapDto;
    }

}
