/**
 * Project Name:b2c-app
 * File Name:ListDto.java
 * Package Name:cn.bluemobi.app.core.dto
 * Date:2016年1月28日上午9:55:15
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.common.core.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Description: <br/>
 * Date: 2016年1月28日 上午9:55:15 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class ListWrapper<T> implements Serializable {

    private static final long serialVersionUID = 4509052294450818836L;

    public ListWrapper() {
    }

    public ListWrapper(List<T> list) {
        this.list = list;
    }

    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
