package com.lgoshop.portal.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.PmsProduct;
import com.lgoshop.portal.domain.PmsPortalProductDetail;
import com.lgoshop.portal.domain.PmsProductCategoryNode;
import com.lgoshop.portal.service.PmsPortalProductService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前台商品管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "PmsPortalProductController")
@Tag(name = "PmsPortalProductController", description = "前台商品管理")
@RequestMapping("/product")
@LgoWebLog
public class PmsPortalProductController {

    @Autowired
    private PmsPortalProductService portalProductService;

    @ApiOperation(value = "综合搜索、筛选、排序")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<PmsProduct>> search(@RequestParam(required = false) String keyword,
                                                       @RequestParam(required = false) Long brandId,
                                                       @RequestParam(required = false) Long productCategoryId,
                                                       @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                       @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                       @RequestParam(required = false, defaultValue = "0") Integer sort) {
        IPage<PmsProduct> productList = portalProductService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return LgoResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation("以树形结构获取所有商品分类")
    @RequestMapping(value = "/categoryTreeList", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<PmsProductCategoryNode>> categoryTreeList() {
        List<PmsProductCategoryNode> list = portalProductService.categoryTreeList();
        return LgoResult.success(list);
    }

    @ApiOperation("获取前台商品详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<PmsPortalProductDetail> detail(@PathVariable Long id) {
        PmsPortalProductDetail productDetail = portalProductService.detail(id);
        return LgoResult.success(productDetail);
    }
}
