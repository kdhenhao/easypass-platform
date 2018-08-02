/**
 * Project Name:b2c-common
 * File Name:DtoBase.java
 * Package Name:org.b2c.common.core.dto
 * Date:2016年1月18日下午3:49:34
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.common.core.dto;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModelProperty;

import cn.bluemobi.common.core.utils.BlueMobiConstants;

/**
 * Description: <br/>
 * Date: 2016年1月18日 下午3:49:34 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class BaseDto implements Serializable {

    private static final long serialVersionUID = 149124814045609190L;

    @ApiModelProperty(value = "处理状态", allowableValues = "0成功，1失败,2token无效，3帐号被封禁")
    protected int status = BlueMobiConstants.SUCCESS; // 标识 默认成功

    @ApiModelProperty("如果出错，将返回错误消息")
    protected String msg = "";

    @ApiModelProperty("在特定错误情况下，会返回错误的代码")
    protected String code = "";

    public void errorMsg(String msg) {
        this.status = BlueMobiConstants.FAIL;
        this.msg = msg;
    }

    public void tokenErrorMsg(String msg) {
        this.status = BlueMobiConstants.TOKEN_FAIL;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
