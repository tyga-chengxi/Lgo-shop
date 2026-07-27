package com.lgoshop.portal.service;

import com.lgoshop.portal.domain.OmsOrderReturnApplyParam;

/**
 * 前台订单退货管理Service
 * Created by lgo-shop.
 */
public interface OmsPortalOrderReturnApplyService {
    /**
     * 提交申请
     */
    int create(OmsOrderReturnApplyParam returnApply);
}
