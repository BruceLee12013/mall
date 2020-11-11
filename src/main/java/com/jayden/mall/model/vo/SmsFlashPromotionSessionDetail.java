package com.jayden.mall.model.vo;

import com.jayden.mall.model.pojo.SmsFlashPromotionSession;

public class SmsFlashPromotionSessionDetail  extends SmsFlashPromotionSession {
    private Long productCount;

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }
}
