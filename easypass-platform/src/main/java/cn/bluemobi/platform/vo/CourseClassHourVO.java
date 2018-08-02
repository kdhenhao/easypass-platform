/**
 * Project Name:easypass-platform
 * File Name:CourseClassHourVO.java
 * Package Name:cn.bluemobi.platform.vo
 * Date:2017年5月25日下午6:22:38
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.vo;

import java.math.BigDecimal;

/**
 * Description: 课时VO<br/>
 * Date: 2017年5月25日 下午6:22:38 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public class CourseClassHourVO {
    private Integer id;

    /**
     * 课程id
     */
    private Integer courseId;

    private String courseName;

    private String classHourName;

    /**
     * 课时章节
     */
    private String lessonPeriod;

    private String content;

    private BigDecimal price;
    
    private Integer points;

    /**
     * 苹果商店价格
     */
    private BigDecimal priceIos;

    private String video;

    private String videoHttpUrl;
    
    private BigDecimal clickNum;
    
    private BigDecimal purchaseNum;
    
    private BigDecimal clickNumReal;
    
    private BigDecimal purchaseNumReal;

    private String createTime;

    private String modifyTime;

    private String beginTime;

    private String endTime;

    /**
     * 对应苹果商品ID
     */
    private String appleProductId;

    /**
     * 税额
     */
    private BigDecimal onTax;

    /**
     * 排序
     */
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public BigDecimal getPriceIos() {
        return priceIos;
    }

    public void setPriceIos(BigDecimal priceIos) {
        this.priceIos = priceIos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public String getLessonPeriod() {
        return lessonPeriod;
    }

    public void setLessonPeriod(String lessonPeriod) {
        this.lessonPeriod = lessonPeriod;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoHttpUrl() {
        return videoHttpUrl;
    }

    public void setVideoHttpUrl(String videoHttpUrl) {
        this.videoHttpUrl = videoHttpUrl;
    }

    public BigDecimal getClickNum() {
        return clickNum;
    }

    public void setClickNum(BigDecimal clickNum) {
        this.clickNum = clickNum;
    }
    
    public BigDecimal getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(BigDecimal purchaseNum) {
        this.purchaseNum = purchaseNum;
    }
    
    public BigDecimal getClickNumReal() {
        return clickNumReal;
    }

    public void setClickNumReal(BigDecimal clickNumReal) {
        this.clickNumReal = clickNumReal;
    }
    
    public BigDecimal getPurchaseNumReal() {
        return purchaseNumReal;
    }

    public void setPurchaseNumReal(BigDecimal purchaseNumReal) {
        this.purchaseNumReal = purchaseNumReal;
    }
    
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAppleProductId() {
        return appleProductId;
    }

    public void setAppleProductId(String appleProductId) {
        this.appleProductId = appleProductId;
    }

    public BigDecimal getOnTax() {
        return onTax;
    }

    public void setOnTax(BigDecimal onTax) {
        this.onTax = onTax;
    }
    
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
