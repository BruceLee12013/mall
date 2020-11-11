package com.jayden.mall.model.vo;

import com.jayden.mall.model.pojo.PmsProduct;
import com.jayden.mall.model.pojo.SmsFlashPromotionProductRelation;

public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {
    private PmsProduct product;

    public PmsProduct getProduct() {
        return product;
    }

    public void setProduct(PmsProduct product) {
        this.product = product;
    }
}
