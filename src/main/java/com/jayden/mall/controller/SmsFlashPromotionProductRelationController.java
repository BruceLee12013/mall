package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.SmsFlashPromotionProductRelation;
import com.jayden.mall.model.vo.SmsFlashPromotionProduct;
import com.jayden.mall.service.SmsFlashPromotionProductRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "SmsFlashPromotionProductRelationController", description = "限时购和商品关系管理")
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {
    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    @ApiOperation("批量选择商品添加关联")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody List<SmsFlashPromotionProductRelation> relationList) {
        int count = relationService.create(relationList);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改关联相关信息")
    @PostMapping("/update/{id}")
    public ApiRestResponse<Object> update(@PathVariable Long id, @RequestBody SmsFlashPromotionProductRelation relation) {
        int count = relationService.update(id, relation);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("删除关联")
    @PostMapping("/delete/{id}")
    public ApiRestResponse<Object> delete(@PathVariable Long id) {
        int count = relationService.delete(id);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("获取管理商品促销信息")
    @GetMapping("/{id}")
    public ApiRestResponse getItem(@PathVariable Long id) {
        SmsFlashPromotionProductRelation relation = relationService.getItem(id);
        return ApiRestResponse.success(relation);
    }

    @ApiOperation("分页查询不同场次关联及商品信息")
    @GetMapping("/list")
    public ApiRestResponse list(@RequestParam(value = "flashPromotionId") Long flashPromotionId,
                                                                   @RequestParam(value = "flashPromotionSessionId") Long flashPromotionSessionId,
                                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsFlashPromotionProduct> flashPromotionProductList = relationService.list(flashPromotionId, flashPromotionSessionId, pageSize, pageNum);
        return ApiRestResponse.success(flashPromotionProductList);
    }
}
