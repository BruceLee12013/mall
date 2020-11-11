package com.jayden.mall.controller;

import com.jayden.mall.common.ApiRestResponse;
import com.jayden.mall.model.pojo.CmsSubject;
import com.jayden.mall.service.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "CmsSubjectController", description = "商品专题管理")
@RestController
@RequestMapping("/subject")
public class CmsSubjectController {
    @Autowired
    private CmsSubjectService subjectService;

    @ApiOperation("获取全部商品专题")
    @GetMapping("/listAll")
    public ApiRestResponse listAll() {
        List<CmsSubject> subjectList = subjectService.listAll();
        return ApiRestResponse.success(subjectList);
    }

    @ApiOperation(value = "根据专题名称分页获取专题")
    @GetMapping("/list")
    public ApiRestResponse getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<CmsSubject> subjectList = subjectService.list(keyword, pageNum, pageSize);
        return ApiRestResponse.success(subjectList);
    }
}
