package com.jayden.mall.service;

import com.github.pagehelper.PageInfo;
import com.jayden.mall.model.pojo.PmsBrand;

import java.util.List;

public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    PageInfo listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
