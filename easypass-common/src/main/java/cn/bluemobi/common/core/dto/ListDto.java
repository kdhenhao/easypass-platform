/**
 * Project Name:b2c-app
 * File Name:GenerateList.java
 * Package Name:cn.bluemobi.app.core.dto
 * Date:2016年1月28日下午1:11:18
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.common.core.dto;

import java.util.List;

/**
 * Description: <br/>
 * Date: 2016年1月28日 下午1:11:18 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class ListDto<T> extends BaseDto {

    private static final long serialVersionUID = 36000510991627328L;

    private ListWrapper<T> data;

    public ListDto() {
    }

    public ListDto(List<T> list) {
        this.data = new ListWrapper<T>(list);
    }

    public ListWrapper<T> getData() {
        return data;
    }

    public void setData(ListWrapper<T> data) {
        this.data = data;
    }

    public void setList(List<T> list) {
        this.data = new ListWrapper<T>(list);
    }
}
