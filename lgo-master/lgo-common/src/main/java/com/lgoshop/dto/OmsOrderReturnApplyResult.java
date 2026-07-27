package com.lgoshop.dto;

import com.lgoshop.model.OmsCompanyAddress;
import com.lgoshop.model.OmsOrderReturnApply;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单退货申请结果封装
 * Created by lgo-shop.
 */
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {
    @Getter
    @Setter
    @ApiModelProperty(value = "公司收货地址")
    private OmsCompanyAddress companyAddress;
}
