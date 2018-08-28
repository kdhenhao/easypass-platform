
package cn.bluemobi.platform.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.campusNews.CampusNews;
import cn.bluemobi.platform.mapper.CampusNewsMapper;
import cn.bluemobi.platform.service.CampusNewsService;
import cn.bluemobi.platform.service.LogService;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2018年6月21日 <br/>
 * 
 * @author justin
 * @version
 * @see
 */
@Service
@Transactional
public class CampusNewsServiceImpl implements CampusNewsService {

    @Autowired
    private CampusNewsMapper campusNewsMapper;
    
    @Autowired
    private LogService logService;

    @Autowired
    private HttpSession session;

    @Override
    public MapDto addNews(CampusNews campusNews) {
        MapDto mapDto = new MapDto();
        int i = campusNewsMapper.addNews(campusNews);
        if (i > 0) { // 插入成功
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "新增新闻:" + campusNews.getCampusUrl(), "新闻管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败");
        }

        return mapDto;
    }

    @Override
    public MapDto updateNews(CampusNews campusNews) {
        MapDto mapDto = new MapDto();
        int i = campusNewsMapper.updateNews(campusNews);
        if (i > 0) { // 修改成功
            mapDto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "更新新闻:" + campusNews.getCampusUrl(), "新闻管理");
        } else {
            mapDto.setStatus(1);
            mapDto.setMsg("服务器操作失败错");
        }

        return mapDto;
    }

    @Override
    public Page<CampusNews> findNews(Integer start, Integer length) {
        Page<CampusNews> page = PageUtils.startPage(start, length);
        List<CampusNews> newsList = campusNewsMapper.findNews();
        page.setList(newsList);
        return page;
    }
    
    @Override
    public MapDto deleteNewsById(Long id) {

        MapDto dto = new MapDto();
        int i = campusNewsMapper.deleteNewsById(id);
        if (i > 0) {
            dto.setStatus(0);
            logService.addLog(((User) session.getAttribute(PlatformBaseController.SESSION_USER)).getId(), null,
                    "删除新闻", "新闻管理");
        } else {
            dto.setStatus(1);
            dto.setMsg("数据删除失败");
        }
        return dto;
    }

}
