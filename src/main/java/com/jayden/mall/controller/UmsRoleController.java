package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.UmsMenu;
import com.jayden.mall.model.pojo.UmsResource;
import com.jayden.mall.model.pojo.UmsRole;
import com.jayden.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "UmsRoleController", description = "后台用户角色管理")
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    private UmsRoleService roleService;
   @ApiOperation("添加角色")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody UmsRole role) {
        int count = roleService.create(role);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改角色")
    @PostMapping("/update/{id}")
    public ApiRestResponse update(@PathVariable Long id, @RequestBody UmsRole role) {
        int count = roleService.update(id, role);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("批量删除角色")
    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam("ids") List<Long> ids) {
        int count = roleService.delete(ids);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/listAll")
    public ApiRestResponse listAll() {
        List<UmsRole> roleList = roleService.list();
        return ApiRestResponse.success().success(roleList);
    }

    @ApiOperation("根据角色名称分页获取角色列表")
    @GetMapping("/list")
    public ApiRestResponse list(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsRole> roleList = roleService.list(keyword, pageSize, pageNum);
        return ApiRestResponse.success().success(roleList);
    }

    @ApiOperation("修改角色状态")
    @PostMapping("/updateStatus/{id}")
    public ApiRestResponse updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        UmsRole umsRole = new UmsRole();
        umsRole.setStatus(status);
        int count = roleService.update(id, umsRole);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("获取角色相关菜单")
    @GetMapping("/listMenu/{roleId}")
    public ApiRestResponse listMenu(@PathVariable Long roleId) {
        List<UmsMenu> roleList = roleService.listMenu(roleId);
        return ApiRestResponse.success().success(roleList);
    }

    @ApiOperation("获取角色相关资源")
    @GetMapping("/listResource/{roleId}")
    public ApiRestResponse listResource(@PathVariable Long roleId) {
        List<UmsResource> roleList = roleService.listResource(roleId);
        return ApiRestResponse.success(roleList);
    }

    @ApiOperation("给角色分配菜单")
    @PostMapping("/allocMenu")
    public ApiRestResponse allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        return ApiRestResponse.success(count);
    }

    @ApiOperation("给角色分配资源")
    @PostMapping("/allocResource")
    public ApiRestResponse allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        int count = roleService.allocResource(roleId, resourceIds);
        return ApiRestResponse.success(count);
    }



}
