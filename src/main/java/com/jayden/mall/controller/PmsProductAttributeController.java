package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.PmsProductAttribute;
import com.jayden.mall.model.request.PmsProductAttributeParam;
import com.jayden.mall.model.vo.ProductAttrInfo;
import com.jayden.mall.service.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "PmsProductAttributeController", description = "商品属性管理")
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {
    @Autowired
    PmsProductAttributeService productAttributeService;
    @ApiOperation("根据分类查询属性列表或参数列表")
    @GetMapping("/list/{cid}")
    @ResponseBody
    public ApiRestResponse getList(@PathVariable Long cid,
                                   @RequestParam(value = "type") Integer type,
                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductAttribute> productAttributeList = productAttributeService.getList(cid, type, pageSize, pageNum);
        return ApiRestResponse.success(productAttributeList);
    }

    @ApiOperation("添加商品属性信息")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = productAttributeService.create(productAttributeParam);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改商品属性信息")
    @PostMapping("/update/{id}")
    public ApiRestResponse update(@PathVariable Long id, @RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = productAttributeService.update(id, productAttributeParam);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("查询单个商品属性")
    @GetMapping("/{id}")
    public ApiRestResponse getItem(@PathVariable Long id) {
        PmsProductAttribute productAttribute = productAttributeService.getItem(id);
        return ApiRestResponse.success(productAttribute);
    }

    @ApiOperation("批量删除商品属性")
    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam("ids") List<Long> ids) {
        int count = productAttributeService.delete(ids);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @GetMapping("/attrInfo/{productCategoryId}")
    @ResponseBody
    public ApiRestResponse getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        return ApiRestResponse.success(productAttrInfoList);
    }

}
