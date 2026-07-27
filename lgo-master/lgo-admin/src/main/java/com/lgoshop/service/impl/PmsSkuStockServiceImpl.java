package com.lgoshop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lgoshop.mapper.PmsSkuStockMapper;
import com.lgoshop.model.PmsSkuStock;
import com.lgoshop.service.PmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品SKU库存管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {
    @Autowired
    private PmsSkuStockMapper skuStockMapper;

    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        LambdaQueryWrapper<PmsSkuStock> wrapper = new LambdaQueryWrapper<PmsSkuStock>()
                .eq(PmsSkuStock::getProductId, pid);
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(PmsSkuStock::getSkuCode, keyword);
        }
        return skuStockMapper.selectList(wrapper);
    }

    @Override
    public int update(Long pid, List<PmsSkuStock> skuStockList) {
        List<PmsSkuStock> filterSkuList = skuStockList.stream()
                .filter(item -> pid.equals(item.getProductId()))
                .collect(Collectors.toList());
        return skuStockMapper.replaceList(filterSkuList);
    }
}
