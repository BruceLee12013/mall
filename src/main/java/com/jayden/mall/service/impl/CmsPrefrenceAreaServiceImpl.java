package com.jayden.mall.service.impl;

import com.jayden.mall.model.dao.CmsPrefrenceAreaMapper;
import com.jayden.mall.model.pojo.CmsPrefrenceArea;
import com.jayden.mall.model.pojo.CmsPrefrenceAreaExample;
import com.jayden.mall.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
