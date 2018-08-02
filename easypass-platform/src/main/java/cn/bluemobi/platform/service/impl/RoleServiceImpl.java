/**
 * Project Name:demo
 * File Name:RoleServiceImpl.java
 * Package Name:cn.bluemobi.demo.service.impl
 * Date:2015年12月10日下午5:22:15
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.system.Auth;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.RoleMapper;
import cn.bluemobi.platform.service.LogService;
import cn.bluemobi.platform.service.RoleService;

/**
 * Description: <br/>
 * Date: 2015年12月10日 下午5:22:15 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private LogService logService;

    @Autowired
    private EhCacheCacheManager cacheManager;

    @Override
    public Page<Map<String, Object>> findAllRoles(int start, int length) {
        Page<Map<String, Object>> page = PageUtils.startPage(start, length);
        page.setList(roleMapper.findAllRoles());
        return page;
    }

    @Override
    public MapDto saveOrUpdataRole(String id, String roleName, String roleCode, User user) {
        MapDto data = new MapDto();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (StringUtils.isBlank(id)) {
            // 新增
            if (roleMapper.canInsert(roleName, roleCode) > 0) {
                data.errorMsg("角色名称或代码已存在，不能新增");
            } else {
                roleMapper.insertRole(roleName, roleCode, user.getId(), now);
            }
            logService.addLog(user.getId(), null, "新增角色：" + roleName, "系统管理");
        } else {
            // 修改
            if (roleMapper.canUpdate(roleName, roleCode, id) > 0) {
                data.errorMsg("角色名称或代码已存在，不能新增");
            } else {
                roleMapper.updateRole(roleName, roleCode, user.getId(), now, id);
            }
            logService.addLog(user.getId(), null, "更新角色：" + roleName, "系统管理");
        }
        return data;
    }

    @Override
    public MapDto deleteRole(String id, User user) {
        MapDto data = new MapDto();
        if (roleMapper.canDelete(id) > 0) {
            data.errorMsg("该角色下还有帐号，不能删除");
        } else {
            roleMapper.deleteRole(id);
            logService.addLog(user.getId(), null, "删除角色", "系统管理");
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Auth> getAuthList() {
        // 查缓存
        ValueWrapper wrapper = cacheManager.getCache("commonCache").get("authList");
        if (wrapper != null && wrapper.get() != null) {
            return (List<Auth>) wrapper.get();
        }
        // 重新查
        List<Map<String, Object>> list = roleMapper.finAllAuth();
        Map<String, Auth> map = new HashMap<String, Auth>();
        for (Map<String, Object> auth : list) {
            Auth child = new Auth();
            child.setId(auth.get("id1").toString());
            child.setAuthName(auth.get("auth_name1").toString());
            child.setHref(auth.get("href1").toString());
            child.setImg(auth.get("img1") == null ? "" : auth.get("img1").toString());

            String parentId = auth.get("id").toString();
            if (map.containsKey(parentId)) {
                Auth parent = map.get(parentId);
                parent.getChildren().add(child);
            } else {
                Auth parent = new Auth();
                parent.setId(auth.get("id").toString());
                parent.setAuthName(auth.get("auth_name").toString());
                // parent.setHref(auth.get("HREF").toString());
                parent.setImg(auth.get("img").toString());
                parent.setOrder((Integer) auth.get("order"));
                parent.setChildren(new ArrayList<Auth>());
                parent.getChildren().add(child);
                map.put(parentId, parent);
            }
        }
        List<Auth> res = new ArrayList<Auth>();
        res.addAll(map.values());
        Collections.sort(res, new Comparator<Auth>() {
            @Override
            public int compare(Auth o1, Auth o2) {
                return o1.getOrder() - o2.getOrder();
            }
        });
        cacheManager.getCache("commonCache").put("authList", res);
        return res;
    }

    @Override
    public MapDto findRoleAuth(String roleId) {
        MapDto data = new MapDto();
        data.putInData("list", roleMapper.findRoleAuth(roleId));
        return data;
    }

    @Override
    public MapDto saveAndFlush(String id, String array, User user) {
        logService.addLog(user.getId(), null, "修改角色权限", "系统管理");
        roleMapper.deleteRoleAuths(id);
        String[] auths = array.split(",");
        for (String auth : auths) {
            roleMapper.insertRoleAuth(id, auth);
        }
        // 删除所有用户权限
        roleMapper.deleteUserAuthByRole(id);
        List<Long> userIds = roleMapper.findAllUserIdByRoleId(id);
        if (!CollectionUtils.isEmpty(userIds)) {
            for (Long userId : userIds) {
                for (String auth : auths) {
                    roleMapper.insertUserAuth(userId, auth);
                }
            }
        }
        return new MapDto();
    }

}
