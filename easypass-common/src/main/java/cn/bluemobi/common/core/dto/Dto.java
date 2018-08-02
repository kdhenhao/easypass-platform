/**
 * Project Name:b2c-app
 * File Name:GenerateDto.java
 * Package Name:cn.bluemobi.app.core.dto
 * Date:2016年1月28日下午3:42:17
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.common.core.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Description: <br/>
 * Date: 2016年1月28日 下午3:42:17 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class Dto<T> extends BaseDto {

    private static final long serialVersionUID = 5244245478274283431L;

    public static final Dto<Object> SUCCESS = new Dto<Object>(new Object());

    @ApiModelProperty("返回的数据结果")
    private T data;

    public Dto() {
    }

    public Dto(T t) {
        this.data = t;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
