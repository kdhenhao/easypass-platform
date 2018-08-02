/**
 * Project Name:b2c-platform
 * File Name:UserRealm.java
 * Package Name:cn.bluemobi.platform.core.shiro
 * Date:2016年5月27日下午3:54:43
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.core.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import cn.bluemobi.platform.core.controller.PlatformBaseController;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.service.UserService;

/**
 * Description: <br/>
 * Date: 2016年5月27日 下午3:54:43 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 查询一个用户有哪些权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = principals.getPrimaryPrincipal().toString();
        // 角色名的集合
        Set<String> roles = new HashSet<String>();
        roles.addAll(userService.findMyRolesByUserName(username));
        // 权限名的集合
        Set<String> permissions = new HashSet<String>();
        permissions.addAll(userService.findMyAuthsByUserName(username));
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 登陆
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        User user = userService.login(username, password);
        if (user == null) {
            throw new AuthenticationException("用户名或密码错误");
        } else {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.getSession().setAttribute(PlatformBaseController.SESSION_USER, user);
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(username, password, "databaseRealm");
            return authcInfo;
        }
    }

}
