package com.jayden.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jayden.mall.exception.BusinessException;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.dao.UmsAdminLoginLogMapper;
import com.jayden.mall.model.dao.UmsAdminMapper;
import com.jayden.mall.model.dao.UmsAdminRoleRelationMapper;
import com.jayden.mall.model.pojo.*;
import com.jayden.mall.model.request.UmsAdminParam;
import com.jayden.mall.model.request.UpdateAdminPasswordParam;
import com.jayden.mall.service.UmsAdminService;
import com.jayden.mall.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationDao;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;


    @Override
    public UmsAdmin getAdminByUsername(String username) {

        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if(adminList!=null)
        {
            return adminList.get(0);
        }
        return  null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = null;
        try {
            encodePassword = MD5Utils.getMD5Str(umsAdmin.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        umsAdmin.setPassword(encodePassword);
        int count=adminMapper.insert(umsAdmin);
        if(count<=0)
        {
            throw new BusinessException(BusinessExceptionEnum.CREATE_FAILED);
        }
        umsAdmin.setPassword(null);
        return umsAdmin;
    }

    @Override
    public UmsAdmin login(String username, String password) {
        try {
            UmsAdminExample example=new UmsAdminExample();
            example.createCriteria().andUsernameEqualTo(username);
             List<UmsAdmin> list=adminMapper.selectByExample(example);
             if(list==null||!list.get(0).getPassword().equals(MD5Utils.getMD5Str(password)))
             {
                 throw new BusinessException(BusinessExceptionEnum.WRONG_PASSWORD);
             }
             list.get(0).setPassword(null);
            insertLoginLog(username);
            return  list.get(0);
        } catch (Exception e) {
//            LOGGER.warn("登录异常:{}", e.getMessage());
            throw new BusinessException(BusinessExceptionEnum.SYSTEM_ERROR);
        }
    }


    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if(admin==null) return;
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }


    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample example = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
        }
        List<UmsAdmin> list= adminMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void update(Long id, UmsAdmin admin) throws NoSuchAlgorithmException {
        admin.setId(id);
        UmsAdmin rawAdmin = adminMapper.selectByPrimaryKey(id);
        if(rawAdmin.getPassword().equals(admin.getPassword())){
            //与原加密密码相同的不需要修改
            admin.setPassword(null);
        }else{
            //与原加密密码不同的需要加密修改
            if(StringUtils.isEmpty(admin.getPassword())){
                admin.setPassword(null);
            }else{
                admin.setPassword(MD5Utils.getMD5Str(admin.getPassword()));
            }
        }
        int count = adminMapper.updateByPrimaryKeySelective(admin);
        if(count<=0)
        {
            throw new BusinessException(BusinessExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Long id) {
        int count = adminMapper.deleteByPrimaryKey(id);
        if (count<=0)
        {
            throw new BusinessException(BusinessExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public void updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        UmsAdminRoleRelationExample adminRoleRelationExample = new UmsAdminRoleRelationExample();
        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationDao.insertList(list);
        }
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return null;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam updatePasswordParam) {
        return 0;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }
}
