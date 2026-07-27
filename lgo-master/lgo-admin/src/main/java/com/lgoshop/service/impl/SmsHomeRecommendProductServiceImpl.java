package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.SmsHomeRecommendProductMapper;
import com.lgoshop.model.SmsHomeRecommendProduct;
import com.lgoshop.model.SmsHomeRecommendProductExample;
import com.lgoshop.service.SmsHomeRecommendProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页人气推荐管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class SmsHomeRecommendProductServiceImpl implements SmsHomeRecommendProductService {
    @Autowired
    private SmsHomeRecommendProductMapper recommendProductMapper;

    @Override
    public int create(List<SmsHomeRecommendProduct> homeRecommendProductList) {
        for (SmsHomeRecommendProduct recommendProduct : homeRecommendProductList) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
            recommendProductMapper.insert(recommendProduct);
        }
        return homeRecommendProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setId(id);
        recommendProduct.setSort(sort);
        return recommendProductMapper.updateById(recommendProduct);
    }

    @Override
    public int delete(List<Long> ids) {
        return recommendProductMapper.delete(new LambdaQueryWrapper<SmsHomeRecommendProduct>());
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendProduct record = new SmsHomeRecommendProduct();
        record.setRecommendStatus(recommendStatus);
        return recommendProductMapper.update(record, new LambdaUpdateWrapper<>());
    }

    @Override
    public IPage<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeRecommendProduct> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SmsHomeRecommendProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(productName), SmsHomeRecommendProduct::getProductName, productName)
               .eq(recommendStatus != null, SmsHomeRecommendProduct::getRecommendStatus, recommendStatus);
        return recommendProductMapper.selectPage(page, wrapper);
    }
}
