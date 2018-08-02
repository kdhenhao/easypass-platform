/**
 * Project Name:gmo-platform Maven Webapp
 * File Name:LoginHelperController.java
 * Package Name:cn.bluemobi.platform.controller.tools
 * Date:2016年8月22日上午11:31:42
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.controller.util;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Description:	   <br/>
 * Date:     2016年8月22日 上午11:31:42 <br/>
 * @author   dingyl
 * @version  
 * @see 	 
 */
public class LoginHelperController extends UsernamePasswordToken {
    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 1L;
    private Integer loginType;

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public LoginHelperController(Integer loginType,String username,String password) {
        super(username,password);
        this.loginType = loginType;
    }
    
}

