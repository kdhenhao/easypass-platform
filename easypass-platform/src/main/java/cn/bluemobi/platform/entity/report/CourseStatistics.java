package cn.bluemobi.platform.entity.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CourseStatistics implements Serializable {
    private static final long serialVersionUID = 23123123102L;

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
     * 支付时间
     */
    private Date payTime;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 支付方式
     */
    private String payWay;

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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

}
