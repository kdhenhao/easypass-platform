/**
 * Project Name lmExpress-platform
 * File Name DepponRequest.java
 * Package Name cn.bluemobi.platform.entity.deppon
 * Date 2016年12月2日上午11 07 13
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.deppon;

import java.io.Serializable;

/**
 * Description <br/>
 * Date 2016年12月2日 上午11 07 13 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class DepponRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String logisticID;// AL00012239673409,

    private String logisticCompanyID;// DEPPON,

    private String comments;// ,

    private String statusType;// GOT,

    private Long gmtUpdated;// 1361496717029,

    private String backSignBill;// 0,

    private String businessNetworkNo;// W011302020515,

    private String cargoName;// 干果,

    private Double codPrice;// 100.0,

    private String codType;// 1,

    private Double codValue;// 10000.0,

    private String deliveryPrice;// ,

    private String deliveryType;// 0,

    private Double insurancePrice;// 100.0,

    private Double insuranceValue;// 3000,

    private String mailNo;// 31350606,

    private String payType;// 0,

    private DepponReceiver receiver;//

    private DepponSender sender;

    private String smsNotify;// Y,

    private Double smsNotifyPrice;// 1.0,

    private String toNetworkNo;// W0013541,

    private Double totalNumber;// 12,

    private Double totalPrice;// 100.0,

    private Double totalVolume;// 63.0,

    private Double totalWeight;// 52.0,

    private String vistReceive;// Y,

    private Double vistReceivePrice;// 10.0

    public String getLogisticID() {
        return logisticID;
    }

    public void setLogisticID(String logisticID) {
        this.logisticID = logisticID;
    }

    public String getLogisticCompanyID() {
        return logisticCompanyID;
    }

    public void setLogisticCompanyID(String logisticCompanyID) {
        this.logisticCompanyID = logisticCompanyID;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public Long getGmtUpdated() {
        return gmtUpdated;
    }

    public void setGmtUpdated(Long gmtUpdated) {
        this.gmtUpdated = gmtUpdated;
    }

    public String getBackSignBill() {
        return backSignBill;
    }

    public void setBackSignBill(String backSignBill) {
        this.backSignBill = backSignBill;
    }

    public String getBusinessNetworkNo() {
        return businessNetworkNo;
    }

    public void setBusinessNetworkNo(String businessNetworkNo) {
        this.businessNetworkNo = businessNetworkNo;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public Double getCodPrice() {
        return codPrice;
    }

    public void setCodPrice(Double codPrice) {
        this.codPrice = codPrice;
    }

    public String getCodType() {
        return codType;
    }

    public void setCodType(String codType) {
        this.codType = codType;
    }

    public Double getCodValue() {
        return codValue;
    }

    public void setCodValue(Double codValue) {
        this.codValue = codValue;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Double getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(Double insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Double getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(Double insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public DepponReceiver getReceiver() {
        return receiver;
    }

    public void setReceiver(DepponReceiver receiver) {
        this.receiver = receiver;
    }

    public DepponSender getSender() {
        return sender;
    }

    public void setSender(DepponSender sender) {
        this.sender = sender;
    }

    public String getSmsNotify() {
        return smsNotify;
    }

    public void setSmsNotify(String smsNotify) {
        this.smsNotify = smsNotify;
    }

    public Double getSmsNotifyPrice() {
        return smsNotifyPrice;
    }

    public void setSmsNotifyPrice(Double smsNotifyPrice) {
        this.smsNotifyPrice = smsNotifyPrice;
    }

    public String getToNetworkNo() {
        return toNetworkNo;
    }

    public void setToNetworkNo(String toNetworkNo) {
        this.toNetworkNo = toNetworkNo;
    }

    public Double getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Double totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getVistReceive() {
        return vistReceive;
    }

    public void setVistReceive(String vistReceive) {
        this.vistReceive = vistReceive;
    }

    public Double getVistReceivePrice() {
        return vistReceivePrice;
    }

    public void setVistReceivePrice(Double vistReceivePrice) {
        this.vistReceivePrice = vistReceivePrice;
    }

}
