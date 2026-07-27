package com.lgoshop.portal.service;

import com.lgoshop.portal.domain.MemberBrandAttention;
import org.springframework.data.domain.Page;

/**
 * 会员品牌关注管理Service
 * Created by lgo-shop.
 */
public interface MemberAttentionService {
    /**
     * 添加关注
     */
    int add(MemberBrandAttention memberBrandAttention);

    /**
     * 取消关注
     */
    int delete(Long brandId);

    /**
     * 分页获取会员关注列表
     */
    Page<MemberBrandAttention> list(Integer pageNum, Integer pageSize);

    /**
     * 获取会员关注详情
     */
    MemberBrandAttention detail(Long brandId);

    /**
     * 清空关注列表
     */
    void clear();
}
