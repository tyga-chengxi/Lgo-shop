package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.lgoshop.dto.SmsFlashPromotionSessionDetail;
import com.lgoshop.mapper.SmsFlashPromotionSessionMapper;
import com.lgoshop.model.SmsFlashPromotionSession;
import com.lgoshop.model.SmsFlashPromotionSessionExample;
import com.lgoshop.service.SmsFlashPromotionProductRelationService;
import com.lgoshop.service.SmsFlashPromotionSessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 限时购场次管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class SmsFlashPromotionSessionServiceImpl implements SmsFlashPromotionSessionService {
    @Autowired
    private SmsFlashPromotionSessionMapper promotionSessionMapper;
    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    @Override
    public int create(SmsFlashPromotionSession promotionSession) {
        promotionSession.setCreateTime(new Date());
        return promotionSessionMapper.insert(promotionSession);
    }

    @Override
    public int update(Long id, SmsFlashPromotionSession promotionSession) {
        promotionSession.setId(id);
        return promotionSessionMapper.updateById(promotionSession);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotionSession promotionSession = new SmsFlashPromotionSession();
        promotionSession.setId(id);
        promotionSession.setStatus(status);
        return promotionSessionMapper.updateById(promotionSession);
    }

    @Override
    public int delete(Long id) {
        return promotionSessionMapper.deleteById(id);
    }

    @Override
    public SmsFlashPromotionSession getItem(Long id) {
        return promotionSessionMapper.selectById(id);
    }

    @Override
    public List<SmsFlashPromotionSession> list() {
        return promotionSessionMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSessionDetail> result = new ArrayList<>();
        List<SmsFlashPromotionSession> list = promotionSessionMapper.selectList(new LambdaQueryWrapper<>());
        for (SmsFlashPromotionSession promotionSession : list) {
            SmsFlashPromotionSessionDetail detail = new SmsFlashPromotionSessionDetail();
            BeanUtils.copyProperties(promotionSession, detail);
            long count = relationService.getCount(flashPromotionId, promotionSession.getId());
            detail.setProductCount(count);
            result.add(detail);
        }
        return result;
    }
}
