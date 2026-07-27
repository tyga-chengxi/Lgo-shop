package com.lgoshop.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.*;
import com.lgoshop.model.*;
import com.lgoshop.portal.dao.HomeDao;
import com.lgoshop.portal.domain.FlashPromotionProduct;
import com.lgoshop.portal.domain.HomeContentResult;
import com.lgoshop.portal.domain.HomeFlashPromotion;
import com.lgoshop.portal.service.HomeService;
import com.lgoshop.portal.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 首页内容管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;
    @Autowired
    private HomeDao homeDao;
    @Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;
    @Autowired
    private SmsFlashPromotionSessionMapper promotionSessionMapper;
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;
    @Autowired
    private CmsSubjectMapper subjectMapper;

    @Override
    public HomeContentResult content() {
        HomeContentResult result = new HomeContentResult();
        result.setAdvertiseList(getHomeAdvertiseList());
        result.setBrandList(homeDao.getRecommendBrandList(0, 6));
        result.setHomeFlashPromotion(getHomeFlashPromotion());
        result.setNewProductList(homeDao.getNewProductList(0, 4));
        result.setHotProductList(homeDao.getHotProductList(0, 4));
        result.setSubjectList(homeDao.getRecommendSubjectList(0, 4));
        return result;
    }

    @Override
    public IPage<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum) {
        Page<PmsProduct> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<PmsProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PmsProduct::getDeleteStatus, 0)
               .eq(PmsProduct::getPublishStatus, 1);
        return productMapper.selectPage(page, wrapper);
    }

    @Override
    public List<PmsProductCategory> getProductCateList(Long parentId) {
        LambdaQueryWrapper<PmsProductCategory> wrapper = new LambdaQueryWrapper<PmsProductCategory>()
                .eq(PmsProductCategory::getShowStatus, 1)
                .eq(PmsProductCategory::getParentId, parentId)
                .orderByDesc(PmsProductCategory::getSort);
        return productCategoryMapper.selectList(wrapper);
    }

    @Override
    public IPage<CmsSubject> getSubjectList(Long cateId, Integer pageSize, Integer pageNum) {
        Page<CmsSubject> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<CmsSubject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CmsSubject::getShowStatus, 1)
               .eq(cateId != null, CmsSubject::getCategoryId, cateId);
        return subjectMapper.selectPage(page, wrapper);
    }

    @Override
    public List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return homeDao.getHotProductList(offset, pageSize);
    }

    @Override
    public List<PmsProduct> newProductList(Integer pageNum, Integer pageSize) {
        int offset = pageSize * (pageNum - 1);
        return homeDao.getNewProductList(offset, pageSize);
    }

    private HomeFlashPromotion getHomeFlashPromotion() {
        HomeFlashPromotion homeFlashPromotion = new HomeFlashPromotion();
        Date now = new Date();
        SmsFlashPromotion flashPromotion = getFlashPromotion(now);
        if (flashPromotion != null) {
            SmsFlashPromotionSession flashPromotionSession = getFlashPromotionSession(now);
            if (flashPromotionSession != null) {
                homeFlashPromotion.setStartTime(flashPromotionSession.getStartTime());
                homeFlashPromotion.setEndTime(flashPromotionSession.getEndTime());
                SmsFlashPromotionSession nextSession = getNextFlashPromotionSession(homeFlashPromotion.getStartTime());
                if (nextSession != null) {
                    homeFlashPromotion.setNextStartTime(nextSession.getStartTime());
                    homeFlashPromotion.setNextEndTime(nextSession.getEndTime());
                }
                List<FlashPromotionProduct> flashProductList = homeDao.getFlashProductList(flashPromotion.getId(), flashPromotionSession.getId());
                homeFlashPromotion.setProductList(flashProductList);
            }
        }
        return homeFlashPromotion;
    }

    private SmsFlashPromotionSession getNextFlashPromotionSession(Date date) {
        LambdaQueryWrapper<SmsFlashPromotionSession> wrapper = new LambdaQueryWrapper<SmsFlashPromotionSession>()
                .gt(SmsFlashPromotionSession::getStartTime, date)
                .orderByAsc(SmsFlashPromotionSession::getStartTime);
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }

    private List<SmsHomeAdvertise> getHomeAdvertiseList() {
        LambdaQueryWrapper<SmsHomeAdvertise> wrapper = new LambdaQueryWrapper<SmsHomeAdvertise>()
                .eq(SmsHomeAdvertise::getType, 1)
                .eq(SmsHomeAdvertise::getStatus, 1)
                .orderByDesc(SmsHomeAdvertise::getSort);
        return advertiseMapper.selectList(wrapper);
    }

    private SmsFlashPromotion getFlashPromotion(Date date) {
        Date currDate = DateUtil.getDate(date);
        LambdaQueryWrapper<SmsFlashPromotion> wrapper = new LambdaQueryWrapper<SmsFlashPromotion>()
                .eq(SmsFlashPromotion::getStatus, 1)
                .le(SmsFlashPromotion::getStartDate, currDate)
                .ge(SmsFlashPromotion::getEndDate, currDate);
        List<SmsFlashPromotion> flashPromotionList = flashPromotionMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(flashPromotionList)) {
            return flashPromotionList.get(0);
        }
        return null;
    }

    private SmsFlashPromotionSession getFlashPromotionSession(Date date) {
        Date currTime = DateUtil.getTime(date);
        LambdaQueryWrapper<SmsFlashPromotionSession> wrapper = new LambdaQueryWrapper<SmsFlashPromotionSession>()
                .le(SmsFlashPromotionSession::getStartTime, currTime)
                .ge(SmsFlashPromotionSession::getEndTime, currTime);
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(promotionSessionList)) {
            return promotionSessionList.get(0);
        }
        return null;
    }
}
