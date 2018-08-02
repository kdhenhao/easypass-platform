/**
 * Project Name:easypass-platform
 * File Name:OrderCommentService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2017年6月2日下午12:11:34
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;

/**
 * Description: <br/>
 * Date: 2017年6月2日 下午12:11:34 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface OrderCommentService {

    Page<Map<String, Object>> findOrderCommentList(PageCondition cond);

    MapDto deleteOrderCommentById(Long id);

}
