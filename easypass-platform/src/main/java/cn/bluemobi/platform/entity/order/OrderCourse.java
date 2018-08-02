/**
 * Project Name:easypass-platform
 * File Name:OrderCourse.java
 * Package Name:cn.bluemobi.platform.entity.order
 * Date:2017年6月1日下午1:58:26
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description: 订单实体 <br/>
 * Date: 2017年6月1日 下午1:58:26 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public class OrderCourse implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 4736916779099724407L;

    private Integer id;

    private Integer userId;

    private String orderNo;

    private BigDecimal price;
    
    private Integer points;

    private Integer status;

    private Integer payway;

    private String cretateTime;

    private String payTime;

    private String modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayway() {
        return payway;
    }

    public void setPayway(Integer payway) {
        this.payway = payway;
    }

    public String getCretateTime() {
        return cretateTime;
    }

    public void setCretateTime(String cretateTime) {
        this.cretateTime = cretateTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
