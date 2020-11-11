package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.SmsFlashPromotion;
import com.jayden.mall.service.SmsFlashPromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "SmsFlashPromotionController", description = "限时购活动管理")
@RequestMapping("/flash")
public class SmsFlashPromotionController {
    @Autowired
    private SmsFlashPromotionService flashPromotionService;

    @ApiOperation("添加活动")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody SmsFlashPromotion flashPromotion) {
        int count = flashPromotionService.create(flashPromotion);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("编辑活动信息")
    @PostMapping("/update/{id}")
    public ApiRestResponse update(@PathVariable Long id, @RequestBody SmsFlashPromotion flashPromotion) {
        int count = flashPromotionService.update(id, flashPromotion);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("删除活动信息")
    @PostMapping("/delete/{id}")
    public ApiRestResponse delete(@PathVariable Long id) {
        int count = flashPromotionService.delete(id);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改上下线状态")
    @PostMapping("/update/status/{id}")
    public ApiRestResponse update(@PathVariable Long id, Integer status) {
        int count = flashPromotionService.updateStatus(id, status);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("获取活动详情")
    @GetMapping("/{id}")
    public ApiRestResponse getItem(@PathVariable Long id) {
        SmsFlashPromotion flashPromotion = flashPromotionService.getItem(id);
        return ApiRestResponse.success(flashPromotion);
    }

    @ApiOperation("根据活动名称分页查询")
    @GetMapping("/list")
    public ApiRestResponse getItem(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsFlashPromotion> flashPromotionList = flashPromotionService.list(keyword, pageSize, pageNum);
        return ApiRestResponse.success(flashPromotionList);
    }
}
