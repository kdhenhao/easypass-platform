/**
 * Project Name:lmExpress-platform
 * File Name:CommodityMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2016年11月3日下午3:43:22
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.commodity.Commodity;

/**
 * Description: <br/>
 * Date: 2016年11月3日 下午3:43:22 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface CommodityMapper {

    List<Map<String, Object>> findCommodities(Map<String, Object> params);

    void insert(Commodity comm);

    void update(Commodity comm);

    Map<String, Object> findById(String id);

}
