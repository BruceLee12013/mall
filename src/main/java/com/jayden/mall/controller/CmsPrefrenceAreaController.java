package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.model.pojo.CmsPrefrenceArea;
import com.jayden.mall.service.CmsPrefrenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 品牌管理Controller
 * Created by macro on 2019/4/19.
 */
@Api(tags = "CmsPrefrenceAreaController", description = "商品优选管理")
@RestController
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaController {
    @Autowired
    private CmsPrefrenceAreaService prefrenceAreaService;

    @ApiOperation("获取所有商品优选")
    @GetMapping("/listAll")
    public ApiRestResponse listAll() {
        List<CmsPrefrenceArea> prefrenceAreaList = prefrenceAreaService.listAll();
        return ApiRestResponse.success(prefrenceAreaList);
    }
}

