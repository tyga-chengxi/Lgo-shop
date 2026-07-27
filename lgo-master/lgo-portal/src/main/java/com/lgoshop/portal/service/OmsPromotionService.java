package com.lgoshop.portal.service;

import com.lgoshop.model.OmsCartItem;
import com.lgoshop.portal.domain.CartPromotionItem;

import java.util.List;

/**
 * 促销管理Service
 * Created by lgo-shop.
 */
public interface OmsPromotionService {
    /**
     * 计算购物车中的促销活动信息
     * @param cartItemList 购物车
     */
    List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList);
}
