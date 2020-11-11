package com.jayden.mall.service;

import com.jayden.mall.model.pojo.OmsOrderReturnApply;
import com.jayden.mall.model.request.OmsReturnApplyQueryParam;
import com.jayden.mall.model.request.OmsUpdateStatusParam;
import com.jayden.mall.model.vo.OmsOrderReturnApplyResult;

import java.util.List;

public interface OmsOrderReturnApplyService {
    /**
     * 分页查询申请
     */
    List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量删除申请
     */
    int delete(List<Long> ids);

    /**
     * 修改申请状态
     */
    int updateStatus(Long id, OmsUpdateStatusParam statusParam);

    /**
     * 获取指定申请详情
     */
    OmsOrderReturnApplyResult getItem(Long id);
}
