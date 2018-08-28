/**
 * Project Name:easypass-platform
 * File Name:AppUser.java
 * Package Name:cn.bluemobi.platform.entity.appUser
 * Date:2017年5月27日下午12:05:06
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.vo;

/**
 * Description: APP校区表<br/>
 * Date: 2018年6月18日<br/>
 * 
 * @author justin
 * @version
 * @see
 */
public class AppCampusVO {
	
	private Integer id;

    private String campusName;
    
    private String name;

    private String logoImg;

    private String backgroundImg;
    
    private String createTime;

    private String modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
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
}
