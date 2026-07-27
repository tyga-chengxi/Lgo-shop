package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.dto.PmsBrandParam;
import com.lgoshop.mapper.PmsBrandMapper;
import com.lgoshop.mapper.PmsProductMapper;
import com.lgoshop.model.PmsBrand;
import com.lgoshop.model.PmsBrandExample;
import com.lgoshop.model.PmsProduct;
import com.lgoshop.model.PmsProductExample;
import com.lgoshop.service.PmsBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品品牌管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper brandMapper;
    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public int createBrand(PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        return brandMapper.insert(pmsBrand);
    }

    @Override
    public int updateBrand(Long id, PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        PmsProduct product = new PmsProduct();
        product.setBrandName(pmsBrand.getName());
        productMapper.update(product, new LambdaUpdateWrapper<>());
        return brandMapper.updateById(pmsBrand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteById(id);
    }

    @Override
    public int deleteBrand(List<Long> ids) {
        return brandMapper.delete(new LambdaQueryWrapper<PmsBrand>());
    }

    @Override
    public IPage<PmsBrand> listBrand(String keyword, Integer showStatus, int pageNum, int pageSize) {
        Page<PmsBrand> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<PmsBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(keyword), PmsBrand::getName, keyword)
                .eq(showStatus != null, PmsBrand::getShowStatus, showStatus);
        return brandMapper.selectPage(page, queryWrapper);
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectById(id);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setShowStatus(showStatus);
        return brandMapper.update(pmsBrand, new LambdaUpdateWrapper<>());
    }

    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setFactoryStatus(factoryStatus);
        return brandMapper.update(pmsBrand, new LambdaUpdateWrapper<>());
    }
}
