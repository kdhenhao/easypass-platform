/**
 * Project Name:b2c-common
 * File Name:SequenceMapper.java
 * Package Name:cn.bluemobi.common.mapper
 * Date:2016年2月1日下午2:38:05
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.mapper;

import java.util.List;

/**
 * Description: <br/>
 * Date: 2016年2月1日 下午2:38:05 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface SequenceMapper {

    List<Long> findFromDataBase(String key);

    int incrementDataBase(String key);

    int insertDataBase(String key);

}
