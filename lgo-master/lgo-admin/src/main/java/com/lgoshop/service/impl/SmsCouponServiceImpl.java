package com.lgoshop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.SmsCouponMapper;
import com.lgoshop.mapper.SmsCouponProductCategoryRelationMapper;
import com.lgoshop.mapper.SmsCouponProductRelationMapper;
import com.lgoshop.dto.SmsCouponParam;
import com.lgoshop.model.SmsCoupon;
import com.lgoshop.model.SmsCouponHistory;
import com.lgoshop.model.SmsCouponProductCategoryRelation;
import com.lgoshop.model.SmsCouponProductCategoryRelationExample;
import com.lgoshop.model.SmsCouponProductRelation;
import com.lgoshop.model.SmsCouponProductRelationExample;
import com.lgoshop.model.SmsCouponExample;
import com.lgoshop.service.SmsCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class SmsCouponServiceImpl implements SmsCouponService {
    @Autowired
    private SmsCouponMapper couponMapper;
    @Autowired
    private SmsCouponProductRelationMapper productRelationMapper;
    @Autowired
    private SmsCouponProductCategoryRelationMapper productCategoryRelationMapper;

    @Override
    public int create(SmsCouponParam couponParam) {
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);
        int count = couponMapper.insert(couponParam);
        if (couponParam.getUseType().equals(2)) {
            for (SmsCouponProductRelation productRelation : couponParam.getProductRelationList()) {
                productRelation.setCouponId(couponParam.getId());
            }
            productRelationMapper.insertList(couponParam.getProductRelationList());
        }
        if (couponParam.getUseType().equals(1)) {
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            productCategoryRelationMapper.insertList(couponParam.getProductCategoryRelationList());
        }
        return count;
    }

    @Override
    public int delete(Long id) {
        int count = couponMapper.deleteById(id);
        deleteProductRelation(id);
        deleteProductCategoryRelation(id);
        return count;
    }

    private void deleteProductCategoryRelation(Long id) {
        productCategoryRelationMapper.delete(new LambdaQueryWrapper<SmsCouponProductCategoryRelation>());
    }

    private void deleteProductRelation(Long id) {
        productRelationMapper.delete(new LambdaQueryWrapper<SmsCouponProductRelation>());
    }

    @Override
    public int update(Long id, SmsCouponParam couponParam) {
        couponParam.setId(id);
        int count = couponMapper.updateById(couponParam);
        if (couponParam.getUseType().equals(2)) {
            deleteProductRelation(id);
            productRelationMapper.insertList(couponParam.getProductRelationList());
        }
        if (couponParam.getUseType().equals(1)) {
            deleteProductCategoryRelation(id);
            productCategoryRelationMapper.insertList(couponParam.getProductCategoryRelationList());
        }
        return count;
    }

    @Override
    public IPage<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        Page<SmsCoupon> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SmsCoupon> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(name), SmsCoupon::getName, name)
                .eq(type != null, SmsCoupon::getType, type);
        return couponMapper.selectPage(page, queryWrapper);
    }

    @Override
    public SmsCouponParam getItem(Long id) {
        return couponMapper.getItem(id);
    }
}
