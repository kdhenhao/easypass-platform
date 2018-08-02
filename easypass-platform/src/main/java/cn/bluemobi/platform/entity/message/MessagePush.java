/**
 * Project Name:easypass-platform
 * File Name:MessagePush.java
 * Package Name:cn.bluemobi.platform.entity.message
 * Date:2017年6月26日下午4:10:45
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.message;

import java.io.Serializable;

/**
 * Description: <br/>
 * Date: 2017年6月26日 下午4:10:45 <br/>
 * 
 * @author wuya
 * @version
 * @see
 */
public class MessagePush implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 推送类型:0系统消息 1通知消息 2公告 3活动消息
     */
    private Integer msgType;

    /**
     * 推送标题
     */
    private String msgHeader;

    /**
     * 消息内容
     */
    private String msgDetail;

    /**
     * 消息创建时间
     */
    private String createTm;

    /**
     * 推送状态
     */
    private Integer pushStatus;

    public MessagePush() {
    }

    public MessagePush(Integer id, Integer msgType, String msgHeader, String msgDetail, String createTm) {
        super();
        this.id = id;
        this.msgType = msgType;
        this.msgHeader = msgHeader;
        this.msgDetail = msgDetail;
        this.createTm = createTm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgHeader() {
        return msgHeader;
    }

    public void setMsgHeader(String msgHeader) {
        this.msgHeader = msgHeader;
    }

    public String getMsgDetail() {
        return msgDetail;
    }

    public void setMsgDetail(String msgDetail) {
        this.msgDetail = msgDetail;
    }

    public String getCreateTm() {
        return createTm;
    }

    public void setCreateTm(String createTm) {
        this.createTm = createTm;
    }

    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }

}
