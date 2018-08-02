/**
 * Project Name:b2c-common
 * File Name:SequenceServiceImpl.java
 * Package Name:cn.bluemobi.common.service.impl
 * Date:2016年2月1日下午2:00:01
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cn.bluemobi.platform.mapper.SequenceMapper;
import cn.bluemobi.platform.service.SequenceService;

/**
 * Description: <br/>
 * Date: 2016年2月1日 下午2:00:01 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
@Service
@Transactional
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private SequenceMapper sequenceMapper;

    // 当前号码
    private ConcurrentMap<String, AtomicLong> map = new ConcurrentHashMap<String, AtomicLong>();

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String nextSeq(String key) {
        initMapIfNecessary(key);
        while (incrementDataBase(key) != 1) {

        }
        AtomicLong al = map.get(key);
        Long l = al.incrementAndGet();
        return l.toString();
    }

    private void initMapIfNecessary(String key) {
        while (true) {
            AtomicLong current = map.get(key);
            if (current == null) {
                // 查看数据库是否有
                List<Long> list = findFromDataBase(key);
                AtomicLong temp = new AtomicLong(0l);
                if (!CollectionUtils.isEmpty(list)) {
                    temp = new AtomicLong(list.get(0));
                }
                // 如果已经有了，继续，如果放进去了，退出循环
                if (map.putIfAbsent(key, temp) == null) {
                    if (temp.get() == 0l) {
                        insertDataBase(key);
                    }
                    break;
                }
            } else {
                break;
            }
        }
    }

    private int insertDataBase(String key) {
        return sequenceMapper.insertDataBase(key);
    }

    private List<Long> findFromDataBase(String key) {
        return sequenceMapper.findFromDataBase(key);
    }

    private int incrementDataBase(String key) {
        return sequenceMapper.incrementDataBase(key);
    }

}
