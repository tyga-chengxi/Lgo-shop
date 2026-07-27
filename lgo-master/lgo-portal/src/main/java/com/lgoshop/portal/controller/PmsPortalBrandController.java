package com.lgoshop.portal.controller;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.PmsBrand;
import com.lgoshop.model.PmsProduct;
import com.lgoshop.portal.service.PmsPortalBrandService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页品牌推荐管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "PmsPortalBrandController")
@Tag(name = "PmsPortalBrandController", description = "前台品牌管理")
@RequestMapping("/brand")
@LgoWebLog
public class PmsPortalBrandController {

    @Autowired
    private PmsPortalBrandService portalBrandService;

    @ApiOperation("分页获取推荐品牌")
    @RequestMapping(value = "/recommendList", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<PmsBrand>> recommendList(@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsBrand> brandList = portalBrandService.recommendList(pageNum, pageSize);
        return LgoResult.success(brandList);
    }

    @ApiOperation("获取品牌详情")
    @RequestMapping(value = "/detail/{brandId}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<PmsBrand> detail(@PathVariable Long brandId) {
        PmsBrand brand = portalBrandService.detail(brandId);
        return LgoResult.success(brand);
    }

    @ApiOperation("分页获取品牌相关商品")
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<PmsProduct>> productList(@RequestParam Long brandId,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        CommonPage<PmsProduct> result = portalBrandService.productList(brandId,pageNum, pageSize);
        return LgoResult.success(result);
    }
}
