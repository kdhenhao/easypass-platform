/**
 * Project Name:lmExpress-platform
 * File Name:ClearNoMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2016年11月24日下午2:30:18
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2016年11月24日 下午2:30:18 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface ClearNoMapper {

    List<Map<String, Object>> findClearNos(Map<String, Object> params);

    void insert(Map<String, Object> map);

    int exist(Map<String, Object> map);

    int countNotUsed();

    List<Long> findOrdersNotAllocated(Long palletId);

    List<String> findNotUsed100();

    void allocate(String clearNo, Long orderId);

    void markAllocated(String clearNo);

}
