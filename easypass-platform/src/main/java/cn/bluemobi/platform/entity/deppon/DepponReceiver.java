/**
 * Project Name;//lmExpress-platform
 * File Name;//DepponReceiver.java
 * Package Name;//cn.bluemobi.platform.entity.deppon
 * Date;//2016年12月2日上午11;//13;//43
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.deppon;

import java.io.Serializable;

/**
 * Description;// <br/>
 * Date;// 2016年12月2日 上午11;//13;//43 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class DepponReceiver implements Serializable {

    private static final long serialVersionUID = 1L;

    private String address;// 明珠路1018号,

    private String city;// 上海市,

    private String county;// 青浦区,

    private String mobile;// 15800000000,

    private String name;// 宝某某,

    private String phone;// ,

    private String province;// 上海

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
