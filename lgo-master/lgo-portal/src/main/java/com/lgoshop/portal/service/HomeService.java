package com.lgoshop.portal.service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.model.CmsSubject;
import com.lgoshop.model.PmsProduct;
import com.lgoshop.model.PmsProductCategory;
import com.lgoshop.portal.domain.HomeContentResult;

import java.util.List;

/**
 * 首页内容管理Service
 * Created by lgo-shop.
 */
public interface HomeService {

    /**
     * 获取首页内容
     */
    HomeContentResult content();

    /**
     * 首页商品推荐
     */
    IPage<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum);

    /**
     * 获取商品分类
     * @param parentId 0:获取一级分类；其他：获取指定二级分类
     */
    List<PmsProductCategory> getProductCateList(Long parentId);

    /**
     * 根据专题分类id分页获取专题
     * @param cateId 专题分类id
     */
    IPage<CmsSubject> getSubjectList(Long cateId, Integer pageSize, Integer pageNum);

    /**
     * 分页获取人气推荐商品
     */
    List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize);

    /**
     * 分页获取新品推荐商品
     */
    List<PmsProduct> newProductList(Integer pageNum, Integer pageSize);
}
