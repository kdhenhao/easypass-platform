
package cn.bluemobi.platform.controller.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.campusNews.CampusNews;
import cn.bluemobi.platform.service.CampusNewsService;

@Controller
@RequestMapping("/admin/campusNews")
public class CampusNewsController extends PlatformBaseController {

    @Autowired
    private CampusNewsService campusNewsService;

    @RequestMapping("/toNewsPage")
    public String toNewsPage() {
        return "news/newsList";
    }

    @RequestMapping("/findNews")
    @ResponseBody
    public DatatablePage findNews(PageCondition cond) {
        return new DatatablePage(cond, campusNewsService.findNews(cond));
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

}
