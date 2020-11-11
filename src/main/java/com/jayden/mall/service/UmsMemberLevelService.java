package com.jayden.mall.service;

import com.jayden.mall.model.pojo.UmsMemberLevel;

import java.util.List;

public interface UmsMemberLevelService {
    /**
     * 获取所有会员登录
     * @param defaultStatus 是否为默认会员
     */
    List<UmsMemberLevel> list(Integer defaultStatus);
}
