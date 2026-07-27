package com.lgoshop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.SmsCouponHistoryMapper;
import com.lgoshop.model.SmsCouponHistory;
import com.lgoshop.model.SmsCouponHistoryExample;
import com.lgoshop.service.SmsCouponHistoryService;
import com.lgoshop.service.SmsHomeRecommendSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 优惠券领取记录管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {
    @Autowired
    private SmsCouponHistoryMapper historyMapper;
    @Override
    public IPage<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        Page page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<SmsCouponHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(couponId!=null,SmsCouponHistory::getCouponId,couponId)
                .eq(useStatus!=null,SmsCouponHistory::getUseStatus,useStatus)
                .eq(StrUtil.isNotEmpty(orderSn),SmsCouponHistory::getOrderSn,orderSn);
        return historyMapper.selectPage(page,queryWrapper);
    }
}
