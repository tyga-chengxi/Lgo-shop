package com.lgoshop.portal.service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.model.PmsProduct;
import com.lgoshop.portal.domain.PmsPortalProductDetail;
import com.lgoshop.portal.domain.PmsProductCategoryNode;

import java.util.List;

/**
 * 前台商品管理Service
 * Created by lgo-shop.
 */
public interface PmsPortalProductService {
    /**
     * 综合搜索商品
     */
    IPage<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);

    /**
     * 以树形结构获取所有商品分类
     */
    List<PmsProductCategoryNode> categoryTreeList();

    /**
     * 获取前台商品详情
     */
    PmsPortalProductDetail detail(Long id);
}
