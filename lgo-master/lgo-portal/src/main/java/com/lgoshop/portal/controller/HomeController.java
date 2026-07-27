package com.lgoshop.portal.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.CmsSubject;
import com.lgoshop.model.PmsProduct;
import com.lgoshop.model.PmsProductCategory;
import com.lgoshop.portal.domain.HomeContentResult;
import com.lgoshop.portal.service.HomeService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页内容管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "HomeController")
@Tag(name = "HomeController", description = "首页内容管理")
@RequestMapping("/home")
@LgoWebLog
public class HomeController {
    @Autowired
    private HomeService homeService;

    @ApiOperation("首页内容信息展示")
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<HomeContentResult> content() {
        HomeContentResult contentResult = homeService.content();
        return LgoResult.success(contentResult);
    }

    @ApiOperation("分页获取推荐商品")
    @RequestMapping(value = "/recommendProductList", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<IPage<PmsProduct>> recommendProductList(@RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<PmsProduct> productList = homeService.recommendProductList(pageSize, pageNum);
        return LgoResult.success(productList);
    }

    @ApiOperation("获取首页商品分类")
    @RequestMapping(value = "/productCateList/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<PmsProductCategory>> getProductCateList(@PathVariable Long parentId) {
        List<PmsProductCategory> productCategoryList = homeService.getProductCateList(parentId);
        return LgoResult.success(productCategoryList);
    }

    @ApiOperation("根据分类分页获取专题")
    @RequestMapping(value = "/subjectList", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<IPage<CmsSubject>> getSubjectList(@RequestParam(required = false) Long cateId,
                                                         @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<CmsSubject> subjectList = homeService.getSubjectList(cateId,pageSize,pageNum);
        return LgoResult.success(subjectList);
    }

    @ApiOperation("分页获取人气推荐商品")
    @RequestMapping(value = "/hotProductList", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<PmsProduct>> hotProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        List<PmsProduct> productList = homeService.hotProductList(pageNum,pageSize);
        return LgoResult.success(productList);
    }

    @ApiOperation("分页获取新品推荐商品")
    @RequestMapping(value = "/newProductList", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<PmsProduct>> newProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        List<PmsProduct> productList = homeService.newProductList(pageNum,pageSize);
        return LgoResult.success(productList);
    }
}
