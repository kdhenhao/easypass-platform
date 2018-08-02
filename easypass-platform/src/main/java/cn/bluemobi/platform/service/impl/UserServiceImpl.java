/**
 * Project Name:jrx
 * File Name:TestService.java
 * Package Name:cn.bluemobi.jrx.service
 * Date:2015年7月10日下午2:06:18
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.common.utils.DateUtils;
import cn.bluemobi.common.utils.MD5Tools;
import cn.bluemobi.platform.core.mybatis.PageUtils;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.mapper.UserMapper;
import cn.bluemobi.platform.service.LogService;
import cn.bluemobi.platform.service.UserService;

/**
 * Description: <br/>
 * Date: 2015年7月10日 下午2:06:18 <br/>
 * 用户service
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private LogService logService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 判断用户是否可以登录，不能返回null
     */
    public User login(String account, String password) {
        String passwordMd5 = MD5Tools.encode(password);
        User user = userMapper.loginByAccountAndPsd(account, passwordMd5);
        return user;
    }

    @Override
    public Page<User> findAllUsers(PageCondition cond) {
        Page<User> page = PageUtils.startPage(cond);
        page.setList(userMapper.searchUsers(cond.getMap()));
        return page;
    }

    @Override
    public List<Map<String, Object>> findAllRoles() {
        return userMapper.findAllRoles();
    }

    @Override
    public MapDto saveOrUpdate(User user, User opt) {
        MapDto data = new MapDto();
        String now = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        if (user.getId() == null) {
            if (userMapper.canInsertUser(user.getUsername()) > 0) {
                data.errorMsg("该账号已存在，无法再次添加");
            } else {
                user.setCreateBy(opt.getId());
                user.setCreateTm(now);
                user.setPassword(MD5Tools.encode(user.getPassword()));
                userMapper.insertUser(user);
                // 增加权限
                List<String> auths = userMapper.findRoleAuth(user.getRoleId());
                if (!CollectionUtils.isEmpty(auths)) {
                    for (String authId : auths) {
                        userMapper.insertUserAuth(user.getId(), authId);
                    }
                }
                logService.addLog(opt.getId(), null, "新增帐号：" + user.getUsername(), "系统管理");
            }
        } else {
            if (userMapper.canUpdateUser(user.getUsername(), user.getId()) > 0) {
                data.errorMsg("该账号修改后会与其他帐号 重复，无法修改");
            } else {
                user.setModifyBy(opt.getId());
                user.setModifyTm(now);
                userMapper.updateUser(user);
                // 增加权限
                userMapper.deleteUserAuth(user.getId());
                List<String> auths = userMapper.findRoleAuth(user.getRoleId());
                if (!CollectionUtils.isEmpty(auths)) {
                    for (String authId : auths) {
                        userMapper.insertUserAuth(user.getId(), authId);
                    }
                }
                logService.addLog(opt.getId(), null, "修改帐号：" + user.getUsername(), "系统管理");
            }
        }
        return data;
    }

    @Override
    public MapDto deleteById(String id, User user) {
        User target = userMapper.findById(id);
        userMapper.deleteUserById(id);
        logService.addLog(user.getId(), null, "删除帐号：" + target.getUsername(), "系统管理");
        return new MapDto();
    }

    @Override
    public MapDto updatePwd(String username, String currentPwd, String newPwd, User user) {
        MapDto MapDto = new MapDto();
        String p1 = MD5Tools.encode(currentPwd);
        String p2 = MD5Tools.encode(newPwd);
        int i = userMapper.updatePwd(p2, user.getId(), p1);
        if (i != 1) {
            MapDto.errorMsg("密码不正确，修改失败");
        }
        return MapDto;
    }

    @Override
    public List<String> findMyRolesByUserName(String username) {
        return userMapper.findMyRolesByUserName(username);
    }

    @Override
    public List<String> findMyAuthsByUserName(String username) {
        return userMapper.findMyAuthsByUserName(username);
    }

    @Override
    public MapDto resetPassword(String id, String name, User user) {
        logService.addLog(user.getId(), null, "重置用户密码，用户名：" + name, "系统管理");
        userMapper.updatePwdById(MD5Tools.encode("123456"), id);
        return new MapDto();
    }
}
