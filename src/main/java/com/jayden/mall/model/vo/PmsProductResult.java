package com.jayden.mall.model.vo;

import com.jayden.mall.model.request.PmsProductParam;

public class PmsProductResult extends PmsProductParam {
    private Long cateParentId;

    public Long getCateParentId() {
        return cateParentId;
    }

    public void setCateParentId(Long cateParentId) {
        this.cateParentId = cateParentId;
    }
}
