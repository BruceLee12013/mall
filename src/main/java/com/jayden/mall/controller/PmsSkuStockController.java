package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.PmsSkuStock;
import com.jayden.mall.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "PmsSkuStockController", description = "sku商品库存管理")
@RequestMapping("/sku")
public class PmsSkuStockController {
    @Autowired
    private PmsSkuStockService skuStockService;
    @ApiOperation("根据商品编号及编号模糊搜索sku库存")
    @GetMapping("/{pid}")
    @ResponseBody
    public ApiRestResponse getList(@PathVariable Long pid, @RequestParam(value = "keyword",required = false) String keyword) {
        List<PmsSkuStock> skuStockList = skuStockService.getList(pid, keyword);
        return ApiRestResponse.success(skuStockList);
    }
    @ApiOperation("批量更新库存信息")
    @PostMapping("/update/{pid}")
    public ApiRestResponse update(@PathVariable Long pid,@RequestBody List<PmsSkuStock> skuStockList){
        int count = skuStockService.update(pid,skuStockList);
        if(count<=0){
         return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

}
