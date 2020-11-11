package com.jayden.mall.model.dao;

import com.jayden.mall.model.pojo.UmsMemberMemberTagRelation;
import com.jayden.mall.model.pojo.UmsMemberMemberTagRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberMemberTagRelationMapper {
    long countByExample(UmsMemberMemberTagRelationExample example);

    int deleteByExample(UmsMemberMemberTagRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberMemberTagRelation record);

    int insertSelective(UmsMemberMemberTagRelation record);

    List<UmsMemberMemberTagRelation> selectByExample(UmsMemberMemberTagRelationExample example);

    UmsMemberMemberTagRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberMemberTagRelation record, @Param("example") UmsMemberMemberTagRelationExample example);

    int updateByExample(@Param("record") UmsMemberMemberTagRelation record, @Param("example") UmsMemberMemberTagRelationExample example);

    int updateByPrimaryKeySelective(UmsMemberMemberTagRelation record);

    int updateByPrimaryKey(UmsMemberMemberTagRelation record);
}