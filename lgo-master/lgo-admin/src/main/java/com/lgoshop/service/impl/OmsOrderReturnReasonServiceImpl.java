package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.OmsOrderReturnReasonMapper;
import com.lgoshop.model.OmsOrderReturnReason;
import com.lgoshop.model.OmsOrderReturnReasonExample;
import com.lgoshop.service.OmsOrderReturnReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单原因管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {
    @Autowired
    private OmsOrderReturnReasonMapper returnReasonMapper;

    @Override
    public int create(OmsOrderReturnReason returnReason) {
        returnReason.setCreateTime(new Date());
        return returnReasonMapper.insert(returnReason);
    }

    @Override
    public int update(Long id, OmsOrderReturnReason returnReason) {
        returnReason.setId(id);
        return returnReasonMapper.updateById(returnReason);
    }

    @Override
    public int delete(List<Long> ids) {
        return returnReasonMapper.delete(new LambdaQueryWrapper<OmsOrderReturnReason>());
    }

    @Override
    public IPage<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum) {
        Page<OmsOrderReturnReason> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<OmsOrderReturnReason> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(OmsOrderReturnReason::getSort);
        return returnReasonMapper.selectPage(page, queryWrapper);
    }

    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        if (!status.equals(0) && !status.equals(1)) {
            return 0;
        }
        OmsOrderReturnReason record = new OmsOrderReturnReason();
        record.setStatus(status);
        return returnReasonMapper.update(record, new LambdaUpdateWrapper<>());
    }

    @Override
    public OmsOrderReturnReason getItem(Long id) {
        return returnReasonMapper.selectById(id);
    }
}
