package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.PmsProduct;
import com.jayden.mall.model.request.PmsProductParam;
import com.jayden.mall.model.vo.PmsProductQueryParam;
import com.jayden.mall.model.vo.PmsProductResult;
import com.jayden.mall.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "PmsProductController", description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    private PmsProductService productService;

    @ApiOperation("创建商品")
    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody PmsProductParam productParam) {
        int count = productService.create(productParam);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
        return  ApiRestResponse.success();
    }

    @ApiOperation("根据商品id获取商品编辑信息")
    @GetMapping("/updateInfo/{id}")
    public ApiRestResponse getUpdateInfo(@PathVariable Long id) {
        PmsProductResult productResult = productService.getUpdateInfo(id);
        return ApiRestResponse.success(productResult);
    }

    @ApiOperation("更新商品")
    @PostMapping("/update/{id}")
    public ApiRestResponse update(@PathVariable Long id, @RequestBody PmsProductParam productParam) {
        int count = productService.update(id, productParam);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return  ApiRestResponse.success();
    }

    @ApiOperation("查询商品")
    @GetMapping("/list")
    public ApiRestResponse getList(PmsProductQueryParam productQueryParam,
                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProduct> productList = productService.list(productQueryParam, pageSize, pageNum);
        return ApiRestResponse.success(productList);
    }

    @ApiOperation("根据商品名称或货号模糊查询")
    @GetMapping("/simpleList")
    public ApiRestResponse getList(String keyword) {
        List<PmsProduct> productList = productService.list(keyword);
        return ApiRestResponse.success(productList);
    }

    @ApiOperation("批量修改审核状态")
    @PostMapping("/update/verifyStatus")
    public ApiRestResponse updateVerifyStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("verifyStatus") Integer verifyStatus,
                                           @RequestParam("detail") String detail) {
        int count = productService.updateVerifyStatus(ids, verifyStatus, detail);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return  ApiRestResponse.success();
    }

    @ApiOperation("批量上下架")
    @PostMapping("/update/publishStatus")
    public ApiRestResponse updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("publishStatus") Integer publishStatus) {
        int count = productService.updatePublishStatus(ids, publishStatus);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return  ApiRestResponse.success();
    }

    @ApiOperation("批量推荐商品")
    @PostMapping( "/update/recommendStatus")
    public ApiRestResponse updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam("recommendStatus") Integer recommendStatus) {
        int count = productService.updateRecommendStatus(ids, recommendStatus);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return  ApiRestResponse.success();
    }

    @ApiOperation("批量设为新品")
    @PostMapping("/update/newStatus")
    public ApiRestResponse updateNewStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("newStatus") Integer newStatus) {
        int count = productService.updateNewStatus(ids, newStatus);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return  ApiRestResponse.success();
    }

    @ApiOperation("批量修改删除状态")
    @PostMapping("/update/deleteStatus")
    public ApiRestResponse updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("deleteStatus") Integer deleteStatus) {
        int count = productService.updateDeleteStatus(ids, deleteStatus);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return  ApiRestResponse.success();
    }

}
