package com.lgoshop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.CmsSubject;
import com.lgoshop.service.CmsSubjectService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品专题管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "CmsSubjectController")
@Tag(name = "CmsSubjectController", description = "商品专题管理")
@RequestMapping("/subject")
@LgoWebLog
public class CmsSubjectController {
    @Autowired
    private CmsSubjectService subjectService;

    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<CmsSubject>> listAll() {
        List<CmsSubject> subjectList = subjectService.listAll();
        return LgoResult.success(subjectList);
    }

    @ApiOperation(value = "根据专题名称分页获取商品专题")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<CmsSubject>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        IPage<CmsSubject> subjectList = subjectService.list(keyword, pageNum, pageSize);
        return LgoResult.success(CommonPage.restPage(subjectList));
    }
}
