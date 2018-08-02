/**
 * Project Name:lmExpress-platform
 * File Name:DepponResponse.java
 * Package Name:cn.bluemobi.platform.entity.deppon
 * Date:2016年12月2日上午11:04:44
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.deppon;

import java.io.Serializable;

/**
 * Description: <br/>
 * Date: 2016年12月2日 上午11:04:44 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class DepponResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String result = "true";

    private String resultCode = "1000";

    private String reason = "成功";

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
