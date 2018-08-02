/**
 * Project Name:lmExpress-platform
 * File Name:DictService.java
 * Package Name:cn.bluemobi.platform.service
 * Date:2016年11月19日下午2:06:48
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

import java.util.Map;

import cn.bluemobi.common.core.dto.MapDto;
import cn.bluemobi.platform.entity.system.User;

/**
 * Description: <br/>
 * Date: 2016年11月19日 下午2:06:48 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface DictService {

    Map<String, Object> findForWeb();

    Map<String, Object> findForDeppon();

    MapDto update(String webAddress, String webPhone, String webEmail, String webQrcode, String webServiceTime,
            String webWechat, String webQQ, User user);

    String findByName(String string);

    MapDto updateDeppon(String depponCompany, String depponSender, String depponPhone, String depponAddress, User user);

}
