/**
 * Project Name:banma
 * File Name:BrochureService.java
 * Package Name:cn.bluemobi.banma.service
 * Date:2015年12月14日下午5:23:51
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.platform.entity.system.User;
import cn.bluemobi.platform.entity.tools.Brochure;

/**
 * Description: <br/>
 * Date: 2015年12月14日 下午5:23:51 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface BrochureService {

    Page<Map<String, Object>> findBrochures(int start, int length);

    Brochure findBrochureById(String id);

    MapDto updateBrochure(Brochure bro, User user);

    String findBrochureByTitle(String title);

}
