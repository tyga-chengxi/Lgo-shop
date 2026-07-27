package com.lgoshop.portal.controller;

import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.OmsCartItem;
import com.lgoshop.portal.domain.CartProduct;
import com.lgoshop.portal.domain.CartPromotionItem;
import com.lgoshop.portal.service.OmsCartItemService;
import com.lgoshop.portal.service.UmsMemberService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "OmsCartItemController")
@Tag(name = "OmsCartItemController", description = "购物车管理")
@RequestMapping("/cart")
@LgoWebLog
public class OmsCartItemController {
    @Autowired
    private OmsCartItemService cartItemService;
    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("添加商品到购物车")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult add(@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.add(cartItem);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("获取当前会员的购物车列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<OmsCartItem>> list() {
        List<OmsCartItem> cartItemList = cartItemService.list(memberService.getCurrentMember().getId());
        return LgoResult.success(cartItemList);
    }

    @ApiOperation("获取当前会员的购物车列表,包括促销信息")
    @RequestMapping(value = "/list/promotion", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<CartPromotionItem>> listPromotion(@RequestParam(required = false) List<Long> cartIds) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(memberService.getCurrentMember().getId(), cartIds);
        return LgoResult.success(cartPromotionItemList);
    }

    @ApiOperation("修改购物车中指定商品的数量")
    @RequestMapping(value = "/update/quantity", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult updateQuantity(@RequestParam Long id,
                                       @RequestParam Integer quantity) {
        int count = cartItemService.updateQuantity(id, memberService.getCurrentMember().getId(), quantity);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("获取购物车中指定商品的规格,用于重选规格")
    @RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CartProduct> getCartProduct(@PathVariable Long productId) {
        CartProduct cartProduct = cartItemService.getCartProduct(productId);
        return LgoResult.success(cartProduct);
    }

    @ApiOperation("修改购物车中商品的规格")
    @RequestMapping(value = "/update/attr", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateAttr(@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.updateAttr(cartItem);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("删除购物车中的指定商品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@RequestParam("ids") List<Long> ids) {
        int count = cartItemService.delete(memberService.getCurrentMember().getId(), ids);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("清空当前会员的购物车")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult clear() {
        int count = cartItemService.clear(memberService.getCurrentMember().getId());
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }
}
