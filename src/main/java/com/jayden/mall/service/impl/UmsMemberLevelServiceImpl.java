package com.jayden.mall.service.impl;

import com.jayden.mall.model.dao.UmsMemberLevelMapper;
import com.jayden.mall.model.pojo.UmsMemberLevel;
import com.jayden.mall.model.pojo.UmsMemberLevelExample;
import com.jayden.mall.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;
    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        example.createCriteria().andDefaultStatusEqualTo(defaultStatus);
        return memberLevelMapper.selectByExample(example);
    }
}
