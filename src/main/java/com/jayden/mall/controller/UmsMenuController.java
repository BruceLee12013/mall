package com.jayden.mall.controller;

import com.github.pagehelper.PageInfo;
import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.UmsMenu;
import com.jayden.mall.model.vo.UmsMenuNode;
import com.jayden.mall.service.UmsMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "UmsMenuController", description = "后台菜单管理")
@RequestMapping("/menu")
public class UmsMenuController {
    @Autowired
    private UmsMenuService menuService;


    @ApiOperation("添加后台菜单")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody UmsMenu umsMenu) {
        int count = menuService.create(umsMenu);
        if (count <= 0) {
           return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success(count);
    }

    @ApiOperation("修改后台菜单")
    @PostMapping("/update/{id}")
    public ApiRestResponse update(@PathVariable Long id,
                               @RequestBody UmsMenu umsMenu) {
        int count = menuService.update(id, umsMenu);
        if (count <= 0) {
            return  ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return  ApiRestResponse.success();
    }


    @ApiOperation("根据ID获取菜单详情")
    @GetMapping("/{id}")
    public ApiRestResponse getItem(@PathVariable Long id) {
        UmsMenu umsMenu = menuService.getItem(id);
        return ApiRestResponse.success(umsMenu);
    }


    @ApiOperation("根据ID删除后台菜单")
    @PostMapping("/delete/{id}")
    public ApiRestResponse delete(@PathVariable Long id) {
        int count = menuService.delete(id);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("分页查询后台菜单")
    @GetMapping("/list/{parentId}")
    public ApiRestResponse list(@PathVariable Long parentId,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsMenu> menuList = menuService.list(parentId, pageSize, pageNum);
        return ApiRestResponse.success(menuList);
    }


    @ApiOperation("树形结构返回所有菜单列表")
    @GetMapping("/treeList")
    public ApiRestResponse<List<UmsMenuNode>> treeList() {
        List<UmsMenuNode> list = menuService.treeList();
        return ApiRestResponse.success(list);
    }

    @ApiOperation("修改菜单显示状态")
    @PostMapping("/updateHidden/{id}")
    public ApiRestResponse updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
        int count = menuService.updateHidden(id, hidden);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }






}
