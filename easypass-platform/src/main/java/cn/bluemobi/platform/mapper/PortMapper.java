/**
 * Project Name:lmExpress-platform
 * File Name:PortMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2016年11月1日下午2:38:02
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.Option;

/**
 * Description: <br/>
 * Date: 2016年11月1日 下午2:38:02 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface PortMapper {

    List<Map<String, Object>> findPorts();

    void startById(Long id);

    void stopById(Long id);

    int existPort(String portName);

    int existPort2(String portName, Long id);

    void insertPort(String portName, Long long1);

    void updatePort(String portName, Long id, Long id2);

    List<Option> findPortsForSelect();

}
