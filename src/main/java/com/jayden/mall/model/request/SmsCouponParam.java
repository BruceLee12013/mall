package com.jayden.mall.model.request;

import com.jayden.mall.model.pojo.SmsCoupon;
import com.jayden.mall.model.pojo.SmsCouponProductCategoryRelation;
import com.jayden.mall.model.pojo.SmsCouponProductRelation;

import java.util.List;

public class SmsCouponParam extends SmsCoupon {
    private List<SmsCouponProductRelation> productRelationList;
    private List<SmsCouponProductCategoryRelation> productCategoryRelationList;

    public List<SmsCouponProductRelation> getProductRelationList() {
        return productRelationList;
    }

    public void setProductRelationList(List<SmsCouponProductRelation> productRelationList) {
        this.productRelationList = productRelationList;
    }

    public List<SmsCouponProductCategoryRelation> getProductCategoryRelationList() {
        return productCategoryRelationList;
    }

    public void setProductCategoryRelationList(List<SmsCouponProductCategoryRelation> productCategoryRelationList) {
        this.productCategoryRelationList = productCategoryRelationList;
    }
}
