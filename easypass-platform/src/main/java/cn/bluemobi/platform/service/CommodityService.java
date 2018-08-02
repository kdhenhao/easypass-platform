/**
 * Project Name:lmExpress-platform
 * File Name:CommodityService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年11月3日下午3:42:22
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.entity.commodity.Commodity;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2016年11月3日 下午3:42:22 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface CommodityService {

    /**
     * 所有商品
     */
    Page<Map<String, Object>> findCommodities(PageCondition cond);

    MapDto saveOrUpdate(Commodity comm, User user);

    /**
     * 可用的商品
     */
    Page<Map<String, Object>> findCommoditiesOpen(Integer start, Integer length, String taxNo, String cnName,
            String barcode, String brand);

    Map<String, Object> findById(String id);

}
