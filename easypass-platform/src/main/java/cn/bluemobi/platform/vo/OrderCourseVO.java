/**
 * Project Name:easypass-platform
 * File Name:OrderCourseVO.java
 * Package Name:cn.bluemobi.platform.vo
 * Date:2017年6月1日下午2:01:36
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.vo;
/**
 * Description:	 订单VO  <br/>
 * Date:     2017年6月1日 下午2:01:36 <br/>
 * @author   oscarwang
 * @version  
 * @see 	 
 */

import java.math.BigDecimal;

public class OrderCourseVO {
    private Integer id;

    private String orderNo;

    private String userName;

    private BigDecimal price;
    
    private Integer points;

    private String phone;

    private Integer orderStatus;

    private String orderStatusStr;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusStr() {
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
