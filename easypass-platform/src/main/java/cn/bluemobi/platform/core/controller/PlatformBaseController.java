/**
 * Project Name:jrx
 * File Name:BackendBaseController.java
 * Package Name:cn.bluemobi.jrx.core.controller
 * Date:2015年7月13日上午11:42:13
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.core.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.utils.BigDecimalEditor;
import cn.bluemobi.common.core.utils.BlueMobiConstants;
import cn.bluemobi.platform.core.utils.PlatformConstants;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.service.RoleService;
import cn.bluemobi.platform.utils.JsonUtils;

/**
 * Description: <br/>
 * Date: 2015年7月13日 上午11:42:13 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class PlatformBaseController extends BaseController {

    public static final String SESSION_USER = "login_user";

    public static final String SESSION_IDENTIFY_CODE = "auth_code";

    protected static final Logger log = LogManager.getLogger(PlatformBaseController.class);

    @Autowired
    protected RoleService roleService;

    /**
     * 返回默认的基本参数 Description: <br/>
     */
    @ModelAttribute
    public void getBaseParam(HttpServletRequest request, Model model) {
        model.addAttribute("ctx", getContextPath(request));
        model.addAttribute("domain", getDomain(request));
        model.addAttribute("theme", PlatformConstants.THEME);
        model.addAttribute("siteName", PlatformConstants.SITE_NAME);
        model.addAttribute("imgDomain", PlatformConstants.IMG_CONTEXT_PATH);
        model.addAttribute("menus", roleService.getAuthList());
    }

    /**
     * 如果发生异常，将默认返回错误页面 Description: <br/>
     */
    @ExceptionHandler(Throwable.class)
    protected String handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error(ExceptionUtils.getStackTrace(ex));

        String requestType = request.getHeader("X-Requested-With");
        if (StringUtils.equals(requestType, "XMLHttpRequest")) {
            // 是ajax请求
            MapDto data = new MapDto();
            data.setStatus(BlueMobiConstants.FAIL);
            data.setMsg(ExceptionUtils.getMessage(ex));
            PrintWriter out = null;
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/json;charset=UTF-8");
                out = response.getWriter();
                out.print(JsonUtils.toJson(data));
            } catch (Exception e) {
            } finally {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            }
            return null;
        } else {
            return "error/500";
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        BigDecimalEditor bigDecimalEditor = new BigDecimalEditor();
        binder.registerCustomEditor(BigDecimal.class, bigDecimalEditor);
    }

    /**
     * 获取当前登陆的用户，如果没登陆，返回null
     */
    protected User getUser() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession(false);
        if (session == null) {
            return null;
        } else {
            return (User) session.getAttribute(SESSION_USER);
        }
    }
}
