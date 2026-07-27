package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.SmsHomeNewProductMapper;
import com.lgoshop.model.SmsHomeNewProduct;
import com.lgoshop.model.SmsHomeNewProductExample;
import com.lgoshop.service.SmsHomeNewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页新品推荐管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {
    @Autowired
    private SmsHomeNewProductMapper homeNewProductMapper;

    @Override
    public int create(List<SmsHomeNewProduct> homeNewProductList) {
        for (SmsHomeNewProduct smsHomeNewProduct : homeNewProductList) {
            smsHomeNewProduct.setRecommendStatus(1);
            smsHomeNewProduct.setSort(0);
            homeNewProductMapper.insert(smsHomeNewProduct);
        }
        return homeNewProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        return homeNewProductMapper.updateById(homeNewProduct);
    }

    @Override
    public int delete(List<Long> ids) {
        return homeNewProductMapper.delete(new LambdaQueryWrapper<SmsHomeNewProduct>());
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeNewProduct record = new SmsHomeNewProduct();
        record.setRecommendStatus(recommendStatus);
        return homeNewProductMapper.update(record, new LambdaUpdateWrapper<>());
    }

    @Override
    public IPage<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeNewProduct> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SmsHomeNewProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(productName), SmsHomeNewProduct::getProductName, productName)
                .eq(recommendStatus != null, SmsHomeNewProduct::getRecommendStatus, recommendStatus);
        return homeNewProductMapper.selectPage(page, queryWrapper);
    }
}
