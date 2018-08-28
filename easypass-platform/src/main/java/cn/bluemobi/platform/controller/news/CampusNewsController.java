
package cn.bluemobi.platform.controller.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.appCampus.AppCampus;
import cn.bluemobi.platform.entity.campusNews.CampusNews;
import cn.bluemobi.platform.service.AppCampusService;
import cn.bluemobi.platform.service.CampusNewsService;

@Controller
@RequestMapping("/admin/campusNews")
public class CampusNewsController extends PlatformBaseController {

    @Autowired
    private CampusNewsService campusNewsService;
    
    @Autowired
    private AppCampusService appCampusService;

    @RequestMapping("/toNewsPage")
    public String toNewsPage(Model model) {
    	List<AppCampus> campusList = appCampusService.getAllCampusList();
    	model.addAttribute("campusList", campusList);
        return "campusNews/newsList";
    }

    @RequestMapping("/findNews")
    @ResponseBody
    public DatatablePage findNews(@RequestParam("draw") Integer draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length) {
        return new DatatablePage(draw, campusNewsService.findNews(start, length));
    }

    @ResponseBody
    @RequestMapping("/addNews")
    public MapDto addNews(CampusNews campusNews) {

        return campusNewsService.addNews(campusNews);
    }

    @ResponseBody
    @RequestMapping("/updateNews")
    public MapDto updateNews(CampusNews campusNews) {

        return campusNewsService.updateNews(campusNews);
    }
    
    @ResponseBody
    @RequestMapping("/deleteNewsById")
    public MapDto deleteNewsById(@RequestParam("id") Long id) {

        return campusNewsService.deleteNewsById(id);
    }

}
