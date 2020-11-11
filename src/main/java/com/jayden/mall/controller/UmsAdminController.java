package com.jayden.mall.controller;
import com.github.pagehelper.PageInfo;
import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.common.Constant;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.UmsAdmin;
import com.jayden.mall.model.pojo.UmsRole;
import com.jayden.mall.model.request.UmsAdminLoginParam;
import com.jayden.mall.model.request.UmsAdminParam;
import com.jayden.mall.model.request.UpdateAdminPasswordParam;
import com.jayden.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    UmsAdminService adminService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public ApiRestResponse register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success(umsAdmin);
    }


    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public ApiRestResponse login(@RequestParam("username") String username, @RequestParam("password")String password) {
       UmsAdmin umsAdmin= adminService.login(username,password);
       return ApiRestResponse.success(umsAdmin);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/info")
    public ApiRestResponse getAdminInfo(HttpSession session) {

        UmsAdmin currentUser=(UmsAdmin)session.getAttribute(Constant.MALL_USER);
        UmsAdmin umsAdmin = adminService.getAdminByUsername(currentUser.getUsername());
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("menus", umsAdmin.getId());
        data.put("icon", umsAdmin.getIcon());
        if(!CollectionUtils.isEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return ApiRestResponse.success(data);
    }
    @ApiOperation(value = "登出功能")
    @PostMapping("/logout")
    public ApiRestResponse logout(HttpSession session) {
        session.removeAttribute(Constant.MALL_USER);
        return ApiRestResponse.success();
    }
    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping("/list")
    public ApiRestResponse list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfo pageInfo= adminService.list(keyword, pageSize, pageNum);
        return ApiRestResponse.success(pageInfo);
    }
    @ApiOperation("获取指定用户信息")
    @RequestMapping("/{id}")
    public ApiRestResponse getItem(@PathVariable Long id) {
        UmsAdmin admin = adminService.getItem(id);
        return ApiRestResponse.success(admin);
    }
    @ApiOperation("修改指定用户信息")
    @PostMapping("/update/{id}")
    public ApiRestResponse update(@PathVariable Long id, @RequestBody UmsAdmin admin) throws NoSuchAlgorithmException {
         adminService.update(id, admin);
        return ApiRestResponse.success();
    }

    @ApiOperation("修改指定用户密码")
    @PostMapping("/updatePassword")
    public ApiRestResponse updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam) {
        adminService.updatePassword(updatePasswordParam);
       return ApiRestResponse.success();
    }
    @ApiOperation("删除指定用户信息")
    @PostMapping("/delete/{id}")
    public ApiRestResponse delete(@PathVariable Long id) {
        adminService.delete(id);
        return  ApiRestResponse.success();
    }
    @ApiOperation("修改帐号状态")
    @PostMapping("/updateStatus/{id}")
    public ApiRestResponse updateStatus(@PathVariable Long id,@RequestParam(value = "status") Integer status) throws NoSuchAlgorithmException {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);
        adminService.update(id,umsAdmin);
        return ApiRestResponse.success();
    }

    @ApiOperation("给用户分配角色")
    @PostMapping("/role/update")
    public ApiRestResponse updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        adminService.updateRole(adminId, roleIds);
        return ApiRestResponse.success();
    }
    @ApiOperation("获取指定用户的角色")
    @GetMapping("/role/{adminId}")
    public ApiRestResponse getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return ApiRestResponse.success(roleList);
    }

}
