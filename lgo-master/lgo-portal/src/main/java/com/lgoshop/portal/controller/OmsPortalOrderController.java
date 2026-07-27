package com.lgoshop.portal.controller;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.portal.domain.ConfirmOrderResult;
import com.lgoshop.portal.domain.OmsOrderDetail;
import com.lgoshop.portal.domain.OrderParam;
import com.lgoshop.portal.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoOperateLog;
import com.lgoshop.common.annotation.LgoWebLog;
import com.lgoshop.common.enums.BusinessType;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 订单管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "OmsPortalOrderController")
@Tag(name = "OmsPortalOrderController", description = "订单管理")
@RequestMapping("/order")
@LgoWebLog
public class OmsPortalOrderController {
    @Autowired
    private OmsPortalOrderService portalOrderService;

    @ApiOperation("根据购物车信息生成确认单")
    @RequestMapping(value = "/generateConfirmOrder", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult<ConfirmOrderResult> generateConfirmOrder(@RequestBody List<Long> cartIds) {
        ConfirmOrderResult confirmOrderResult = portalOrderService.generateConfirmOrder(cartIds);
        return LgoResult.success(confirmOrderResult);
    }

    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    @LgoOperateLog(title = "订单管理", businessType = BusinessType.INSERT)
    @ResponseBody
    public LgoResult generateOrder(@RequestBody OrderParam orderParam) {
        Map<String, Object> result = portalOrderService.generateOrder(orderParam);
        return LgoResult.success("下单成功", result);
    }

    @ApiOperation("用户支付成功的回调")
    @RequestMapping(value = "/paySuccess", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult paySuccess(@RequestParam Long orderId,@RequestParam Integer payType) {
        Integer count = portalOrderService.paySuccess(orderId,payType);
        return LgoResult.success("支付成功", count);
    }

    @ApiOperation("自动取消超时订单")
    @RequestMapping(value = "/cancelTimeOutOrder", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult cancelTimeOutOrder() {
        portalOrderService.cancelTimeOutOrder();
        return LgoResult.success(null);
    }

    @ApiOperation("取消单个超时订单")
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult cancelOrder(Long orderId) {
        portalOrderService.sendDelayMessageCancelOrder(orderId);
        return LgoResult.success(null);
    }

    @ApiOperation("按状态分页获取用户订单列表")
    @ApiImplicitParam(name = "status", value = "订单状态：-1->全部；0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭",
            defaultValue = "-1", allowableValues = "-1,0,1,2,3,4", paramType = "query", dataType = "int")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<OmsOrderDetail>> list(@RequestParam Integer status,
                                                   @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        CommonPage<OmsOrderDetail> orderPage = portalOrderService.list(status,pageNum,pageSize);
        return LgoResult.success(orderPage);
    }

    @ApiOperation("根据ID获取订单详情")
    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<OmsOrderDetail> detail(@PathVariable Long orderId) {
        OmsOrderDetail orderDetail = portalOrderService.detail(orderId);
        return LgoResult.success(orderDetail);
    }

    @ApiOperation("用户取消订单")
    @RequestMapping(value = "/cancelUserOrder", method = RequestMethod.POST)
    @LgoOperateLog(title = "订单管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public LgoResult cancelUserOrder(Long orderId) {
        portalOrderService.cancelOrder(orderId);
        return LgoResult.success(null);
    }

    @ApiOperation("用户确认收货")
    @RequestMapping(value = "/confirmReceiveOrder", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult confirmReceiveOrder(Long orderId) {
        portalOrderService.confirmReceiveOrder(orderId);
        return LgoResult.success(null);
    }

    @ApiOperation("用户删除订单")
    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult deleteOrder(Long orderId) {
        portalOrderService.deleteOrder(orderId);
        return LgoResult.success(null);
    }
}
