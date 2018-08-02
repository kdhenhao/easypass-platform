/**
 * Project Name:jrx
 * File Name:TestService.java
 * Package Name:cn.bluemobi.jrx.service
 * Date:2015年7月10日下午2:06:18
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.List;
import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2015年7月10日 下午2:06:18 <br/>
 * 用户service
 * 
 * @author hut
 * @version
 * @see
 */
public interface UserService {

    /**
     * 判断用户是否可以登录，不能返回null Description: <br/>
     */
    User login(String account, String password);

    /**
     * 搜索用户 Description: <br/>
     */
    Page<User> findAllUsers(PageCondition cond);

    List<Map<String, Object>> findAllRoles();

    MapDto saveOrUpdate(User user, User opt);

    MapDto deleteById(String id, User user);

    MapDto updatePwd(String username, String currentPwd, String newPwd, User user);

    /**
     * 查询这个用户有哪些角色
     */
    List<String> findMyRolesByUserName(String username);

    /**
     * 查询这个用户有那些权限
     */
    List<String> findMyAuthsByUserName(String username);

    /**
     * 重置密码
     */
    MapDto resetPassword(String id, String name, User user);
}
