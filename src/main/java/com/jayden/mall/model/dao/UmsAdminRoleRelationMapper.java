package com.jayden.mall.model.dao;

import com.jayden.mall.model.pojo.UmsAdminRoleRelation;
import com.jayden.mall.model.pojo.UmsAdminRoleRelationExample;
import java.util.List;

import com.jayden.mall.model.pojo.UmsPermission;
import com.jayden.mall.model.pojo.UmsRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface UmsAdminRoleRelationMapper {
    long countByExample(UmsAdminRoleRelationExample example);

    int deleteByExample(UmsAdminRoleRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminRoleRelation record);

    int insertSelective(UmsAdminRoleRelation record);

    List<UmsAdminRoleRelation> selectByExample(UmsAdminRoleRelationExample example);

    UmsAdminRoleRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAdminRoleRelation record, @Param("example") UmsAdminRoleRelationExample example);

    int updateByExample(@Param("record") UmsAdminRoleRelation record, @Param("example") UmsAdminRoleRelationExample example);

    int updateByPrimaryKeySelective(UmsAdminRoleRelation record);

    int updateByPrimaryKey(UmsAdminRoleRelation record);
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);
    /**
     * 获取用于所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);
    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);

}