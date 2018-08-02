/**
 * Project Name:b2c-platform
 * File Name:Option.java
 * Package Name:cn.bluemobi.platform.entity
 * Date:2016年2月16日下午1:46:49
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity;

import java.util.List;

/**
 * Description: <br/>
 * Date: 2016年2月16日 下午1:46:49 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class Option {
    private String value;

    private String text;

    private List<Option> children;

    public Option() {
    }

    public Option(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Option> getChildren() {
        return children;
    }

    public void setChildren(List<Option> children) {
        this.children = children;
    }
}
