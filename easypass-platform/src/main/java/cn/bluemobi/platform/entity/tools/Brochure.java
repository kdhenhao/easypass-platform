/**
 * Project Name:banma
 * File Name:Brochure.java
 * Package Name:cn.bluemobi.banma.entity.tools
 * Date:2015年12月15日上午10:58:55
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.tools;

/**
 * Description: 单页 <br/>
 * Date: 2015年12月15日 上午10:58:55 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class Brochure {
    private Long id;// '主键(单页)',

    private String title;// varchar(50) comment '单页标题',

    private String content;// text comment '单页内容',

    private String modifyTm;// datetime comment '更新时间',

    private String modifyBy;// bigint comment '更新时间',

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(String modifyTm) {
        this.modifyTm = modifyTm;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
}
