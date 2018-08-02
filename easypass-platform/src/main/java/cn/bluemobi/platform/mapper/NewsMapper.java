/**
 * Project Name:lmExpress-platform
 * File Name:NewsMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2016年10月31日下午4:38:26
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2016年10月31日 下午4:38:26 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface NewsMapper {

    List<Map<String, Object>> findNews();

    void insertNews(String title, String content, Long id);

    void updateNews(String title, String content, Long id, Long id2);

    void insertNews2(String title, String content, Long id);

    void updateNews2(String title, String content, Long id, Long id2);

    void deleteById(Long id);

    void publishById(Long id, Long long1);

    Map<String, Object> findById(Long id);

}
