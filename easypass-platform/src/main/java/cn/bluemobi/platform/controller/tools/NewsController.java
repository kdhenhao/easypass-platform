/**
 * Project Name:lmExpress-platform
 * File Name:NewsController.java
 * Package Name:cn.bluemobi.platform.controller.tools
 * Date:2016年10月31日下午4:22:16
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.service.NewsService;

/**
 * Description: <br/>
 * Date: 2016年10月31日 下午4:22:16 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/news")
public class NewsController extends PlatformBaseController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/toNewsPage")
    public String toNewsPage() {
        return "tools/newsList";
    }

    @ResponseBody
    @RequestMapping("/findNews")
    public DatatablePage findNews(@RequestParam("draw") int draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length) {
        return new DatatablePage(draw, newsService.findNews(start, length));
    }

    @RequestMapping("/toEditPage")
    public String toEditPage(@RequestParam(required = false, value = "id") Long id, Model model) {
        if (id != null) {
            model.addAttribute("brochure", newsService.findById(id));
        }
        return "tools/newsEdit";
    }

    @ResponseBody
    @RequestMapping("/saveOrupdateNews")
    public MapDto saveOrupdateNews(@RequestParam("title") String title, @RequestParam("id") Long id,
            @RequestParam("content") String content) {
        return newsService.saveOrUpdateNews(id, title, content, getUser());
    }

    @ResponseBody
    @RequestMapping("/publishOrupdateNews")
    public MapDto publishOrupdateNews(@RequestParam("title") String title, @RequestParam("id") Long id,
            @RequestParam("content") String content) {
        return newsService.publishOrupdateNews(id, title, content, getUser());
    }

    @ResponseBody
    @RequestMapping("/deleteById")
    public MapDto deleteById(@RequestParam("id") Long id) {
        return newsService.deleteById(id, getUser());
    }

    @RequestMapping("/publishById")
    @ResponseBody
    public MapDto publishById(@RequestParam("id") Long id) {
        return newsService.publishById(id, getUser());
    }
}
