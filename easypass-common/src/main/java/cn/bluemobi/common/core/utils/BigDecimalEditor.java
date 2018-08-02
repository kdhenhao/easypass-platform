/**
 * Project Name:jrx
 * File Name:BigDecimalEditor.java
 * Package Name:cn.bluemobi.jrx.core.util
 * Date:2015年8月19日下午1:18:50
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.common.core.utils;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

/**
 * Description: <br/>
 * Date: 2015年8月19日 下午1:18:50 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class BigDecimalEditor extends PropertyEditorSupport {

    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.isEmpty()) {
            setValue(null);
        } else {
            setValue(new BigDecimal(text));
        }
    }
}
