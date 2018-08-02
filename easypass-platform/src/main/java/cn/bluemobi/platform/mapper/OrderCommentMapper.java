/**
 * Project Name:easypass-platform
 * File Name:OrderCommentMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年6月2日下午1:40:07
 * Copyright (c) 2017, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2017年6月2日 下午1:40:07 <br/>
 * 
 * @author oscarwang
 * @version
 * @see
 */
public interface OrderCommentMapper {

    List<Map<String, Object>> findOrderCommentList(Map<String, Object> map);

    int deleteOrderCommentById(Long id);

}
