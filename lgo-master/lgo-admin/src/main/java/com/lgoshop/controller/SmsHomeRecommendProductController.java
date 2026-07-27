package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.SmsHomeRecommendProduct;
import com.lgoshop.service.SmsHomeRecommendProductService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页人气推荐管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "SmsHomeRecommendProductController")
@Tag(name = "SmsHomeRecommendProductController", description = "首页人气推荐管理")
@RequestMapping("/home/recommendProduct")
@LgoWebLog
public class SmsHomeRecommendProductController {
    @Autowired
    private SmsHomeRecommendProductService recommendProductService;

    @ApiOperation("添加首页推荐")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody List<SmsHomeRecommendProduct> homeRecommendProductList) {
        int count = recommendProductService.create(homeRecommendProductList);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("修改推荐排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateSort(@PathVariable Long id, Integer sort) {
        int count = recommendProductService.updateSort(id, sort);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("批量删除推荐")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@RequestParam("ids") List<Long> ids) {
        int count = recommendProductService.delete(ids);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = recommendProductService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("分页查询推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<SmsHomeRecommendProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<SmsHomeRecommendProduct> homeRecommendProductList = recommendProductService.list(productName, recommendStatus, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(homeRecommendProductList));
    }
}
