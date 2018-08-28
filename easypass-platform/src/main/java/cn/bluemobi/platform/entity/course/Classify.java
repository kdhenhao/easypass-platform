/**
 * Project Name:easypass-platform
 * File Name:Classify.java
 * Package Name:cn.bluemobi.platform.entity.course
 * Date:2017年5月23日下午10:41:38
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.course;

/**
 * Description: 课程专业分类 <br/>
 * Date: 2017年5月23日 下午10:41:38 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public class Classify {

    private Integer id;

    private String name;

    private Integer parentId;
    
    private String campusName;

    private Integer level;

    private Integer sort;

    /**
     * 封面图片
     */
    private String img;
    
    private Integer campusId;

    private String createTime;

    private String modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
