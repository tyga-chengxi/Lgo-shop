package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.PmsProductCategoryAttributeRelationMapper;
import com.lgoshop.mapper.PmsProductCategoryMapper;
import com.lgoshop.mapper.PmsProductMapper;
import com.lgoshop.dto.PmsProductCategoryParam;
import com.lgoshop.dto.PmsProductCategoryWithChildrenItem;
import com.lgoshop.model.PmsProduct;
import com.lgoshop.model.PmsProductCategory;
import com.lgoshop.model.PmsProductCategoryAttributeRelation;
import com.lgoshop.model.PmsProductCategoryAttributeRelationExample;
import com.lgoshop.model.PmsProductCategoryExample;
import com.lgoshop.model.PmsProductExample;
import com.lgoshop.service.PmsProductCategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;

    @Override
    public int create(PmsProductCategoryParam pmsProductCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        setCategoryLevel(productCategory);
        int count = productCategoryMapper.insert(productCategory);
        List<Long> productAttributeIdList = pmsProductCategoryParam.getProductAttributeIdList();
        if (!CollectionUtils.isEmpty(productAttributeIdList)) {
            insertRelationList(productCategory.getId(), productAttributeIdList);
        }
        return count;
    }

    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationMapper.insertList(relationList);
    }

    @Override
    public int update(Long id, PmsProductCategoryParam pmsProductCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        productCategory.setId(id);
        setCategoryLevel(productCategory);
        PmsProduct product = new PmsProduct();
        product.setProductCategoryName(productCategory.getName());
        productMapper.update(product, new LambdaUpdateWrapper<>());
        if (!CollectionUtils.isEmpty(pmsProductCategoryParam.getProductAttributeIdList())) {
            productCategoryAttributeRelationMapper.delete(new LambdaQueryWrapper<PmsProductCategoryAttributeRelation>());
            insertRelationList(id, pmsProductCategoryParam.getProductAttributeIdList());
        }
        return productCategoryMapper.updateById(productCategory);
    }

    @Override
    public IPage<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum) {
        Page<PmsProductCategory> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<PmsProductCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PmsProductCategory::getParentId, parentId);
        return productCategoryMapper.selectPage(page, queryWrapper);
    }

    @Override
    public int delete(Long id) {
        return productCategoryMapper.deleteById(id);
    }

    @Override
    public PmsProductCategory getItem(Long id) {
        return productCategoryMapper.selectById(id);
    }

    @Override
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setNavStatus(navStatus);
        return productCategoryMapper.update(productCategory, new LambdaUpdateWrapper<>());
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setShowStatus(showStatus);
        return productCategoryMapper.update(productCategory, new LambdaUpdateWrapper<>());
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return productCategoryMapper.listWithChildren();
    }

    private void setCategoryLevel(PmsProductCategory productCategory) {
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            PmsProductCategory parentCategory = productCategoryMapper.selectById(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }
}
