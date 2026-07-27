package com.lgoshop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.dto.PmsProductAttributeCategoryItem;
import com.lgoshop.mapper.PmsProductAttributeCategoryMapper;
import com.lgoshop.model.PmsProductAttributeCategory;
import com.lgoshop.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性分类管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {
    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public int create(String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        return productAttributeCategoryMapper.insert(productAttributeCategory);
    }

    @Override
    public int update(Long id, String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setId(id);
        return productAttributeCategoryMapper.updateById(productAttributeCategory);
    }

    @Override
    public int delete(Long id) {
        return productAttributeCategoryMapper.deleteById(id);
    }

    @Override
    public PmsProductAttributeCategory getItem(Long id) {
        return productAttributeCategoryMapper.selectById(id);
    }

    @Override
    public IPage<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        Page<PmsProductAttributeCategory> page = new Page<>(pageNum, pageSize);
        return productAttributeCategoryMapper.selectPage(page, null);
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return productAttributeCategoryMapper.getListWithAttr();
    }
}
