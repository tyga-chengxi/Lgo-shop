package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.SmsHomeNewProduct;
import com.lgoshop.service.SmsHomeNewProductService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页新品管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "SmsHomeNewProductController")
@Tag(name = "SmsHomeNewProductController", description = "首页新品管理")
@RequestMapping("/home/newProduct")
@LgoWebLog
public class SmsHomeNewProductController {
    @Autowired
    private SmsHomeNewProductService homeNewProductService;

    @ApiOperation("批量添加首页新品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody List<SmsHomeNewProduct> homeNewProductList) {
        int count = homeNewProductService.create(homeNewProductList);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("修改首页新品排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateSort(@PathVariable Long id, Integer sort) {
        int count = homeNewProductService.updateSort(id, sort);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("批量删除首页新品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@RequestParam("ids") List<Long> ids) {
        int count = homeNewProductService.delete(ids);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("批量修改首页新品状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = homeNewProductService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("分页查询首页新品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<SmsHomeNewProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                            @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<SmsHomeNewProduct> homeNewProductList = homeNewProductService.list(productName, recommendStatus, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(homeNewProductList));
    }
}
