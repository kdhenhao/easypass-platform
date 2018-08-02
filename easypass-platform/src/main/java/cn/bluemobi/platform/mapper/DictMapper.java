/**
 * Project Name:lmExpress-platform
 * File Name:DictMapper.java
 * Package Name:cn.bluemobi.platform.mapper
 * Date:2016年11月19日下午2:07:45
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

/**
 * Description: <br/>
 * Date: 2016年11月19日 下午2:07:45 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface DictMapper {

    String findByName(String string);

    void updateByName(String webAddress, String string);

}
