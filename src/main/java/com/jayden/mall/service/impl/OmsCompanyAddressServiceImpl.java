package com.jayden.mall.service.impl;

import com.jayden.mall.model.dao.OmsCompanyAddressMapper;
import com.jayden.mall.model.pojo.OmsCompanyAddress;
import com.jayden.mall.model.pojo.OmsCompanyAddressExample;
import com.jayden.mall.service.OmsCompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {
    @Autowired
    private OmsCompanyAddressMapper companyAddressMapper;
    @Override
    public List<OmsCompanyAddress> list() {
        return companyAddressMapper.selectByExample(new OmsCompanyAddressExample());
    }
}
