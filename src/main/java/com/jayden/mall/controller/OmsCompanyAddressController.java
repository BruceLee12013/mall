package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.model.pojo.OmsCompanyAddress;
import com.jayden.mall.service.OmsCompanyAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "OmsCompanyAddressController", description = "收货地址管理")
@RestController
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {
    @Autowired
    private OmsCompanyAddressService companyAddressService;

    @ApiOperation("获取所有收货地址")
    @GetMapping("/list")
    public ApiRestResponse list() {
        List<OmsCompanyAddress> companyAddressList = companyAddressService.list();
        return ApiRestResponse.success(companyAddressList);
    }
}
