/**
 * Project Name:banma
 * File Name:LogMapper.java
 * Package Name:cn.bluemobi.banma.mapper
 * Date:2015年12月16日下午4:35:44
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2015年12月16日 下午4:35:44 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface LogMapper {

    List<Map<String, Object>> findLogs(Map<String, Object> params);

    int insertLog(Long userId, Long memberId, String message, String model, Timestamp now);

    int merchantLog(Long busiId, Long memberId, String message, String model, Timestamp now);

}
