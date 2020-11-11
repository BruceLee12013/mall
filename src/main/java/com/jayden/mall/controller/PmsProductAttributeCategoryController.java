package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.PmsProductAttributeCategory;
import com.jayden.mall.model.vo.PmsProductAttributeCategoryItem;
import com.jayden.mall.service.PmsProductAttributeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {
    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;
    @ApiOperation("添加商品属性分类")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestParam String name) {
        int count = productAttributeCategoryService.create(name);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }
    @ApiOperation("修改商品属性分类")
    @PostMapping("/update/{id}")
    public ApiRestResponse update(@PathVariable Long id, @RequestParam String name) {
        int count = productAttributeCategoryService.update(id, name);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("删除单个商品属性分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiRestResponse<Object> delete(@PathVariable Long id) {
        int count = productAttributeCategoryService.delete(id);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("获取单个商品属性分类信息")
    @GetMapping("/{id}")
    public ApiRestResponse getItem(@PathVariable Long id) {
        PmsProductAttributeCategory productAttributeCategory = productAttributeCategoryService.getItem(id);
        return ApiRestResponse.success(productAttributeCategory);
    }

    @ApiOperation("分页获取所有商品属性分类")
    @GetMapping("/list")
    public ApiRestResponse getList(@RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNum) {
        List<PmsProductAttributeCategory> productAttributeCategoryList = productAttributeCategoryService.getList(pageSize, pageNum);
        return ApiRestResponse.success(productAttributeCategoryList);
    }

    @ApiOperation("获取所有商品属性分类及其下属性")
    @GetMapping("/list/withAttr")
    public ApiRestResponse getListWithAttr() {
        List<PmsProductAttributeCategoryItem> productAttributeCategoryResultList = productAttributeCategoryService.getListWithAttr();
        return ApiRestResponse.success(productAttributeCategoryResultList);
    }



}
