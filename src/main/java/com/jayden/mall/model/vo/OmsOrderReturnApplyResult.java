package com.jayden.mall.model.vo;

import com.jayden.mall.model.pojo.OmsCompanyAddress;
import com.jayden.mall.model.pojo.OmsOrderReturnApply;

public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {
    private OmsCompanyAddress companyAddress;

    public OmsCompanyAddress getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(OmsCompanyAddress companyAddress) {
        this.companyAddress = companyAddress;
    }
}
