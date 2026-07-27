package com.lgoshop.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.common.api.CommonPage;
import com.lgoshop.mapper.PmsBrandMapper;
import com.lgoshop.mapper.PmsProductMapper;
import com.lgoshop.model.PmsBrand;
import com.lgoshop.model.PmsProduct;
import com.lgoshop.portal.dao.HomeDao;
import com.lgoshop.portal.service.PmsPortalBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前台品牌管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class PmsPortalBrandServiceImpl implements PmsPortalBrandService {
    @Autowired
    private HomeDao homeDao;
    @Autowired
    private PmsBrandMapper brandMapper;
    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public List<PmsBrand> recommendList(Integer pageNum, Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return homeDao.getRecommendBrandList(offset, pageSize);
    }

    @Override
    public PmsBrand detail(Long brandId) {
        return brandMapper.selectById(brandId);
    }

    @Override
    public CommonPage<PmsProduct> productList(Long brandId, Integer pageNum, Integer pageSize) {
        Page<PmsProduct> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<PmsProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PmsProduct::getDeleteStatus, 0)
               .eq(PmsProduct::getPublishStatus, 1)
               .eq(PmsProduct::getBrandId, brandId);
        return CommonPage.restPage(productMapper.selectPage(page, wrapper));
    }
}
