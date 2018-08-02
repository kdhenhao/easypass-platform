/**
 * Project Name:lmExpress-platform
 * File Name:TaxController.java
 * Package Name:cn.bluemobi.platform.controller.commodity
 * Date:2016年11月2日下午4:17:51
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.commodity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.core.dto.DatatablePage;
import cn.bluemobi.platform.entity.commodity.Tax;
import cn.bluemobi.platform.service.TaxService;

/**
 * Description: <br/>
 * Date: 2016年11月2日 下午4:17:51 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
@RequestMapping("/admin/tax")
public class TaxController extends PlatformBaseController {

    @Autowired
    private TaxService taxService;

    @RequestMapping("/toTaxPage")
    public String toTaxPage() {

        return "commodity/taxList";
    }

    @ResponseBody
    @RequestMapping("/findTaxs")
    public DatatablePage findTaxs(PageCondition cond) {
        return new DatatablePage(cond, taxService.findTaxs(cond));
    }

    @RequestMapping("/lockById")
    @ResponseBody
    public MapDto lockById(@RequestParam("id") Long id) {
        return taxService.lockById(id, getUser());
    }

    @RequestMapping("/unLockById")
    @ResponseBody
    public MapDto unLockById(@RequestParam("id") Long id) {
        return taxService.unLockById(id, getUser());
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public MapDto saveOrUpdate(Tax tax) {
        return taxService.saveOrUpdate(tax, getUser());
    }
}
