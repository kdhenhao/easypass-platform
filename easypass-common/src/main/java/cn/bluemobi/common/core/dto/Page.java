/**
 * Project Name:b2c-app
 * File Name:GeneratePage.java
 * Package Name:cn.bluemobi.app.core.dto
 * Date:2016年1月25日下午6:20:49
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.common.core.dto;

import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2016年1月25日 下午6:20:49 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class Page<T> extends BaseDto {

    private static final long serialVersionUID = 6953033557915205500L;

    private PageWrapper<T> data;

    public Page() {
    }

    /**
     * 返回一个初始化页数和大小的对象 Description: <br/>
     */
    public static <E> Page<E> get(int pageNo, int pageSize) {
        Page<E> page = new Page<E>();
        PageWrapper<E> data = new PageWrapper<E>(pageNo, pageSize);
        page.setData(data);
        return page;
    }

    public static Page<Map<String, Object>> getMap(int pageNo, int pageSize) {
        Page<Map<String, Object>> page = new Page<Map<String, Object>>();
        PageWrapper<Map<String, Object>> data = new PageWrapper<Map<String, Object>>(pageNo, pageSize);
        page.setData(data);
        return page;
    }

    public Page(List<T> list) {
        this.data = new PageWrapper<T>(list);
    }

    public void setList(List<T> list) {
        if (this.data != null) {
            this.data.setList(list);
        } else {
            this.data = new PageWrapper<T>(list);
        }
    }

    public PageWrapper<T> getData() {
        return data;
    }

    public void setData(PageWrapper<T> data) {
        this.data = data;
    }

    public int findPageNo() {
        return this.data.getPageNo();
    }

    public int findPageSize() {
        return this.data.getPageSize();
    }

    public void setTotal(int total) {
        this.data.setTotal(total);
    }

    public void setTotal(int total, int pageNo, int pageSize) {
        this.data.setPageNo(pageNo);
        this.data.setPageSize(pageSize);
        this.data.setTotal(total);
    }
}
