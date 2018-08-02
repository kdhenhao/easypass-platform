/**
 * Project Name:lmExpress-platform
 * File Name:ClearNoService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年11月24日下午2:21:05
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.Page;

/**
 * Description: <br/>
 * Date: 2016年11月24日 下午2:21:05 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface ClearNoService {

    Page<Map<String, Object>> findClearNos(Integer start, Integer length, String orderNo, String clearNo,
            String status);

    Map<String, Object> importClearNo(Map<String, Object> map);

}
