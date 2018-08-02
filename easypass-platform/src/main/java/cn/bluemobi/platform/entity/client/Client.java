/**
 * Project Name:lmExpress-platform
 * File Name:Client.java
 * Package Name:cn.bluemobi.platform.entity.client
 * Date:2016年11月7日下午3:21:42
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.client;

import java.io.Serializable;

/**
 * Description: <br/>
 * Date: 2016年11月7日 下午3:21:42 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String clientName;

    private String phone;

    private String provinceCode;

    private String province;

    private String cityCode;

    private String city;

    private String districtCode;

    private String district;

    private String address;

    private String role;

    private String hasPic;

    private String idcardNo;

    private String fontsidePic;

    private String backsidePic;

    private String mergesidePic;

    private Long createBy;

    private Long modifyBy;

    private Long orderId;// 传参用 和数据库无关

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getHasPic() {
        return hasPic;
    }

    public void setHasPic(String hasPic) {
        this.hasPic = hasPic;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getFontsidePic() {
        return fontsidePic;
    }

    public void setFontsidePic(String fontsidePic) {
        this.fontsidePic = fontsidePic;
    }

    public String getBacksidePic() {
        return backsidePic;
    }

    public void setBacksidePic(String backsidePic) {
        this.backsidePic = backsidePic;
    }

    public String getMergesidePic() {
        return mergesidePic;
    }

    public void setMergesidePic(String mergesidePic) {
        this.mergesidePic = mergesidePic;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}
