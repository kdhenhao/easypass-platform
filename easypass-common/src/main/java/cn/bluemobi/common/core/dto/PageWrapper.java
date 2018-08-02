/**
 * Project Name:b2c-app
 * File Name:PageDto.java
 * Package Name:cn.bluemobi.app.core.dto
 * Date:2016年1月28日下午4:09:44
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.common.core.dto;

import java.io.Serializable;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Description: <br/>
 * Date: 2016年1月28日 下午4:09:44 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class PageWrapper<T> implements Serializable {

    private static final long serialVersionUID = -3769242798837901124L;

    @ApiModelProperty("总记录数")
    private Integer total;

    @ApiModelProperty("总页数")
    private Integer totalPage;

    @ApiModelProperty("每页记录数")
    private Integer pageSize;

    @ApiModelProperty("当前页数")
    private Integer pageNo;

    @ApiModelProperty("数据")
    private List<T> list;

    public PageWrapper() {
    }

    public PageWrapper(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageWrapper(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setTotal(int total, Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPage = (total / pageSize) + 1;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
        this.totalPage = (total / pageSize) + 1;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
