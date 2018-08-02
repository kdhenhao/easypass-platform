/**
 * Project Name:easypass-platform
 * File Name:AppUserRecord.java
 * Package Name:cn.bluemobi.platform.entity.appUser
 * Date:2017年7月16日下午8:07:03
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.appUser;

/**
 * Description: 用户记录 <br/>
 * Date: 2017年7月16日 下午8:07:03 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public class AppUserRecord {

    private Integer id;

    private String userId;

    private String nickName;

    private Integer studyDayNum;

    private Integer finishCourseHour;

    private Integer score;
    
    private Integer points;

    private String createTime;

    private String modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getStudyDayNum() {
        return studyDayNum;
    }

    public void setStudyDayNum(Integer studyDayNum) {
        this.studyDayNum = studyDayNum;
    }

    public Integer getFinishCourseHour() {
        return finishCourseHour;
    }

    public void setFinishCourseHour(Integer finishCourseHour) {
        this.finishCourseHour = finishCourseHour;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
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

}
