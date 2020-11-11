package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.UmsResourceCategory;
import com.jayden.mall.service.UmsResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "UmsResourceCategoryController", description = "后台资源分类管理")
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {
    @Autowired
    private UmsResourceCategoryService resourceCategoryService;
    @ApiOperation("查询所有后台资源分类")
    @GetMapping("/listAll")
    public ApiRestResponse listAll() {
        List<UmsResourceCategory> resourceList = resourceCategoryService.listAll();
        return ApiRestResponse.success(resourceList);
    }

    @ApiOperation("添加后台资源分类")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody UmsResourceCategory umsResourceCategory) {
        int count = resourceCategoryService.create(umsResourceCategory);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改后台资源分类")
    @PostMapping("/update/{id}")
    public ApiRestResponse<Object> update(@PathVariable Long id,
                                          @RequestBody UmsResourceCategory umsResourceCategory) {
        int count = resourceCategoryService.update(id, umsResourceCategory);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("根据ID删除后台资源")
    @PostMapping("/delete/{id}")
    public ApiRestResponse<Object> delete(@PathVariable Long id) {
        int count = resourceCategoryService.delete(id);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }
}
