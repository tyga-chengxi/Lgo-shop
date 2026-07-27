package com.lgoshop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgoshop.dto.PmsProductCategoryParam;
import com.lgoshop.dto.PmsProductCategoryWithChildrenItem;
import com.lgoshop.model.PmsProductCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分类管理Service
 * Created by lgo-shop.
 */
public interface PmsProductCategoryService {
    /**
     * 创建商品分类
     */
    @Transactional
    int create(PmsProductCategoryParam pmsProductCategoryParam);

    /**
     * 修改商品分类
     */
    @Transactional
    int update(Long id, PmsProductCategoryParam pmsProductCategoryParam);

    /**
     * 分页获取商品分类
     */
    IPage<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 删除商品分类
     */
    int delete(Long id);

    /**
     * 根据ID获取商品分类
     */
    PmsProductCategory getItem(Long id);

    /**
     * 批量修改导航状态
     */
    int updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * 批量修改显示状态
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 以层级形式获取商品分类
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
