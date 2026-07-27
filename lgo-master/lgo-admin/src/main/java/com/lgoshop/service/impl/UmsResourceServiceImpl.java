package com.lgoshop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.UmsResourceMapper;
import com.lgoshop.model.UmsResource;
import com.lgoshop.model.UmsResourceExample;
import com.lgoshop.service.UmsAdminCacheService;
import com.lgoshop.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(new Date());
        return resourceMapper.insert(umsResource);
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        int count = resourceMapper.updateById(umsResource);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public UmsResource getItem(Long id) {
        return resourceMapper.selectById(id);
    }

    @Override
    public int delete(Long id) {
        int count = resourceMapper.deleteById(id);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public IPage<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        Page<UmsResource> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UmsResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(categoryId != null, UmsResource::getCategoryId, categoryId)
                .like(StrUtil.isNotEmpty(nameKeyword), UmsResource::getName, nameKeyword)
                .like(StrUtil.isNotEmpty(urlKeyword), UmsResource::getUrl, urlKeyword);
        return resourceMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectList(new LambdaQueryWrapper<>());
    }
}
