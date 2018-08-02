/**
 * Project Name:jrx
 * File Name:Auth.java
 * Package Name:cn.bluemobi.jrx.entity.system
 * Date:2015年7月17日上午11:04:24
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.system;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 权限<br/>
 * Date: 2015年7月17日 上午11:04:24 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class Auth implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -5636670874596454029L;

    private String id;

    private String authName;

    private String href;

    private int level;

    private String parent;

    private String img;

    private int order;

    private List<Auth> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Auth> getChildren() {
        return children;
    }

    public void setChildren(List<Auth> children) {
        this.children = children;
    }

}
