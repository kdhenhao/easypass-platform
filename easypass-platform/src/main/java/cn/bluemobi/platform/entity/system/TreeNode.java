/**
 * Project Name:cwy-platform
 * File Name:TreeNode.java
 * Package Name:cn.bluemobi.platform.entity.system
 * Date:2016年10月22日下午2:59:29
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.system;

import java.io.Serializable;
import java.util.List;

/**
 * Description: <br/>
 * Date: 2016年10月22日 下午2:59:29 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String text;

    private String id;

    private List<TreeNode> nodes;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }
}
