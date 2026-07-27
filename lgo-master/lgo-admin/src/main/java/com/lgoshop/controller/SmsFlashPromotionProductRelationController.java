package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.dto.SmsFlashPromotionProduct;
import com.lgoshop.model.SmsFlashPromotionProductRelation;
import com.lgoshop.service.SmsFlashPromotionProductRelationService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 限时购和商品关系管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "SmsFlashPromotionProductRelationController")
@Tag(name = "SmsFlashPromotionProductRelationController", description = "限时购和商品关系管理")
@RequestMapping("/flashProductRelation")
@LgoWebLog
public class SmsFlashPromotionProductRelationController {
    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    @ApiOperation("批量选择商品添加关联")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody List<SmsFlashPromotionProductRelation> relationList) {
        int count = relationService.create(relationList);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("根据ID修改关联信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long id, @RequestBody SmsFlashPromotionProductRelation relation) {
        int count = relationService.update(id, relation);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("根据ID删除关联信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@PathVariable Long id) {
        int count = relationService.delete(id);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("获取关联商品促销信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<SmsFlashPromotionProductRelation> getItem(@PathVariable Long id) {
        SmsFlashPromotionProductRelation relation = relationService.getItem(id);
        return LgoResult.success(relation);
    }

    @ApiOperation("分页查询不同场次关联及商品信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<SmsFlashPromotionProduct>> list(@RequestParam(value = "flashPromotionId") Long flashPromotionId,
                                                                   @RequestParam(value = "flashPromotionSessionId") Long flashPromotionSessionId,
                                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<SmsFlashPromotionProduct> flashPromotionProductList = relationService.list(flashPromotionId, flashPromotionSessionId, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(flashPromotionProductList));
    }
}
