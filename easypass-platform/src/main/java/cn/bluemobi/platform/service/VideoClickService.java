/**
 * Project Name:easypass-platform Maven Webapp
 * File Name:VideoClick.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2017年8月3日下午12:56:48
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;

/**
 * Description: 点击量模块<br/>
 * Date: 2017年8月3日 下午12:56:48 <br/>
 * 
 * @author zhangcong
 * @version
 * @see
 */
public interface VideoClickService {
    Page<Map<String, Object>> findVideoClicks(PageCondition cond);
}
