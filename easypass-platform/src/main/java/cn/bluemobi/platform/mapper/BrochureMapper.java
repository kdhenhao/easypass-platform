/**
 * Project Name:banma
 * File Name:BrochureMapper.java
 * Package Name:cn.bluemobi.banma.mapper
 * Date:2015年12月14日下午5:38:05
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;
import java.util.Map;

import cn.bluemobi.platform.entity.tools.Brochure;

/**
 * Description: <br/>
 * Date: 2015年12月14日 下午5:38:05 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface BrochureMapper {

    List<Map<String, Object>> findBrochures();

    Brochure findBrochureById(String id);

    int updateBrochure(Brochure bro);

    List<Brochure> findBrochureByTitle(String title);

}
