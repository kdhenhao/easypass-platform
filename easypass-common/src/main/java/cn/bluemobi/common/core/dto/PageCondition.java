/**
 * Project Name:lmExpress-platform
 * File Name:PageCondition.java
 * Package Name:cn.bluemobi.platform.core.dto
 * Date:2016年11月29日下午5:22:28
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.common.core.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2016年11月29日 下午5:22:28 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class PageCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer start;

    private Integer length;

    private Integer draw;

    private Map<String, Object> map;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
