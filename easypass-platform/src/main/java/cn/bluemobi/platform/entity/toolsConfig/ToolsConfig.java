/**
 * Project Name:easypass-platform
 * File Name:ToolsConfig.java
 * Package Name:cn.bluemobi.platform.entity.toolsConfig
 * Date:2017年5月23日下午15:17:17
 * Copyright (c) 2017, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.entity.toolsConfig;

public class ToolsConfig {

    private long id;

    private String name;

    private String configType;

    private String content;

    private String userName;

    private String createTime;

    private String modifyTime;

    private long modifyBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(long modifyBy) {
        this.modifyBy = modifyBy;
    }

}
