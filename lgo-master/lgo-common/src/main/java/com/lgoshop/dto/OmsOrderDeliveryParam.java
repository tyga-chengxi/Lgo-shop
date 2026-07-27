package com.lgoshop.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单发货参数
 * Created by lgo-shop.
 */
@Getter
@Setter
public class OmsOrderDeliveryParam {
    @ApiModelProperty("订单id")
    private Long orderId;
    @ApiModelProperty("物流公司")
    private String deliveryCompany;
    @ApiModelProperty("物流单号")
    private String deliverySn;
}
