/**
 * Project Name:easypass-platform
 * File Name:AppCampusController.java
 * Package Name:cn.bluemobi.platform.controller.AppCampus
 * Date:2018年6月8日
 *
*/

package cn.bluemobi.platform.controller.appCampus;

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
import cn.bluemobi.platform.service.AppCampusService;
import cn.bluemobi.platform.vo.AppCampusVO;

/**
 * Description: appCampus管理<br/>
 * Date: 2018年6月8日 <br/>
 * 
 * @author justin
 * @version
 * @see
 */
@Controller
@RequestMapping("admin/appCampus")
public class AppCampusController extends PlatformBaseController {
    @Autowired
    private AppCampusService appCampusService;

    @RequestMapping("/toAppCampusPage")
    public String toAppCampusPage(Model model) {
        return "appCampus/appCampusList";
    }
    
    /**
     * 查询所有
     */
    @RequestMapping("/findAppCampusList")
    @ResponseBody
    public DatatablePage findAppCampusList(@RequestParam("draw") Integer draw, @RequestParam("start") Integer start,
            @RequestParam("length") Integer length, AppCampusVO appCampus) {
        return new DatatablePage(draw, appCampusService.findAppCampusListByPage(start, length, appCampus));
    }

    /**
     * 修改
     */
    @RequestMapping("updateAppCampus")
    @ResponseBody
    public MapDto updateAppCampus(AppCampus appCampus) {
        return appCampusService.updateAppCampus(appCampus);
    }

    /**
     * 添加
     */
    @RequestMapping("/addAppCampus")
    @ResponseBody
    public MapDto addAppCampus(AppCampus appCampus) {
        return appCampusService.addAppCampus(appCampus);
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteAppCampusById")
    @ResponseBody
    public MapDto deleteAppCampusById(@RequestParam("id") Long id) {
        return appCampusService.deleteAppCampusById(id);
    }
}
