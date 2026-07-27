package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.SmsFlashPromotion;
import com.lgoshop.service.SmsFlashPromotionService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 限时购活动管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "SmsFlashPromotionController")
@Tag(name = "SmsFlashPromotionController", description = "限时购活动管理")
@RequestMapping("/flash")
@LgoWebLog
public class SmsFlashPromotionController {
    @Autowired
    private SmsFlashPromotionService flashPromotionService;

    @ApiOperation("添加活动")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody SmsFlashPromotion flashPromotion) {
        int count = flashPromotionService.create(flashPromotion);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("编辑活动")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long id, @RequestBody SmsFlashPromotion flashPromotion) {
        int count = flashPromotionService.update(id, flashPromotion);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("删除活动")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@PathVariable Long id) {
        int count = flashPromotionService.delete(id);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("修改活动上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long id, Integer status) {
        int count = flashPromotionService.updateStatus(id, status);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("获取活动详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<SmsFlashPromotion> getItem(@PathVariable Long id) {
        SmsFlashPromotion flashPromotion = flashPromotionService.getItem(id);
        return LgoResult.success(flashPromotion);
    }

    @ApiOperation("根据活动名称分页查询活动")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<SmsFlashPromotion>> getItem(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<SmsFlashPromotion> flashPromotionList = flashPromotionService.list(keyword, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(flashPromotionList));
    }
}
