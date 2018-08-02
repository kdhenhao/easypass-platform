/**
 * Project Name:easypass-platform Maven Webapp
 * File Name:OrderQuery.java
 * Package Name:cn.bluemobi.platform.entity.report
 * Date:2017年8月3日下午3:11:53
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bluemobi.platform.entity.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: <br/>
 * Date: 2017年8月3日 下午3:11:53 <br/>
 * 
 * @author zhangcong
 * @version
 * @see
 */
public class OrderQuery implements Serializable {
    private static final long serialVersionUID = 112312312312L;

    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课时名称
     */
    private String classHourName;

    /**
     * 购买数量
     */
    private Long quantity;

    /**
     * 课程还是课时的判断1：课程 0：课时
     */
    private Long courseFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 订单价格
     */
    private BigDecimal price;

    /**
     * 支付方式
     */
    private String payWay;

    /**
     * 订单价格总和
     */
    private BigDecimal totalPrice;

    public OrderQuery() {
        super();
    }

    public OrderQuery(Long id, String orderNo, String courseName, String classHourName, Long quantity, Long courseFlag,
            Date createTime, BigDecimal price, String payWay, BigDecimal totalPrice) {
        super();
        this.id = id;
        this.orderNo = orderNo;
        this.courseName = courseName;
        this.classHourName = classHourName;
        this.quantity = quantity;
        this.courseFlag = courseFlag;
        this.createTime = createTime;
        this.price = price;
        this.payWay = payWay;
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassHourName() {
        return classHourName;
    }

    public void setClassHourName(String classHourName) {
        this.classHourName = classHourName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getCourseFlag() {
        return courseFlag;
    }

    public void setCourseFlag(Long courseFlag) {
        this.courseFlag = courseFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
