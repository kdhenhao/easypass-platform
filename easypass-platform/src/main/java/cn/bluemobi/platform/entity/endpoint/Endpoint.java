/**
 * Project Name:lmExpress-platform
 * File Name:Endpoint.java
 * Package Name:cn.bluemobi.platform.entity.endpoint
 * Date:2016年11月4日下午2:25:37
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.endpoint;

import java.io.Serializable;

/**
 * Description: <br/>
 * Date: 2016年11月4日 下午2:25:37 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class Endpoint implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String pointName;

    private String pointCharger;

    private String pointAddress;

    private String pointContact;

    private Long createBy;

    private Long modifyBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getPointCharger() {
        return pointCharger;
    }

    public void setPointCharger(String pointCharger) {
        this.pointCharger = pointCharger;
    }

    public String getPointAddress() {
        return pointAddress;
    }

    public void setPointAddress(String pointAddress) {
        this.pointAddress = pointAddress;
    }

    public String getPointContact() {
        return pointContact;
    }

    public void setPointContact(String pointContact) {
        this.pointContact = pointContact;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

}
