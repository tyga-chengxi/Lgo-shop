package com.lgoshop.portal.service;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.model.PmsBrand;
import com.lgoshop.model.PmsProduct;

import java.util.List;

/**
 * 前台品牌管理Service
 * Created by lgo-shop.
 */
public interface PmsPortalBrandService {
    /**
     * 分页获取推荐品牌
     */
    List<PmsBrand> recommendList(Integer pageNum, Integer pageSize);

    /**
     * 获取品牌详情
     */
    PmsBrand detail(Long brandId);

    /**
     * 分页获取品牌关联商品
     */
    CommonPage<PmsProduct> productList(Long brandId, Integer pageNum, Integer pageSize);
}
