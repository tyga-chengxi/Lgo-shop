package com.lgoshop.controller;

import com.lgoshop.common.api.LgoResult;
import com.lgoshop.dto.SmsFlashPromotionSessionDetail;
import com.lgoshop.model.SmsFlashPromotionSession;
import com.lgoshop.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 限时购场次管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "SmsFlashPromotionSessionController")
@Tag(name = "SmsFlashPromotionSessionController", description = "限时购场次管理")
@RequestMapping("/flashSession")
@LgoWebLog
public class SmsFlashPromotionSessionController {
    @Autowired
    private SmsFlashPromotionSessionService flashPromotionSessionService;

    @ApiOperation("添加场次")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody SmsFlashPromotionSession promotionSession) {
        int count = flashPromotionSessionService.create(promotionSession);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("修改场次")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long id, @RequestBody SmsFlashPromotionSession promotionSession) {
        int count = flashPromotionSessionService.update(id, promotionSession);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("根据ID修改场次启用状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateStatus(@PathVariable Long id, Integer status) {
        int count = flashPromotionSessionService.updateStatus(id, status);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("删除场次")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@PathVariable Long id) {
        int count = flashPromotionSessionService.delete(id);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("获取场次详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<SmsFlashPromotionSession> getItem(@PathVariable Long id) {
        SmsFlashPromotionSession promotionSession = flashPromotionSessionService.getItem(id);
        return LgoResult.success(promotionSession);
    }

    @ApiOperation("获取全部场次")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<SmsFlashPromotionSession>> list() {
        List<SmsFlashPromotionSession> promotionSessionList = flashPromotionSessionService.list();
        return LgoResult.success(promotionSessionList);
    }

    @ApiOperation("获取全部可选场次及其数量")
    @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<SmsFlashPromotionSessionDetail>> selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSessionDetail> promotionSessionList = flashPromotionSessionService.selectList(flashPromotionId);
        return LgoResult.success(promotionSessionList);
    }
}