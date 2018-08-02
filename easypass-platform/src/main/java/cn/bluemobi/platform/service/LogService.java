/**
 * Project Name:banma
 * File Name:LogService.java
 * Package Name:cn.bluemobi.banma.service
 * Date:2015年12月16日下午3:03:26
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.Page;

/**
 * Description: <br/>
 * Date: 2015年12月16日 下午3:03:26 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface LogService {

    Page<Map<String, Object>> findLogsPage(int start, int length, String startDate, String endDate, String username,
            String optType);

    /**
     * 管理员操作记录 Description: <br/>
     */
    void addLog(Long userId, Long memberId, String message, String model);

}
