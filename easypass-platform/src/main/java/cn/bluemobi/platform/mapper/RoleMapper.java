/**
 * Project Name:demo
 * File Name:RoleMapper.java
 * Package Name:cn.bluemobi.demo.mapper
 * Date:2015年12月10日下午5:23:41
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2015年12月10日 下午5:23:41 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface RoleMapper {

    List<Map<String, Object>> findAllRoles();

    int canInsert(String roleName, String roleCode);

    int insertRole(String roleName, String roleCode, Long user, Timestamp now);

    int canUpdate(String roleName, String roleCode, String id);

    int updateRole(String roleName, String roleCode, Long user, Timestamp now, String id);

    int canDelete(String id);

    int deleteRole(String id);

    List<Map<String, Object>> finAllAuth();

    List<String> findRoleAuth(String roleId);

    /**
     * 删除角色所有权限 Description: <br/>
     */
    int deleteRoleAuths(String id);

    int insertRoleAuth(String id, String auth);

    int deleteUserAuthByRole(String id);

    List<Long> findAllUserIdByRoleId(String id);

    int insertUserAuth(Long userId, String auth);

}
