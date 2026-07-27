package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.SmsHomeBrandMapper;
import com.lgoshop.model.SmsHomeBrand;
import com.lgoshop.model.SmsHomeBrandExample;
import com.lgoshop.service.SmsHomeBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页品牌管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {
    @Autowired
    private SmsHomeBrandMapper homeBrandMapper;

    @Override
    public int create(List<SmsHomeBrand> homeBrandList) {
        for (SmsHomeBrand smsHomeBrand : homeBrandList) {
            smsHomeBrand.setRecommendStatus(1);
            smsHomeBrand.setSort(0);
            homeBrandMapper.insert(smsHomeBrand);
        }
        return homeBrandList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setId(id);
        homeBrand.setSort(sort);
        return homeBrandMapper.updateById(homeBrand);
    }

    @Override
    public int delete(List<Long> ids) {
        return homeBrandMapper.delete(new LambdaQueryWrapper<SmsHomeBrand>());
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeBrand record = new SmsHomeBrand();
        record.setRecommendStatus(recommendStatus);
        return homeBrandMapper.update(record, new LambdaUpdateWrapper<>());
    }

    @Override
    public IPage<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeBrand> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SmsHomeBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(brandName), SmsHomeBrand::getBrandName, brandName)
                .eq(recommendStatus != null, SmsHomeBrand::getRecommendStatus, recommendStatus);
        return homeBrandMapper.selectPage(page, queryWrapper);
    }
}
