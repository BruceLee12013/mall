package com.jayden.mall.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jayden.mall.model.dao.PmsBrandMapper;
import com.jayden.mall.model.pojo.PmsBrand;
import com.jayden.mall.model.pojo.PmsBrandExample;
import com.jayden.mall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper brandMapper;
    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int createBrand(PmsBrand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, "show_status, sort");
        List<PmsBrand> pmsBrandList=brandMapper.selectByExample(new PmsBrandExample());
        PageInfo pageInfo = new PageInfo(pmsBrandList);
        return pageInfo;
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
