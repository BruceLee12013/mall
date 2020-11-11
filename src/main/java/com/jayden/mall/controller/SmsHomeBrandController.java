package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.SmsHomeBrand;
import com.jayden.mall.service.SmsHomeBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "SmsHomeBrandController", description = "首页品牌管理")
@RequestMapping("/home/brand")
public class SmsHomeBrandController {
    @Autowired
    private SmsHomeBrandService homeBrandService;

    @ApiOperation("添加首页推荐品牌")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody List<SmsHomeBrand> homeBrandList) {
        int count = homeBrandService.create(homeBrandList);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改品牌排序")
    @PostMapping("/update/sort/{id}")
    public ApiRestResponse updateSort(@PathVariable Long id, Integer sort) {
        int count = homeBrandService.updateSort(id, sort);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("批量删除推荐品牌")
    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam("ids") List<Long> ids) {
        int count = homeBrandService.delete(ids);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("批量修改推荐状态")
    @PostMapping("/update/recommendStatus")
    public ApiRestResponse updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = homeBrandService.updateRecommendStatus(ids, recommendStatus);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("分页查询推荐品牌")
    @GetMapping("/list")
    public ApiRestResponse list(@RequestParam(value = "brandName", required = false) String brandName,
                                                       @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeBrand> homeBrandList = homeBrandService.list(brandName, recommendStatus, pageSize, pageNum);
        return ApiRestResponse.success(homeBrandList);
    }
}
