package com.jayden.mall.controller;
import com.github.pagehelper.PageInfo;
import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.exception.BusinessExceptionEnum;
import com.jayden.mall.model.pojo.PmsBrand;
import com.jayden.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService demoService;
    @ApiOperation(value = "获取全部品牌列表")
    @GetMapping("/listAll")
    public ApiRestResponse getBrandList() {
        return ApiRestResponse.success(demoService.listAllBrand());
    }
    @ApiOperation(value = "添加品牌")
    @PostMapping("/create")
    public ApiRestResponse createBrand(@RequestBody PmsBrand pmsBrand) {

        int count = demoService.createBrand(pmsBrand);
        if (count == 1) {
            return  ApiRestResponse.success(pmsBrand);
        } else {
          return ApiRestResponse.error(BusinessExceptionEnum.CREATE_FAILED);
        }
    }

    @ApiOperation(value = "更新品牌")
    @PostMapping("/update/{id}")
    public ApiRestResponse updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
        int count = demoService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            return  ApiRestResponse.success(pmsBrandDto);
        } else {
            return  ApiRestResponse.error(BusinessExceptionEnum.UPDATE_FAILED);
        }
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiRestResponse deleteBrand(@PathVariable("id") Long id) {
        int count = demoService.deleteBrand(id);
        if (count == 1) {
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(BusinessExceptionEnum.DELETE_FAILED);
        }
    }
    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @GetMapping("/list")
    public ApiRestResponse listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {

        PageInfo pageInfo = demoService.listBrand(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);

    }
    @ApiOperation(value = "根据编号查询品牌信息")
    @GetMapping("/{id}")
    public ApiRestResponse<PmsBrand> brand(@PathVariable("id") Long id) {
        return ApiRestResponse.success(demoService.getBrand(id));
    }
}
