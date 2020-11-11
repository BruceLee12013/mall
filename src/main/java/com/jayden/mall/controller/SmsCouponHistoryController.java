package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.model.pojo.SmsCouponHistory;
import com.jayden.mall.service.SmsCouponHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "SmsCouponHistoryController", description = "优惠券领取记录管理")
@RequestMapping("/couponHistory")
public class SmsCouponHistoryController {
    @Autowired
    private SmsCouponHistoryService historyService;

    @ApiOperation("根据优惠券id，使用状态，订单编号分页获取领取记录")
    @GetMapping("/list")
    public ApiRestResponse list(@RequestParam(value = "couponId", required = false) Long couponId,
                                @RequestParam(value = "useStatus", required = false) Integer useStatus,
                                @RequestParam(value = "orderSn", required = false) String orderSn,
                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsCouponHistory> historyList = historyService.list(couponId, useStatus, orderSn, pageSize, pageNum);
        return ApiRestResponse.success(historyList);
    }
}

