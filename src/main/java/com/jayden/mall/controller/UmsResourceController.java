package com.jayden.mall.controller;
import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.UmsResource;
import com.jayden.mall.service.UmsResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping("/resource")
public class UmsResourceController {
    @Autowired
    private UmsResourceService resourceService;


    @ApiOperation("添加后台资源")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody UmsResource umsResource) {
        int count = resourceService.create(umsResource);
        if (count <=0) {
           return  ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改后台资源")
    @PostMapping("/update/{id}")
    public ApiRestResponse update(@PathVariable Long id,
                               @RequestBody UmsResource umsResource) {
        int count = resourceService.update(id, umsResource);
        if (count <=0) {
            return  ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("根据ID获取资源详情")
    @GetMapping("/{id}")
    public ApiRestResponse getItem(@PathVariable Long id) {
        UmsResource umsResource = resourceService.getItem(id);
        return ApiRestResponse.success(umsResource);
    }

    @ApiOperation("根据ID删除后台资源")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public ApiRestResponse delete(@PathVariable Long id) {
        int count = resourceService.delete(id);
        if (count <=0) {
            return  ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("分页模糊查询后台资源")
    @GetMapping("/list")
    public ApiRestResponse list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsResource> resourceList = resourceService.list(categoryId,nameKeyword, urlKeyword, pageSize, pageNum);
        return ApiRestResponse.success(resourceList);
    }

    @ApiOperation("查询所有后台资源")
    @GetMapping("/listAll")
    public ApiRestResponse listAll() {
        List<UmsResource> resourceList = resourceService.listAll();
        return ApiRestResponse.success(resourceList);
    }
}
