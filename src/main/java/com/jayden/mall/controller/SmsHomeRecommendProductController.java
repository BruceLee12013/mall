package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.SmsHomeRecommendProduct;
import com.jayden.mall.service.SmsHomeRecommendProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "SmsHomeRecommendProductController", description = "首页人气推荐管理")
@RequestMapping("/home/recommendProduct")
public class SmsHomeRecommendProductController {
    @Autowired
    private SmsHomeRecommendProductService recommendProductService;

    @ApiOperation("添加首页推荐")
    @PostMapping("/create")
    @ResponseBody
    public ApiRestResponse create(@RequestBody List<SmsHomeRecommendProduct> homeBrandList) {
        int count = recommendProductService.create(homeBrandList);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改推荐排序")
    @PostMapping("/update/sort/{id}")
    public ApiRestResponse updateSort(@PathVariable Long id, Integer sort) {
        int count = recommendProductService.updateSort(id, sort);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("批量删除推荐")
    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam("ids") List<Long> ids) {
        int count = recommendProductService.delete(ids);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("批量修改推荐状态")
    @PostMapping("/update/recommendStatus")
    @ResponseBody
    public ApiRestResponse updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = recommendProductService.updateRecommendStatus(ids, recommendStatus);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("分页查询推荐")
    @GetMapping("/list")
    public ApiRestResponse list(@RequestParam(value = "productName", required = false) String productName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeRecommendProduct> homeBrandList = recommendProductService.list(productName, recommendStatus, pageSize, pageNum);
        return ApiRestResponse.success(homeBrandList);
    }
}

