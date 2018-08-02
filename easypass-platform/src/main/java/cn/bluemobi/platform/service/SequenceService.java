/**
 * Project Name:b2c-common
 * File Name:SequenceService.java
 * Package Name:cn.bluemobi.common.service
 * Date:2016年2月1日下午1:55:32
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.service;

/**
 * Description: 序列号生成 <br/>
 * Date: 2016年2月1日 下午1:55:32 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public interface SequenceService {

    /**
     * 订单号
     */
    public static final String ORDER_NO = "orderNo";

    /**
     * 支付号
     */
    public static final String BATCH_NO = "batchNo";

    /**
     * 返回一个自增长的序列
     */
    String nextSeq(String key);
}
