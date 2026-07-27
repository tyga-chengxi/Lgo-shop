package com.lgoshop.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lgoshop.common.exception.Asserts;
import com.lgoshop.mapper.*;
import com.lgoshop.model.*;
import com.lgoshop.portal.dao.SmsCouponHistoryDao;
import com.lgoshop.portal.domain.CartPromotionItem;
import com.lgoshop.portal.domain.SmsCouponHistoryDetail;
import com.lgoshop.portal.service.UmsMemberCouponService;
import com.lgoshop.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 会员优惠券管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class UmsMemberCouponServiceImpl implements UmsMemberCouponService {
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private SmsCouponMapper couponMapper;
    @Autowired
    private SmsCouponHistoryMapper couponHistoryMapper;
    @Autowired
    private SmsCouponHistoryDao couponHistoryDao;
    @Autowired
    private SmsCouponProductRelationMapper couponProductRelationMapper;
    @Autowired
    private SmsCouponProductCategoryRelationMapper couponProductCategoryRelationMapper;
    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public void add(Long couponId) {
        UmsMember currentMember = memberService.getCurrentMember();
        SmsCoupon coupon = couponMapper.selectById(couponId);
        if (coupon == null) {
            Asserts.fail("优惠券不存在");
        }
        if (coupon.getCount() <= 0) {
            Asserts.fail("优惠券已经领完了");
        }
        Date now = new Date();
        if (now.before(coupon.getEnableTime())) {
            Asserts.fail("优惠券还没到领取时间");
        }
        long count = couponHistoryMapper.selectCount(new LambdaQueryWrapper<SmsCouponHistory>()
                .eq(SmsCouponHistory::getCouponId, couponId)
                .eq(SmsCouponHistory::getMemberId, currentMember.getId()));
        if (count >= coupon.getPerLimit()) {
            Asserts.fail("您已经领取过该优惠券");
        }
        SmsCouponHistory couponHistory = new SmsCouponHistory();
        couponHistory.setCouponId(couponId);
        couponHistory.setCouponCode(generateCouponCode(currentMember.getId()));
        couponHistory.setCreateTime(now);
        couponHistory.setMemberId(currentMember.getId());
        couponHistory.setMemberNickname(currentMember.getNickname());
        couponHistory.setGetType(1);
        couponHistory.setUseStatus(0);
        couponHistoryMapper.insert(couponHistory);
        coupon.setCount(coupon.getCount() - 1);
        coupon.setReceiveCount(coupon.getReceiveCount() == null ? 1 : coupon.getReceiveCount() + 1);
        couponMapper.updateById(coupon);
    }

    private String generateCouponCode(Long memberId) {
        StringBuilder sb = new StringBuilder();
        Long currentTimeMillis = System.currentTimeMillis();
        String timeMillisStr = currentTimeMillis.toString();
        sb.append(timeMillisStr.substring(timeMillisStr.length() - 8));
        for (int i = 0; i < 4; i++) {
            sb.append(new Random().nextInt(10));
        }
        String memberIdStr = memberId.toString();
        if (memberIdStr.length() <= 4) {
            sb.append(String.format("%04d", memberId));
        } else {
            sb.append(memberIdStr.substring(memberIdStr.length() - 4));
        }
        return sb.toString();
    }

    @Override
    public List<SmsCouponHistory> listHistory(Integer useStatus) {
        UmsMember currentMember = memberService.getCurrentMember();
        LambdaQueryWrapper<SmsCouponHistory> wrapper = new LambdaQueryWrapper<SmsCouponHistory>()
                .eq(SmsCouponHistory::getMemberId, currentMember.getId());
        if (useStatus != null) {
            wrapper.eq(SmsCouponHistory::getUseStatus, useStatus);
        }
        return couponHistoryMapper.selectList(wrapper);
    }

    @Override
    public List<SmsCouponHistoryDetail> listCart(List<CartPromotionItem> cartItemList, Integer type) {
        UmsMember currentMember = memberService.getCurrentMember();
        Date now = new Date();
        List<SmsCouponHistoryDetail> allList = couponHistoryDao.getDetailList(currentMember.getId());
        List<SmsCouponHistoryDetail> enableList = new ArrayList<>();
        List<SmsCouponHistoryDetail> disableList = new ArrayList<>();
        for (SmsCouponHistoryDetail couponHistoryDetail : allList) {
            Integer useType = couponHistoryDetail.getCoupon().getUseType();
            BigDecimal minPoint = couponHistoryDetail.getCoupon().getMinPoint();
            Date endTime = couponHistoryDetail.getCoupon().getEndTime();
            if (useType.equals(0)) {
                BigDecimal totalAmount = calcTotalAmount(cartItemList);
                if (now.before(endTime) && totalAmount.subtract(minPoint).floatValue() >= 0) {
                    enableList.add(couponHistoryDetail);
                } else {
                    disableList.add(couponHistoryDetail);
                }
            } else if (useType.equals(1)) {
                List<Long> productCategoryIds = new ArrayList<>();
                for (SmsCouponProductCategoryRelation categoryRelation : couponHistoryDetail.getCategoryRelationList()) {
                    productCategoryIds.add(categoryRelation.getProductCategoryId());
                }
                BigDecimal totalAmount = calcTotalAmountByproductCategoryId(cartItemList, productCategoryIds);
                if (now.before(endTime) && totalAmount.floatValue() > 0 && totalAmount.subtract(minPoint).floatValue() >= 0) {
                    enableList.add(couponHistoryDetail);
                } else {
                    disableList.add(couponHistoryDetail);
                }
            } else if (useType.equals(2)) {
                List<Long> productIds = new ArrayList<>();
                for (SmsCouponProductRelation productRelation : couponHistoryDetail.getProductRelationList()) {
                    productIds.add(productRelation.getProductId());
                }
                BigDecimal totalAmount = calcTotalAmountByProductId(cartItemList, productIds);
                if (now.before(endTime) && totalAmount.floatValue() > 0 && totalAmount.subtract(minPoint).floatValue() >= 0) {
                    enableList.add(couponHistoryDetail);
                } else {
                    disableList.add(couponHistoryDetail);
                }
            }
        }
        if (type.equals(1)) {
            return enableList;
        } else {
            return disableList;
        }
    }

    @Override
    public List<SmsCoupon> listByProduct(Long productId) {
        List<Long> allCouponIds = new ArrayList<>();
        LambdaQueryWrapper<SmsCouponProductRelation> cprWrapper = new LambdaQueryWrapper<SmsCouponProductRelation>()
                .eq(SmsCouponProductRelation::getProductId, productId);
        List<SmsCouponProductRelation> cprList = couponProductRelationMapper.selectList(cprWrapper);
        if (CollUtil.isNotEmpty(cprList)) {
            List<Long> couponIds = cprList.stream().map(SmsCouponProductRelation::getCouponId).collect(Collectors.toList());
            allCouponIds.addAll(couponIds);
        }
        PmsProduct product = productMapper.selectById(productId);
        LambdaQueryWrapper<SmsCouponProductCategoryRelation> cpcrWrapper = new LambdaQueryWrapper<SmsCouponProductCategoryRelation>()
                .eq(SmsCouponProductCategoryRelation::getProductCategoryId, product.getProductCategoryId());
        List<SmsCouponProductCategoryRelation> cpcrList = couponProductCategoryRelationMapper.selectList(cpcrWrapper);
        if (CollUtil.isNotEmpty(cpcrList)) {
            List<Long> couponIds = cpcrList.stream().map(SmsCouponProductCategoryRelation::getCouponId).collect(Collectors.toList());
            allCouponIds.addAll(couponIds);
        }
        LambdaQueryWrapper<SmsCoupon> couponWrapper = new LambdaQueryWrapper<SmsCoupon>()
                .gt(SmsCoupon::getEndTime, new Date())
                .lt(SmsCoupon::getStartTime, new Date())
                .eq(SmsCoupon::getUseType, 0);
        if (CollUtil.isNotEmpty(allCouponIds)) {
            couponWrapper.or(w -> w
                    .gt(SmsCoupon::getEndTime, new Date())
                    .lt(SmsCoupon::getStartTime, new Date())
                    .ne(SmsCoupon::getUseType, 0)
                    .in(SmsCoupon::getId, allCouponIds));
        }
        return couponMapper.selectList(couponWrapper);
    }

    @Override
    public List<SmsCoupon> list(Integer useStatus) {
        UmsMember member = memberService.getCurrentMember();
        return couponHistoryDao.getCouponList(member.getId(), useStatus);
    }

    private BigDecimal calcTotalAmount(List<CartPromotionItem> cartItemList) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
            total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }

    private BigDecimal calcTotalAmountByproductCategoryId(List<CartPromotionItem> cartItemList, List<Long> productCategoryIds) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            if (productCategoryIds.contains(item.getProductCategoryId())) {
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }

    private BigDecimal calcTotalAmountByProductId(List<CartPromotionItem> cartItemList, List<Long> productIds) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            if (productIds.contains(item.getProductId())) {
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }
}
