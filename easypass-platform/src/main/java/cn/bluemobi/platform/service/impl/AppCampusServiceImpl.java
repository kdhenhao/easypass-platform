
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
import cn.bluemobi.platform.entity.appCampus.AppCampus;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.AppCampusMapper;
import cn.bluemobi.platform.service.AppCampusService;
import cn.bluemobi.platform.service.LogService;
import cn.bluemobi.platform.vo.AppCampusVO;

/**
 * Description: appCampus管理 <br/>
 * Date: 2017年6月18日 <br/>
 * 
 * @author justin
 * @version
 * @see
 */
@Service
public class AppCampusServiceImpl implements AppCampusService {

    @Autowired
    private AppCampusMapper appCampusMapper;
    //添加日志
    @Autowired
    private LogService logService;

    @Autowired
    private HttpSession session;

    @Override
    public Page<AppCampusVO> findAppCampusListByPage(Integer start, Integer length, AppCampusVO appCampus) {

        Page<AppCampusVO> page = PageUtils.startPage(start, length);
        Map<String, Object> map = new HashMap<String, Object>();

        List<AppCampusVO> appCampusList = appCampusMapper.findAppCampusListByPage(map);
        page.setList(appCampusList);
        return page;
    }

    @Override
    public MapDto updateAppCampus(AppCampus appCampus) {

        MapDto dto = new MapDto();
        int i = appCampusMapper.updateAppCampus(appCampus);
        if (i > 0) {
            dto.setStatus(0);
            dto.setMsg("编辑成功");
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "修改campus:" + appCampus.getName(), "campus管理");
        } else {
            dto.setStatus(1);
            dto.setMsg("编辑失败");
        }
        return dto;
    }

    @Override
    public MapDto addAppCampus(AppCampus appCampus) {

        MapDto mapDto = new MapDto();
        int i = appCampusMapper.addAppCampus(appCampus);
        if (i > 0) {
            mapDto.setStatus(0);
            mapDto.setMsg("数据添加成功");
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "添加campus:" + appCampus.getName(), "campus管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("数据添加失败");
        }
        return mapDto;
    }

    @Override
    public MapDto deleteAppCampusById(Long id) {

        MapDto dto = new MapDto();
        int i = appCampusMapper.deleteAppCampusById(id);
        if (i > 0) {
            dto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "删除campus", "campus管理");
        } else {
            dto.setStatus(1);
            dto.setMsg("数据删除失败");
        }
        return dto;
    }
    
    @Override
    public List<AppCampus> getAllCampusList() {
    	return appCampusMapper.getAllCampusList();
    }

}
