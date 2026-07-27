package com.lgoshop.portal.controller;

import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.SmsCoupon;
import com.lgoshop.model.SmsCouponHistory;
import com.lgoshop.portal.domain.CartPromotionItem;
import com.lgoshop.portal.domain.SmsCouponHistoryDetail;
import com.lgoshop.portal.service.OmsCartItemService;
import com.lgoshop.portal.service.UmsMemberCouponService;
import com.lgoshop.portal.service.UmsMemberService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员优惠券管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "UmsMemberCouponController")
@Tag(name = "UmsMemberCouponController", description = "用户优惠券管理")
@RequestMapping("/member/coupon")
@LgoWebLog
public class UmsMemberCouponController {
    @Autowired
    private UmsMemberCouponService memberCouponService;
    @Autowired
    private OmsCartItemService cartItemService;
    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("领取指定优惠券")
    @RequestMapping(value = "/add/{couponId}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult add(@PathVariable Long couponId) {
        memberCouponService.add(couponId);
        return LgoResult.success("领取成功", null);
    }

    @ApiOperation("获取会员优惠券历史列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/listHistory", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<SmsCouponHistory>> listHistory(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCouponHistory> couponHistoryList = memberCouponService.listHistory(useStatus);
        return LgoResult.success(couponHistoryList);
    }

    @ApiOperation("获取会员优惠券列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<SmsCoupon>> list(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCoupon> couponList = memberCouponService.list(useStatus);
        return LgoResult.success(couponList);
    }

    @ApiOperation("获取登录会员购物车的相关优惠券")
    @ApiImplicitParam(name = "type", value = "使用可用:0->不可用；1->可用",
            defaultValue = "1", allowableValues = "0,1", paramType = "path", dataType = "integer")
    @RequestMapping(value = "/list/cart/{type}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<SmsCouponHistoryDetail>> listCart(@PathVariable Integer type) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(memberService.getCurrentMember().getId(), null);
        List<SmsCouponHistoryDetail> couponHistoryList = memberCouponService.listCart(cartPromotionItemList, type);
        return LgoResult.success(couponHistoryList);
    }

    @ApiOperation("获取当前商品相关优惠券")
    @RequestMapping(value = "/listByProduct/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<SmsCoupon>> listByProduct(@PathVariable Long productId) {
        List<SmsCoupon> couponHistoryList = memberCouponService.listByProduct(productId);
        return LgoResult.success(couponHistoryList);
    }
}
