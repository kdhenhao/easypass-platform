/**
 * Project Name;//lmExpress-platform
 * File Name;//DepponSender.java
 * Package Name;//cn.bluemobi.platform.entity.deppon
 * Date;//2016年12月2日上午11;//16;//25
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.deppon;

import java.io.Serializable;

/**
 * Description;// <br/>
 * Date;// 2016年12月2日 上午11;//16;//25 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class DepponSender implements Serializable {
    private static final long serialVersionUID = 1L;

    private String address;// 北京中路100号,

    private String city;// 贵阳市,

    private String county;// 花溪区,

    private String mobile;// 15800000001,

    private String name;// 淘某某,

    private String phone;// ,

    private String province;// 贵州省

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
