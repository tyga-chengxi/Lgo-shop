package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.dto.PmsProductAttributeParam;
import com.lgoshop.dto.ProductAttrInfo;
import com.lgoshop.model.PmsProductAttribute;
import com.lgoshop.service.PmsProductAttributeService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "PmsProductAttributeController")
@Tag(name = "PmsProductAttributeController", description = "商品属性管理")
@RequestMapping("/productAttribute")
@LgoWebLog
public class PmsProductAttributeController {
    @Autowired
    private PmsProductAttributeService productAttributeService;

    @ApiOperation("根据分类ID查询属性列表或参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<PmsProductAttribute>> getList(@PathVariable Long cid,
                                                                 @RequestParam(value = "type") Integer type,
                                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<PmsProductAttribute> productAttributeList = productAttributeService.getList(cid, type, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(productAttributeList));
    }

    @ApiOperation("添加商品属性信息")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = productAttributeService.create(productAttributeParam);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("修改商品属性信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long id, @RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = productAttributeService.update(id, productAttributeParam);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("根据ID查询商品属性")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<PmsProductAttribute> getItem(@PathVariable Long id) {
        PmsProductAttribute productAttribute = productAttributeService.getItem(id);
        return LgoResult.success(productAttribute);
    }

    @ApiOperation("批量删除商品属性")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@RequestParam("ids") List<Long> ids) {
        int count = productAttributeService.delete(ids);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("根据商品分类的ID获取商品属性及属性分类ID")
    @RequestMapping(value = "/attrInfo/{productCategoryId}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<ProductAttrInfo>> getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        return LgoResult.success(productAttrInfoList);
    }
}
