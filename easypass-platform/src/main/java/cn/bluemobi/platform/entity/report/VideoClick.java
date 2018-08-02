/**
 * Project Name:easypass-platform Maven Webapp
 * File Name:VideoClick.java
 * Package Name:cn.bluemobi.platform.entity.report
 * Date:2017年8月3日下午12:46:45
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bluemobi.platform.entity.report;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description: <br/>
 * Date: 2017年8月3日 下午12:46:45 <br/>
 * 
 * @author zhangcong
 * @version
 * @see
 */
public class VideoClick implements Serializable {
    private static final long serialVersionUID = 1123123123L;

    private Long id;

    /**
     * 课程名字
     */
    private String courseName;

    /**
     * 课时名称
     */
    private String classHourName;

    /**
     * 点击量
     */
    private BigDecimal clickNum;

    public VideoClick() {
        super();
    }

    public VideoClick(Long id, String courseName, String classHourName, BigDecimal clickNum) {
        super();
        this.id = id;
        this.courseName = courseName;
        this.classHourName = classHourName;
        this.clickNum = clickNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigDecimal getClickNum() {
        return clickNum;
    }

    public void setClickNum(BigDecimal clickNum) {
        this.clickNum = clickNum;
    }
 
}
