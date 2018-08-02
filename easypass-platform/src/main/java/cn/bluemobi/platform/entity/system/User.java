/**
 * Project Name:jrx
 * File Name:User.java
 * Package Name:cn.bluemobi.jrx.entity.system
 * Date:2015年7月14日下午1:48:23
 * Copyright (c) 2015; bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.entity.system;

import java.io.Serializable;

/**
 * Description: <br/>
 * Date: 2015年7月14日 下午1:48:23 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class User implements Serializable {
    private static final long serialVersionUID = 4763589246941117992L;

    private Long id;

    private String username;

    private String password;

    private String isAdmin;

    private Long roleId;

    private String roleName;

    private String fullName;

    private String phone;

    private String email;

    private String address;

    private String remark;

    private Long createBy;

    private Long modifyBy;

    private String createTm;

    private String modifyTm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getCreateTm() {
        return createTm;
    }

    public void setCreateTm(String createTm) {
        this.createTm = createTm;
    }

    public String getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(String modifyTm) {
        this.modifyTm = modifyTm;
    }

}
