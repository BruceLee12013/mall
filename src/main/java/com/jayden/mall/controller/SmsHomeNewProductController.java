package com.jayden.mall.controller;
import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.SmsHomeNewProduct;
import com.jayden.mall.service.SmsHomeNewProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "SmsHomeNewProductController", description = "首页新品管理")
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {
    @Autowired
    private SmsHomeNewProductService homeNewProductService;

    @ApiOperation("添加首页推荐品牌")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody List<SmsHomeNewProduct> homeBrandList) {
        int count = homeNewProductService.create(homeBrandList);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("修改推荐排序")
    @PostMapping("/update/sort/{id}")
    @ResponseBody
    public ApiRestResponse updateSort(@PathVariable Long id, Integer sort) {
        int count = homeNewProductService.updateSort(id, sort);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("批量删除推荐")
    @PostMapping("/delete")
    @ResponseBody
    public ApiRestResponse delete(@RequestParam("ids") List<Long> ids) {
        int count = homeNewProductService.delete(ids);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("批量修改推荐状态")
    @PostMapping("/update/recommendStatus")
    @ResponseBody
    public ApiRestResponse updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = homeNewProductService.updateRecommendStatus(ids, recommendStatus);
        if (count <=0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("分页查询推荐")
    @GetMapping("/list")
    @ResponseBody
    public ApiRestResponse list(@RequestParam(value = "productName", required = false) String productName,
                                                            @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeNewProduct> homeBrandList = homeNewProductService.list(productName, recommendStatus, pageSize, pageNum);
        return ApiRestResponse.success(homeBrandList);
    }
}
