package com.jayden.mall.service;

import com.github.pagehelper.PageInfo;
import com.jayden.mall.model.pojo.UmsAdmin;
import com.jayden.mall.model.pojo.UmsPermission;
import com.jayden.mall.model.pojo.UmsResource;
import com.jayden.mall.model.pojo.UmsRole;
import com.jayden.mall.model.request.UmsAdminParam;
import com.jayden.mall.model.request.UpdateAdminPasswordParam;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 后台管理员Service
 * Created by macro on 2018/4/26.
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
//    String login(String username, String password);

    UmsAdmin login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    UmsAdmin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    PageInfo list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    void update(Long id, UmsAdmin admin) throws NoSuchAlgorithmException;

    /**
     * 删除指定用户
     */
    void delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    void updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);


    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);

}
