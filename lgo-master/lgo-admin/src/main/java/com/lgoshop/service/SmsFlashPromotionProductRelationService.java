package com.lgoshop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgoshop.dto.SmsFlashPromotionProduct;
import com.lgoshop.model.SmsFlashPromotionProductRelation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 限时购商品关联管理Service
 * Created by lgo-shop.
 */
public interface SmsFlashPromotionProductRelationService {
    /**
     * 批量添加关联
     */
    @Transactional
    int create(List<SmsFlashPromotionProductRelation> relationList);

    /**
     * 修改关联信息
     */
    int update(Long id, SmsFlashPromotionProductRelation relation);

    /**
     * 删除关联
     */
    int delete(Long id);

    /**
     * 获取关联详情
     */
    SmsFlashPromotionProductRelation getItem(Long id);

    /**
     * 根据限时购和场次id分页查询限时购商品信息
     *
     * @param flashPromotionId        限时购id
     * @param flashPromotionSessionId 限时购场次id
     */
    IPage<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum);

    /**
     * 根据限时购和场次id获取商品关系数量
     * @param flashPromotionId        限时购id
     * @param flashPromotionSessionId 限时购场次id
     */
    long getCount(Long flashPromotionId,Long flashPromotionSessionId);
}
