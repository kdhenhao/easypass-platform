/**
 * Project Name:easypass-platform
 * File Name:AppAboutUs.java
 * Package Name:cn.bluemobi.platform.entity.toolsConfig
 * Date:2017年8月11日上午12:25:51
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.toolsConfig;

/**
 * Description: 关于我们、联系方式 <br/>
 * Date: 2017年8月11日 上午12:25:51 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public class AppAboutUs {
    private Integer id;

    private String name;

    /**
     * logo图片
     */
    private String logoImg;

    private String telephone;

    private String description;

    /**
     * 微信公众号
     */
    private String wechatPublicNumber;

    private String email;

    /**
     * 二维码
     */
    private String qrCodeImg;

    private String modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWechatPublicNumber() {
        return wechatPublicNumber;
    }

    public void setWechatPublicNumber(String wechatPublicNumber) {
        this.wechatPublicNumber = wechatPublicNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQrCodeImg() {
        return qrCodeImg;
    }

    public void setQrCodeImg(String qrCodeImg) {
        this.qrCodeImg = qrCodeImg;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
