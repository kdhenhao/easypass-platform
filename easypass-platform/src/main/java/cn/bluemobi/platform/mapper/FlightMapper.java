/**
 * Project Name:lmExpress-platform
 * File Name:FlightMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2016年11月1日下午5:28:36
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2016年11月1日 下午5:28:36 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface FlightMapper {

    List<Map<String, Object>> findFlights(Map<String, Object> map);

    void insertFlight(String flightNo, String flightDate, Long portId, Long id);

    void updateFlight(String flightNo, String flightDate, Long portId, Long id, Long id2);

    void updateStatus(String flightStatus, Long id, Long id2);

    List<Map<String, Object>> findForSelect2(String q);

    String findFlightNameById(String flightId);

}
