package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.SmsHomeAdvertise;
import com.lgoshop.service.SmsHomeAdvertiseService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页轮播广告管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "SmsHomeAdvertiseController")
@Tag(name = "SmsHomeAdvertiseController", description = "首页轮播广告管理")
@RequestMapping("/home/advertise")
@LgoWebLog
public class SmsHomeAdvertiseController {
    @Autowired
    private SmsHomeAdvertiseService advertiseService;

    @ApiOperation("添加广告")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody SmsHomeAdvertise advertise) {
        int count = advertiseService.create(advertise);
        if (count > 0)
            return LgoResult.success(count);
        return LgoResult.failed();
    }

    @ApiOperation("批量删除广告")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@RequestParam("ids") List<Long> ids) {
        int count = advertiseService.delete(ids);
        if (count > 0)
            return LgoResult.success(count);
        return LgoResult.failed();
    }

    @ApiOperation("修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateStatus(@PathVariable Long id, Integer status) {
        int count = advertiseService.updateStatus(id, status);
        if (count > 0)
            return LgoResult.success(count);
        return LgoResult.failed();
    }

    @ApiOperation("根据ID获取广告详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<SmsHomeAdvertise> getItem(@PathVariable Long id) {
        SmsHomeAdvertise advertise = advertiseService.getItem(id);
        return LgoResult.success(advertise);
    }

    @ApiOperation("根据ID修改广告")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long id, @RequestBody SmsHomeAdvertise advertise) {
        int count = advertiseService.update(id, advertise);
        if (count > 0)
            return LgoResult.success(count);
        return LgoResult.failed();
    }

    @ApiOperation("分页查询首页广告")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<SmsHomeAdvertise>> list(@RequestParam(value = "name", required = false) String name,
                                                           @RequestParam(value = "type", required = false) Integer type,
                                                           @RequestParam(value = "endTime", required = false) String endTime,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<SmsHomeAdvertise> advertiseList = advertiseService.list(name, type, endTime, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(advertiseList));
    }
}
