package com.jayden.mall.model.vo;

import javax.validation.constraints.NotNull;

public class PmsProductQueryParam {
//    @NotNull(message = "上架状态")
    private Integer publishStatus;
//    @NotNull(message = "审核状态")
    private Integer verifyStatus;
//    @NotNull(message = "商品名称模糊关键字")
    private String keyword;
//    @NotNull(message = "商品货号")
    private String productSn;
//    @NotNull(message = "商品分类编号")
    private Long productCategoryId;
//    @NotNull(message = "商品品牌编号")
    private Long brandId;

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
