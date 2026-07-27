package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.dto.PmsProductParam;
import com.lgoshop.dto.PmsProductQueryParam;
import com.lgoshop.dto.PmsProductResult;
import com.lgoshop.model.PmsProduct;
import com.lgoshop.service.PmsProductService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "PmsProductController")
@Tag(name = "PmsProductController", description = "商品管理")
@RequestMapping("/product")
@LgoWebLog
public class PmsProductController {
    @Autowired
    private PmsProductService productService;

    @ApiOperation("创建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody PmsProductParam productParam) {
        int count = productService.create(productParam);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("根据商品id获取商品编辑信息")
    @RequestMapping(value = "/updateInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<PmsProductResult> getUpdateInfo(@PathVariable Long id) {
        PmsProductResult productResult = productService.getUpdateInfo(id);
        return LgoResult.success(productResult);
    }

    @ApiOperation("根据ID修改商品信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long id, @RequestBody PmsProductParam productParam) {
        int count = productService.update(id, productParam);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<PmsProduct>> getList(PmsProductQueryParam productQueryParam,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<PmsProduct> productList = productService.list(productQueryParam, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation("根据商品名称或货号模糊查询")
    @RequestMapping(value = "/simpleList", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<PmsProduct>> getList(String keyword) {
        List<PmsProduct> productList = productService.list(keyword);
        return LgoResult.success(productList);
    }

    @ApiOperation("批量修改审核状态")
    @RequestMapping(value = "/update/verifyStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateVerifyStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("verifyStatus") Integer verifyStatus,
                                           @RequestParam("detail") String detail) {
        int count = productService.updateVerifyStatus(ids, verifyStatus, detail);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("批量上下架商品")
    @RequestMapping(value = "/update/publishStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("publishStatus") Integer publishStatus) {
        int count = productService.updatePublishStatus(ids, publishStatus);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("批量推荐商品")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam("recommendStatus") Integer recommendStatus) {
        int count = productService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("批量设为新品")
    @RequestMapping(value = "/update/newStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateNewStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("newStatus") Integer newStatus) {
        int count = productService.updateNewStatus(ids, newStatus);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("批量修改删除状态")
    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("deleteStatus") Integer deleteStatus) {
        int count = productService.updateDeleteStatus(ids, deleteStatus);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }
}
