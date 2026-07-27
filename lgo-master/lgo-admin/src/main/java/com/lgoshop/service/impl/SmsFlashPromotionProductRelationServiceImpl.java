package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.SmsFlashPromotionProductRelationMapper;
import com.lgoshop.dto.SmsFlashPromotionProduct;
import com.lgoshop.model.SmsFlashPromotionProductRelation;
import com.lgoshop.model.SmsFlashPromotionProductRelationExample;
import com.lgoshop.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 限时购商品关联管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {
    @Autowired
    private SmsFlashPromotionProductRelationMapper relationMapper;

    @Override
    public int create(List<SmsFlashPromotionProductRelation> relationList) {
        for (SmsFlashPromotionProductRelation relation : relationList) {
            relationMapper.insert(relation);
        }
        return relationList.size();
    }

    @Override
    public int update(Long id, SmsFlashPromotionProductRelation relation) {
        relation.setId(id);
        return relationMapper.updateById(relation);
    }

    @Override
    public int delete(Long id) {
        return relationMapper.deleteById(id);
    }

    @Override
    public SmsFlashPromotionProductRelation getItem(Long id) {
        return relationMapper.selectById(id);
    }

    @Override
    public IPage<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        Page<SmsFlashPromotionProduct> page = new Page<>(pageNum, pageSize);
        return relationMapper.selectFlashPage(page, flashPromotionId, flashPromotionSessionId);
    }

    @Override
    public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        return relationMapper.selectCount(new LambdaQueryWrapper<>());
    }
}
