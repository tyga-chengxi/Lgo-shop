package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.dto.SmsCouponParam;
import com.lgoshop.model.SmsCoupon;
import com.lgoshop.service.SmsCouponService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoOperateLog;
import com.lgoshop.common.annotation.LgoWebLog;
import com.lgoshop.common.enums.BusinessType;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "SmsCouponController")
@Tag(name = "SmsCouponController", description = "优惠券管理")
@RequestMapping("/coupon")
@LgoWebLog
public class SmsCouponController {
    @Autowired
    private SmsCouponService couponService;
    @ApiOperation("添加优惠券")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @LgoOperateLog(title = "优惠券管理", businessType = BusinessType.INSERT)
    @ResponseBody
    public LgoResult add(@RequestBody SmsCouponParam couponParam) {
        int count = couponService.create(couponParam);
        if(count>0){
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("根据ID删除优惠券")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @LgoOperateLog(title = "优惠券管理", businessType = BusinessType.DELETE)
    @ResponseBody
    public LgoResult delete(@PathVariable Long id) {
        int count = couponService.delete(id);
        if(count>0){
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("根据ID修改优惠券")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @LgoOperateLog(title = "优惠券管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public LgoResult update(@PathVariable Long id,@RequestBody SmsCouponParam couponParam) {
        int count = couponService.update(id,couponParam);
        if(count>0){
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("根据优惠券名称和类型分页获取优惠券列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<SmsCoupon>> list(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "type",required = false) Integer type,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<SmsCoupon> couponList = couponService.list(name,type,pageSize,pageNum);
        return LgoResult.success(CommonPage.restPage(couponList));
    }

    @ApiOperation("获取单个优惠券的详细信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<SmsCouponParam> getItem(@PathVariable Long id) {
        SmsCouponParam couponParam = couponService.getItem(id);
        return LgoResult.success(couponParam);
    }
}
