package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.SmsFlashPromotionSession;
import com.jayden.mall.model.vo.SmsFlashPromotionSessionDetail;
import com.jayden.mall.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flashSession")
@Api(tags = "SmsFlashPromotionSessionController", description = "限时购场次管理")
public class SmsFlashPromotionSessionController {
    @Autowired
    private SmsFlashPromotionSessionService flashPromotionSessionService;

    @ApiOperation("添加场次")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody SmsFlashPromotionSession promotionSession) {
        int count = flashPromotionSessionService.create(promotionSession);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改场次")
    @PostMapping("/update/{id}")
    public ApiRestResponse update(@PathVariable Long id, @RequestBody SmsFlashPromotionSession promotionSession) {
        int count = flashPromotionSessionService.update(id, promotionSession);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改启用状态")
    @PostMapping("/update/status/{id}")
    public ApiRestResponse updateStatus(@PathVariable Long id, Integer status) {
        int count = flashPromotionSessionService.updateStatus(id, status);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("删除场次")
    @PostMapping("/delete/{id}")
    public ApiRestResponse delete(@PathVariable Long id) {
        int count = flashPromotionSessionService.delete(id);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("获取场次详情")
    @GetMapping("/{id}")
    public ApiRestResponse getItem(@PathVariable Long id) {
        SmsFlashPromotionSession promotionSession = flashPromotionSessionService.getItem(id);
        return ApiRestResponse.success(promotionSession);
    }

    @ApiOperation("获取全部场次")
    @GetMapping("/list")
    public ApiRestResponse list() {
        List<SmsFlashPromotionSession> promotionSessionList = flashPromotionSessionService.list();
        return ApiRestResponse.success(promotionSessionList);
    }

    @ApiOperation("获取全部可选场次及其数量")
    @GetMapping("/selectList")
    public ApiRestResponse selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSessionDetail> promotionSessionList = flashPromotionSessionService.selectList(flashPromotionId);
        return ApiRestResponse.success(promotionSessionList);
    }
}
