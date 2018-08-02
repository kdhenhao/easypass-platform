/**
 * Project Name:banma
 * File Name:AreaService.java
 * Package Name:cn.bluemobi.banma.service
 * Date:2015年12月22日下午3:11:23
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service;

import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2015年12月22日 下午3:11:23 <br/>
 * 
 * @author lvxh
 * @version
 * @see
 */
public interface AreaService {

    List<Map<String, Object>> findProvinces();

    List<Map<String, Object>> findByParent(String string);

}
