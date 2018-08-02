/**
 * Project Name:easypass-platform
 * File Name:AppBanner.java
 * Package Name:cn.bluemobi.platform.entity.appBanner
 * Date:2017年5月31日下午1:47:39
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.appBanner;

/**
 * Description: appbanner实体 <br/>
 * Date: 2017年5月31日 下午1:47:39 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public class AppBanner {

    private Integer id;

    private String title;

    /**
     * 广告位置，默认0：首页
     */
    private Integer position;

    private String img;

    private String content;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 链接类型（0：内部链接，1：外部链接 默认0）
     */
    private Integer linkType;

    private String linkUrl;

    /**
     * 状态（0：启动，1：禁用 默认0）
     */
    private Integer status;

    private String statusStr;

    private String createTime;

    private String modifyTime;

    /**
     * 关联的课程id
     */
    private Integer courseId;

    private String courseName;
    
    /**
     * 关联的校区id
     */
    private Integer campusId;
    
    private String campusName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLinkType() {
        return linkType;
    }

    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
    
    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }
    
    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

}
