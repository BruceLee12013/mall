package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.OmsOrderReturnApply;
import com.jayden.mall.model.request.OmsReturnApplyQueryParam;
import com.jayden.mall.model.request.OmsUpdateStatusParam;
import com.jayden.mall.model.vo.OmsOrderReturnApplyResult;
import com.jayden.mall.service.OmsOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "OmsOrderReturnApplyController", description = "订单退货申请管理")
@RestController
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {
    @Autowired
    private OmsOrderReturnApplyService returnApplyService;

    @ApiOperation("分页查询退货申请")
    @GetMapping("/list")
    public ApiRestResponse list(OmsReturnApplyQueryParam queryParam,
                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderReturnApply> returnApplyList = returnApplyService.list(queryParam, pageSize, pageNum);
        return ApiRestResponse.success(returnApplyList);
    }

    @ApiOperation("批量删除申请")
    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam("ids") List<Long> ids) {
        int count = returnApplyService.delete(ids);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
        return ApiRestResponse.success();
    }

    @ApiOperation("获取退货申请详情")
    @GetMapping("/{id}")
    public ApiRestResponse getItem(@PathVariable Long id) {
        OmsOrderReturnApplyResult result = returnApplyService.getItem(id);
        return ApiRestResponse.success(result);
    }

    @ApiOperation("修改申请状态")
    @PostMapping("/update/status/{id}")
    public ApiRestResponse updateStatus(@PathVariable Long id, @RequestBody OmsUpdateStatusParam statusParam) {
        int count = returnApplyService.updateStatus(id, statusParam);
        if (count <= 0) {
            return ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
        return ApiRestResponse.success();
    }
}
