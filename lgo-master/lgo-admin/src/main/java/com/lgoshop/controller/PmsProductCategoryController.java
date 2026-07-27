package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.dto.PmsProductCategoryParam;
import com.lgoshop.dto.PmsProductCategoryWithChildrenItem;
import com.lgoshop.model.PmsProductCategory;
import com.lgoshop.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "PmsProductCategoryController")
@Tag(name = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
@LgoWebLog
public class PmsProductCategoryController {
    @Autowired
    private PmsProductCategoryService productCategoryService;

    @ApiOperation("添加商品分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = productCategoryService.create(productCategoryParam);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("修改商品分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long id,
                         @Validated
                         @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = productCategoryService.update(id, productCategoryParam);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("分页查询商品分类")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<PmsProductCategory>> getList(@PathVariable Long parentId,
                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<PmsProductCategory> productCategoryList = productCategoryService.getList(parentId, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(productCategoryList));
    }

    @ApiOperation("根据ID获取商品分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<PmsProductCategory> getItem(@PathVariable Long id) {
        PmsProductCategory productCategory = productCategoryService.getItem(id);
        return LgoResult.success(productCategory);
    }

    @ApiOperation("根据ID删除商品分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@PathVariable Long id) {
        int count = productCategoryService.delete(id);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("批量修改导航栏显示状态")
    @RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        int count = productCategoryService.updateNavStatus(ids, navStatus);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("批量修改显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        int count = productCategoryService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("查询所有一级分类及子分类")
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return LgoResult.success(list);
    }
}
