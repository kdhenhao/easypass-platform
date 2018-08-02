/**
 * Project Name:lmExpress-platform
 * File Name:NewsService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年10月31日下午4:33:52
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2016年10月31日 下午4:33:52 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface NewsService {

    Page<Map<String, Object>> findNews(Integer start, Integer length);

    /**
     * 保存
     */
    MapDto saveOrUpdateNews(Long id, String title, String content, User user);

    /**
     * 保存并发布
     */
    MapDto publishOrupdateNews(Long id, String title, String content, User user);

    /**
     * 删除
     */
    MapDto deleteById(Long id, User user);

    /**
     * 发布
     */
    MapDto publishById(Long id, User user);

    Map<String, Object> findById(Long id);

}
