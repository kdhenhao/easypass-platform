/**
 * Project Name:lmExpress-platform
 * File Name:TaxService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年11月3日上午11:18:08
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.List;
import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;
import cn.bluemobi.platform.entity.commodity.Tax;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2016年11月3日 上午11:18:08 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface TaxService {

    Page<Map<String, Object>> findTaxs(PageCondition cond);

    MapDto lockById(Long id, User user);

    MapDto unLockById(Long id, User user);

    MapDto saveOrUpdate(Tax tax, User user);

    List<Tax> findTaxsForSelect();

}
