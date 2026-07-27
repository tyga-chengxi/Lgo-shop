package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.lgoshop.mapper.UmsResourceCategoryMapper;
import com.lgoshop.model.UmsResourceCategory;
import com.lgoshop.model.UmsResourceCategoryExample;
import com.lgoshop.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源分类管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
    @Autowired
    private UmsResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<UmsResourceCategory> listAll() {
        return resourceCategoryMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public int create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        return resourceCategoryMapper.insert(umsResourceCategory);
    }

    @Override
    public int update(Long id, UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setId(id);
        return resourceCategoryMapper.updateById(umsResourceCategory);
    }

    @Override
    public int delete(Long id) {
        return resourceCategoryMapper.deleteById(id);
    }
}
