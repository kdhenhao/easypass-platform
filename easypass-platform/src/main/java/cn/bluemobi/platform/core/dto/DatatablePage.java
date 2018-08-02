/**
 * Project Name:hopefull-web
 * File Name:DatatablePage.java
 * Package Name:cn.bluemobi.web.core.dto
 * Date:2016年9月12日下午3:23:48
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.core.dto;

import java.io.Serializable;
import java.util.List;

import cn.bluemobi.common.core.dto.Page;
import cn.bluemobi.common.core.dto.PageCondition;

/**
 * Description: <br/>
 * Date: 2016年9月12日 下午3:23:48 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class DatatablePage implements Serializable {

    private static final long serialVersionUID = 1L;

    public DatatablePage(Integer draw, Page<?> page) {
        this.draw = draw;
        this.recordsFiltered = page.getData().getTotal();
        this.recordsTotal = page.getData().getTotal();
        this.data = page.getData().getList();
    }

    public DatatablePage(PageCondition cond, Page<?> page) {
        this.draw = cond.getDraw();
        this.recordsFiltered = page.getData().getTotal();
        this.recordsTotal = page.getData().getTotal();
        this.data = page.getData().getList();
    }

    private Integer draw;

    private Integer recordsTotal;

    private Integer recordsFiltered;

    private List<?> data;

    private String error;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
