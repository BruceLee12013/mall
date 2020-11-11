package com.jayden.mall.model.dao;

import com.jayden.mall.model.pojo.PmsProductCategory;
import com.jayden.mall.model.pojo.PmsProductCategoryExample;
import java.util.List;

import com.jayden.mall.model.vo.PmsProductCategoryWithChildrenItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsProductCategoryMapper {
    long countByExample(PmsProductCategoryExample example);

    int deleteByExample(PmsProductCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsProductCategory record);

    int insertSelective(PmsProductCategory record);

    List<PmsProductCategory> selectByExampleWithBLOBs(PmsProductCategoryExample example);

    List<PmsProductCategory> selectByExample(PmsProductCategoryExample example);

    PmsProductCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsProductCategory record, @Param("example") PmsProductCategoryExample example);

    int updateByExampleWithBLOBs(@Param("record") PmsProductCategory record, @Param("example") PmsProductCategoryExample example);

    int updateByExample(@Param("record") PmsProductCategory record, @Param("example") PmsProductCategoryExample example);

    int updateByPrimaryKeySelective(PmsProductCategory record);

    int updateByPrimaryKeyWithBLOBs(PmsProductCategory record);

    int updateByPrimaryKey(PmsProductCategory record);
    /**
     * 获取商品分类及其子分类
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();



}