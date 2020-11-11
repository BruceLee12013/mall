package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.PmsProductCategory;
import com.jayden.mall.model.request.PmsProductCategoryParam;
import com.jayden.mall.model.vo.PmsProductCategoryWithChildrenItem;
import com.jayden.mall.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    private PmsProductCategoryService productCategoryService;
    @ApiOperation("添加产品分类")
    @PostMapping("/create")
    public ApiRestResponse create(@Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = productCategoryService.create(productCategoryParam);
        if (count <= 0) {
           return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

  @ApiOperation("修改商品分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ApiRestResponse update(@PathVariable Long id,
                         @Validated
                         @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = productCategoryService.update(id, productCategoryParam);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("分页查询商品分类")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public ApiRestResponse getList(@PathVariable Long parentId,
                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductCategory> productCategoryList = productCategoryService.getList(parentId, pageSize, pageNum);
        return ApiRestResponse.success(productCategoryList);
    }

    @ApiOperation("根据id获取商品分类")
    @GetMapping("/{id}")
    public ApiRestResponse getItem(@PathVariable Long id) {
        PmsProductCategory productCategory = productCategoryService.getItem(id);
        return ApiRestResponse.success(productCategory);
    }

    @ApiOperation("删除商品分类")
    @PostMapping("/delete/{id}")
    public ApiRestResponse delete(@PathVariable Long id) {
        int count = productCategoryService.delete(id);
        if (count <= 0) {
           return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改导航栏显示状态")
    @PostMapping("/update/navStatus")
    public ApiRestResponse updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        int count = productCategoryService.updateNavStatus(ids, navStatus);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改显示状态")
    @PostMapping("/update/showStatus")
    public ApiRestResponse updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        int count = productCategoryService.updateShowStatus(ids, showStatus);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }
    @ApiOperation("查询所有一级分类及子分类")
    @GetMapping("/list/withChildren")
    public ApiRestResponse listWithChildren() {
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return ApiRestResponse.success(list);
    }


}
