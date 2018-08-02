/**
 * Project Name:lmExpress-platform
 * File Name:Tax.java
 * Package Name:cn.bluemobi.platform.entity.commodity
 * Date:2016年11月3日下午2:21:00
 * Copyright (c) 2016, bluemobi.cn All Rights Reserved.
 *
*/

package cn.bluemobi.platform.entity.commodity;

import java.io.Serializable;

/**
 * Description: <br/>
 * Date: 2016年11月3日 下午2:21:00 <br/>
 * 
 * @author hut
 * @version
 * @see
 */
public class Tax implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String taxNo;

    private String taxName;

    private String taxUnit;

    private String taxUnitCode;

    private String taxPrice;

    private String taxPercent;

    private Integer level;

    private Long createBy;

    private Long modifyBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getTaxUnit() {
        return taxUnit;
    }

    public void setTaxUnit(String taxUnit) {
        this.taxUnit = taxUnit;
    }

    public String getTaxUnitCode() {
        return taxUnitCode;
    }

    public void setTaxUnitCode(String taxUnitCode) {
        this.taxUnitCode = taxUnitCode;
    }

    public String getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(String taxPrice) {
        this.taxPrice = taxPrice;
    }

    public String getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(String taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

}
