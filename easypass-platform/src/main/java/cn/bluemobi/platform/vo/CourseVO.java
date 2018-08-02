/**
 * Project Name:easypass-platform
 * File Name:CourseVO.java
 * Package Name:cn.bluemobi.platform.vo
 * Date:2017年5月25日下午12:47:39
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.vo;

import java.math.BigDecimal;

/**
 * Description: 课程VO对象<br/>
 * Date: 2017年5月25日 下午12:47:39 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public class CourseVO {
    private Integer id;

    /**
     * 所属专业分类
     */
    private Integer classifyId;

    /**
     * 专业名称
     */
    private String classifyName;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 学分
     */
    private Integer credit;

    /**
     * 难度（五颗星表示）
     */
    private Integer difficulty;

    /**
     * 课程详情
     */
    private String courseDetail;

    /**
     * 课时
     */
    private Integer classHour;

    /**
     * 价格
     */
    private BigDecimal price;
    
    /**
     * 积分
     */
    private Integer points;

    /**
     * 苹果商店价格
     */
    private BigDecimal priceIos;

    /**
     * 税额
     */
    private BigDecimal onTax;

    /**
     * 课程视频
     */
    private String video;

    /**
     * 视频外链
     */
    private String videoHttpUrl;

    /**
     * 课程老师
     */
    private String teacher;

    /**
     * 老师描述
     */
    private String teacherDesc;

    /**
     * 课程封面
     */
    private String photo;

    /**
     * 收藏人气
     */
    private String collectNum;

    /**
     * 年级
     */
    private Integer grade;

    private String gradeName;

    /**
     * 学期
     */
    private Integer term;

    private String termName;

    /**
     * 标签（0：预约课程 1：学习小组）
     */
    private Integer tag;

    private String tagName;

    /**
     * 折扣（0-100）
     */
    private Integer offset;
    
    private Integer campusId;

    private String createTime;

    private String modifyTime;

    private String beginTime;

    private String endTime;

    /**
     * 对应苹果商品ID
     */
    private String appleProductId;

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

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    public Integer getClassHour() {
        return classHour;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOnTax() {
        return onTax;
    }

    public void setOnTax(BigDecimal onTax) {
        this.onTax = onTax;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacherDesc() {
        return teacherDesc;
    }

    public void setTeacherDesc(String teacherDesc) {
        this.teacherDesc = teacherDesc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(String collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
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

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getAppleProductId() {
        return appleProductId;
    }

    public void setAppleProductId(String appleProductId) {
        this.appleProductId = appleProductId;
    }
    
    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }
    
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
