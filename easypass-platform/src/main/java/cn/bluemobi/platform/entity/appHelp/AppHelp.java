/**
 * Project Name:easypass-platform
 * File Name:AppHelp.java
 * Package Name:cn.bluemobi.platform.entity.appHelp
 * Date:2017年6月23日下午4:21:42
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.appHelp;

import java.io.Serializable;

/**
 * Description: <br/>
 * Date: 2017年6月23日 下午4:21:42 <br/>
 * 
 * @author wuya
 * @version
 * @see
 */
public class AppHelp implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 帮助标题
     */
    private String helpHeader;

    /**
     * 帮助序号
     */
    private Integer helpSeq;

    /**
     * 帮助详情
     */
    private String solution;

    /**
     * 创建时间
     */
    private String createTm;

    public AppHelp() {
    }

    public AppHelp(Integer id, String helpHeader, Integer helpSeq, String solution, String createTm) {
        super();
        this.id = id;
        this.helpHeader = helpHeader;
        this.helpSeq = helpSeq;
        this.solution = solution;
        this.createTm = createTm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHelpHeader() {
        return helpHeader;
    }

    public void setHelpHeader(String helpHeader) {
        this.helpHeader = helpHeader;
    }

    public Integer getHelpSeq() {
        return helpSeq;
    }

    public void setHelpSeq(Integer helpSeq) {
        this.helpSeq = helpSeq;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getCreateTm() {
        return createTm;
    }

    public void setCreateTm(String createTm) {
        this.createTm = createTm;
    }

}
