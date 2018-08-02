/**
 * Project Name:easypass-platform
 * File Name:Course.java
 * Package Name:cn.bluemobi.platform.entity.course
 * Date:2017年5月24日下午3:53:43
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.course;

import java.math.BigDecimal;

/**
 * Description: 课程对象 <br/>
 * Date: 2017年5月24日 下午3:53:43 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public class Course {

    private Integer id;

    /**
     * 所属专业分类
     */
    private String classifyId;

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
     * 适合人群
     */
    private String forCrowd;

    /**
     * 学习目标
     */
    private String studyGoal;

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
     * 价格
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
     * 平均成绩
     */
    private BigDecimal averageMarks;

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

    /**
     * 学期
     */
    private Integer term;

    /**
     * 标签（0：预约课程 1：学习小组）
     */
    private Integer tag;
    
    /**
     * 内容难度
     */
    private BigDecimal contentDifficulty;
    
    /**
     * 作业难度
     */
    private BigDecimal courseworkDifficulty;
    
    /**
     * 考试难度
     */
    private BigDecimal examDifficulty;
    
    /**
     * 综合难度
     */
    private BigDecimal overallDifficulty;
    
    /**
     * 教授1
     */
    private String prof1;
    
    /**
     * 教授1难度
     */
    private BigDecimal prof1Difficulty;
    
    /**
     * 教授2
     */
    private String prof2;
    
    /**
     * 教授2难度
     */
    private BigDecimal prof2Difficulty;
    
    /**
     * 是否显示(0:不显示 1：显示)
     */
    private Integer display;

    /**
     * 折扣（0-100）
     */
    private Integer offset;
    
    private Integer campusId;

    private String createTime;

    private String modifyTime;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 对应苹果商品ID
     */
    private String appleProductId;

    /**
     * 视频封面图片
     */
    private String coverImg;

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

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

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public BigDecimal getAverageMarks() {
        return averageMarks;
    }

    public void setAverageMarks(BigDecimal averageMarks) {
        this.averageMarks = averageMarks;
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

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
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

    public Integer getClassHour() {
        return classHour;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public String getVideoHttpUrl() {
        return videoHttpUrl;
    }

    public void setVideoHttpUrl(String videoHttpUrl) {
        this.videoHttpUrl = videoHttpUrl;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public BigDecimal getContentDifficulty() {
        return contentDifficulty;
    }

    public void setContentDifficulty(BigDecimal contentDifficulty) {
        this.contentDifficulty = contentDifficulty;
    }
    
    public BigDecimal getCourseworkDifficulty() {
        return courseworkDifficulty;
    }

    public void setCourseworkDifficulty(BigDecimal courseworkDifficulty) {
        this.courseworkDifficulty = courseworkDifficulty;
    }
    
    public BigDecimal getExamDifficulty() {
        return examDifficulty;
    }

    public void setExamDifficulty(BigDecimal examDifficulty) {
        this.examDifficulty = examDifficulty;
    }
    
    public BigDecimal getOverallDifficulty() {
        return overallDifficulty;
    }

    public void setOverallDifficulty(BigDecimal overallDifficulty) {
        this.overallDifficulty = overallDifficulty;
    }
    
    public String getProf1() {
        return prof1;
    }

    public void setProf1(String prof1) {
        this.prof1 = prof1;
    }
    
    public BigDecimal getProf1Difficulty() {
        return prof1Difficulty;
    }
    
    public void setProf1Difficulty(BigDecimal prof1Difficulty) {
    	this.prof1Difficulty = prof1Difficulty;
    }
    
    public String getProf2() {
        return prof2;
    }

    public void setProf2(String prof2) {
        this.prof2 = prof2;
    }
    
    public BigDecimal getProf2Difficulty() {
        return prof2Difficulty;
    }
    
    public void setProf2Difficulty(BigDecimal prof2Difficulty) {
    	this.prof2Difficulty = prof2Difficulty;
    }
    
    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }
    
    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getForCrowd() {
        return forCrowd;
    }

    public void setForCrowd(String forCrowd) {
        this.forCrowd = forCrowd;
    }

    public String getStudyGoal() {
        return studyGoal;
    }

    public void setStudyGoal(String studyGoal) {
        this.studyGoal = studyGoal;
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

}
