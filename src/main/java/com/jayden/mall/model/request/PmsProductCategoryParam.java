package com.jayden.mall.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 添加更新产品分类的参数
 * Created by macro on 2018/4/26.
 */
public class PmsProductCategoryParam {
    @NotNull(message = "父类编号")
    private Long parentId;
    @NotNull(message = "商品分类名称")
    private String name;
    @NotNull(message = "分类单位")
    private String productUnit;
    @NotNull(message = "是否在导航栏显示")
    @Size(max = 1,min =0)
    private Integer navStatus;
    @NotNull(message = "是否进行显示")
    @Size(max = 1,min =0)
    private Integer showStatus;
    @Min(value = 0)
    @NotNull(message = "排序")
    private Integer sort;
    @NotNull(message = "图标")
    private String icon;
    @NotNull(message = "关键字")
    private String keywords;
    @NotNull(message = "描述")
    private String description;
    @NotNull(message = "产品相关筛选属性集合")
    private List<Long> productAttributeIdList;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Integer getNavStatus() {
        return navStatus;
    }

    public void setNavStatus(Integer navStatus) {
        this.navStatus = navStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getProductAttributeIdList() {
        return productAttributeIdList;
    }

    public void setProductAttributeIdList(List<Long> productAttributeIdList) {
        this.productAttributeIdList = productAttributeIdList;
    }
}
