/**
 * Project Name:lmExpress-platform
 * File Name:TaxMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2016年11月3日上午11:28:35
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.commodity.Tax;

/**
 * Description: <br/>
 * Date: 2016年11月3日 上午11:28:35 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface TaxMapper {

    List<Map<String, Object>> findTaxs(Map<String, Object> params);

    void lockById(Long id, Long id2);

    void unLockById(Long id, Long id2);

    int hasCode(String taxNo);

    int hasCode2(String taxNo, Long id);

    void insert(Tax tax);

    void update(Tax tax);

    List<Tax> findTaxForSelect();

}
