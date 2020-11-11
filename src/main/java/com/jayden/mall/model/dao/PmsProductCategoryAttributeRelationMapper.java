package com.jayden.mall.model.dao;

import com.jayden.mall.model.pojo.PmsProductCategoryAttributeRelation;
import com.jayden.mall.model.pojo.PmsProductCategoryAttributeRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsProductCategoryAttributeRelationMapper {
    long countByExample(PmsProductCategoryAttributeRelationExample example);

    int deleteByExample(PmsProductCategoryAttributeRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsProductCategoryAttributeRelation record);

    int insertSelective(PmsProductCategoryAttributeRelation record);

    List<PmsProductCategoryAttributeRelation> selectByExample(PmsProductCategoryAttributeRelationExample example);

    PmsProductCategoryAttributeRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsProductCategoryAttributeRelation record, @Param("example") PmsProductCategoryAttributeRelationExample example);

    int updateByExample(@Param("record") PmsProductCategoryAttributeRelation record, @Param("example") PmsProductCategoryAttributeRelationExample example);

    int updateByPrimaryKeySelective(PmsProductCategoryAttributeRelation record);

    int updateByPrimaryKey(PmsProductCategoryAttributeRelation record);

    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsProductCategoryAttributeRelation> productCategoryAttributeRelationList);
}