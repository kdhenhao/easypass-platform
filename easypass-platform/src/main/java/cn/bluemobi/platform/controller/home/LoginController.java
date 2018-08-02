/**
 * Project Name:mfh-web
 * File Name:LoginController.java
 * Package Name:cn.bluemobi.web.controller.home
 * Date:2016年6月14日下午1:40:37
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.home;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.utils.BlueMobiConstants;
import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.utils.JsonUtils;

/**
 * Description: 登陆 <br/>
 * Date: 2016年6月14日 下午1:40:37 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Controller
public class LoginController extends PlatformBaseController {

    /**
     * 跳转到登陆页
     */
    @RequestMapping("/toLoginPage")
    public String toLoginPage() {
        return "home/login";
    }

    /**
     * 返回验证码
     */
    @RequestMapping("/identifyCode")
    public void identifyCode(HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        int width = 60, height = 34;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // 想文字旋转一定的角度
            AffineTransform trans = new AffineTransform();
            int dir = random.nextBoolean() ? 1 : -1;
            trans.rotate(dir * random.nextInt(45) * 3.14 / 180, 15 * i + 8, 7);
            // 缩放文字
            float scaleSize = random.nextFloat() + 0.8f;
            if (scaleSize > 1f)
                scaleSize = 1f;
            trans.scale(scaleSize, scaleSize);
            g.setTransform(trans);
            g.drawString(rand, 13 * i + 6, 24);
        }
        // 将认证码存入SESSION
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getSession().setAttribute(SESSION_IDENTIFY_CODE, sRand);
        g.dispose();
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 登录
     */
    @RequestMapping("/login")
    public String login(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "identifyCode", required = false) String identifyCode, Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            // 已经登陆，跳转到首页
            return "home/home";
        }
        // 检查参数
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(identifyCode)) {
            model.addAttribute("errorMsg", "请填写完整的登陆信息");
            model.addAttribute("username", username);
            return "home/login";
        }

        Session session = currentUser.getSession();
        if (session == null || session.getAttribute(SESSION_IDENTIFY_CODE) == null
                || !StringUtils.equals(session.getAttribute(SESSION_IDENTIFY_CODE).toString(), identifyCode)) {
            model.addAttribute("errorMsg", "验证码不正确，请重新输入");
            model.addAttribute("username", username);
            return "home/login";
        }
        // 登陆
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            currentUser.login(token);
        } catch (AuthenticationException e) {
            // 登陆失败
            model.addAttribute("errorMsg", e.getMessage());
            model.addAttribute("username", username);
            return "home/login";
        }
        // 登陆成功，跳转到首页
        return "home/home";
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject user = SecurityUtils.getSubject();
        user.logout();
        return "home/login";
    }

    /**
     * 没有权限
     */
    @RequestMapping("/unauthorizedUrl")
    public String unauthorizedUrl(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        // 未登陆
        String requestType = req.getHeader("X-Requested-With");
        if (StringUtils.equals(requestType, "XMLHttpRequest")) {
            // 是ajax请求
            MapDto data = new MapDto();
            data.setStatus(BlueMobiConstants.FAIL);
            data.setMsg("您没有权限访问该页面，或您长时间未操作而导致会话过期，请尝试重新登陆");
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
            return "error/401";
        }
    }
}
