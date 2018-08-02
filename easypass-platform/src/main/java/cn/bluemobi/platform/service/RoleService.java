/**
 * Project Name:demo
 * File Name:RoleService.java
 * Package Name:cn.bluemobi.demo.service
 * Date:2015年12月10日下午5:21:00
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.List;
import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.system.Auth;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2015年12月10日 下午5:21:00 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface RoleService {

    Page<Map<String, Object>> findAllRoles(int pageNo, int pageSize);

    MapDto saveOrUpdataRole(String id, String roleName, String roleCode, User user);

    MapDto deleteRole(String id, User user);

    List<Auth> getAuthList();

    MapDto findRoleAuth(String roleId);

    MapDto saveAndFlush(String id, String array, User user);

}
