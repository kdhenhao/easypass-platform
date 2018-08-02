/**
 * Project Name:lmExpress-platform
 * File Name:OrderMain.java
 * Package Name:cn.bluemobi.platform.entity.order
 * Date:2016年11月9日下午2:47:18
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description: <br/>
 * Date: 2016年11月9日 下午2:47:18 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class OrderMain implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String orderNo;// varchar(25),

    private String senderName; // varchar(20),

    private String senderPhone; // varchar(20),

    private String senderProvince; // varchar(20),

    private String senderCity; // varchar(20),

    private String senderDistrict; // varchar(20),

    private String senderAddress; // varchar(50),

    private String receiverName; // varchar(20),

    private String receiverPhone;// varchar(20),

    private String receiverProvince;// varchar(20),

    private String receiverProvinceCode;

    private String receiverCity;// varchar(20),

    private String receiverCityCode;

    private String receiverDistrict;// varchar(20),

    private String receiverDistrictCode;// varchar(8),

    private String receiverAddress;// varchar(50),

    private Long endpointId;// bigint,

    private Double totalWeight;// double,

    private BigDecimal totalPrice;// decimal(12,2),

    private String payMethod;// varchar(20),

    private String commodityIds;

    private String updateIds;

    private String counts;

    private Long createBy;

    private Long modifyBy;

    private String hasPic;

    private String idcardNo;

    private String fontsidePic;

    private String backsidePic;

    private String mergesidePic;

    private String fakeIdcardNo;

    private String fakeFontsidePic;

    private String fakeBacksidePic;

    private String fakeMergesidePic;

    private Long palletId;

    private String palletName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderProvince() {
        return senderProvince;
    }

    public void setSenderProvince(String senderProvince) {
        this.senderProvince = senderProvince;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderDistrict() {
        return senderDistrict;
    }

    public void setSenderDistrict(String senderDistrict) {
        this.senderDistrict = senderDistrict;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    public String getReceiverDistrictCode() {
        return receiverDistrictCode;
    }

    public void setReceiverDistrictCode(String receiverDistrictCode) {
        this.receiverDistrictCode = receiverDistrictCode;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getCommodityIds() {
        return commodityIds;
    }

    public void setCommodityIds(String commodityIds) {
        this.commodityIds = commodityIds;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public Long getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(Long endpointId) {
        this.endpointId = endpointId;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getReceiverProvinceCode() {
        return receiverProvinceCode;
    }

    public void setReceiverProvinceCode(String receiverProvinceCode) {
        this.receiverProvinceCode = receiverProvinceCode;
    }

    public String getReceiverCityCode() {
        return receiverCityCode;
    }

    public void setReceiverCityCode(String receiverCityCode) {
        this.receiverCityCode = receiverCityCode;
    }

    public String getUpdateIds() {
        return updateIds;
    }

    public void setUpdateIds(String updateIds) {
        this.updateIds = updateIds;
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

    public Long getPalletId() {
        return palletId;
    }

    public void setPalletId(Long palletId) {
        this.palletId = palletId;
    }

    public String getMergesidePic() {
        return mergesidePic;
    }

    public void setMergesidePic(String mergesidePic) {
        this.mergesidePic = mergesidePic;
    }

    public String getFakeIdcardNo() {
        return fakeIdcardNo;
    }

    public void setFakeIdcardNo(String fakeIdcardNo) {
        this.fakeIdcardNo = fakeIdcardNo;
    }

    public String getFakeFontsidePic() {
        return fakeFontsidePic;
    }

    public void setFakeFontsidePic(String fakeFontsidePic) {
        this.fakeFontsidePic = fakeFontsidePic;
    }

    public String getFakeBacksidePic() {
        return fakeBacksidePic;
    }

    public void setFakeBacksidePic(String fakeBacksidePic) {
        this.fakeBacksidePic = fakeBacksidePic;
    }

    public String getFakeMergesidePic() {
        return fakeMergesidePic;
    }

    public void setFakeMergesidePic(String fakeMergesidePic) {
        this.fakeMergesidePic = fakeMergesidePic;
    }

    public String getPalletName() {
        return palletName;
    }

    public void setPalletName(String palletName) {
        this.palletName = palletName;
    }

}
