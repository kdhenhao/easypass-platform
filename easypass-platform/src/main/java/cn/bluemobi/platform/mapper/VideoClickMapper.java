/**
 * Project Name:easypass-platform Maven Webapp
 * File Name:VideoClickMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2017年8月3日下午12:52:00
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2017年8月3日 下午12:52:00 <br/>
 * 
 * @author zhangcong
 * @version
 * @see
 */
public interface VideoClickMapper {
    List<Map<String, Object>> findVideoClick(Map<String, Object> params);
}
