/**
 * Project Name:easypass-platform
 * File Name:OrderCourseDetailVO.java
 * Package Name:cn.bluemobi.platform.vo
 * Date:2017年6月1日下午3:54:05
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.vo;
/**
 * Description:	 订单详情VO  <br/>
 * Date:     2017年6月1日 下午3:54:05 <br/>
 * @author   oscarwang
 * @version  
 * @see 	 
 */

import java.math.BigDecimal;

public class OrderCourseDetailVO {
    private Integer id;

    private String courseName;

    private String classHourName;

    private BigDecimal price;
    
    private Integer points;

    /**
     * 数量
     */
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
