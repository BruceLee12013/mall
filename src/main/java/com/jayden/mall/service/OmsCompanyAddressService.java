package com.jayden.mall.service;

import com.jayden.mall.model.pojo.OmsCompanyAddress;

import java.util.List;

public interface OmsCompanyAddressService {
    /**
     * 获取全部收货地址
     */
    List<OmsCompanyAddress> list();
}
