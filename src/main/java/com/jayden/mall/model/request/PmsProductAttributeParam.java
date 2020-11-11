package com.jayden.mall.model.request;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 商品属性参数
 * Created by macro on 2018/4/26.
 */

public class PmsProductAttributeParam {
    @NotNull(message = "属性分类ID")
    private Long productAttributeCategoryId;
    @NotNull(message = "属性名称")
    private String name;

    @NotNull(message = "属性选择类型")
    @Size(min = 0,max =3)
    private Integer selectType;
    @Size(min = 0,max =1)
    private Integer inputType;
    @NotNull(message ="可选值列表" )
    private String inputList;
    private Integer sort;
    @NotNull(message = "分类筛选样式")
    @Size(min =0,max = 1)
    private Integer filterType;
    @NotNull(message = "检索类型")
    @Size(min = 0,max = 2)
    private Integer searchType;
    @NotNull(message = "相同属性产品是否关联")
    @Size(min = 0,max=1)
    private Integer relatedStatus;
    @NotNull(message = "是否支持手动新增")
    @Size(min = 0,max=1)
    private Integer handAddStatus;
    @NotNull(message = "属性的类型")
    @Size(min = 0,max=1)
    private Integer type;

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public String getInputList() {
        return inputList;
    }

    public void setInputList(String inputList) {
        this.inputList = inputList;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getRelatedStatus() {
        return relatedStatus;
    }

    public void setRelatedStatus(Integer relatedStatus) {
        this.relatedStatus = relatedStatus;
    }

    public Integer getHandAddStatus() {
        return handAddStatus;
    }

    public void setHandAddStatus(Integer handAddStatus) {
        this.handAddStatus = handAddStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
