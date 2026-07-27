package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.OmsOrderReturnApplyMapper;
import com.lgoshop.dto.OmsOrderReturnApplyResult;
import com.lgoshop.dto.OmsReturnApplyQueryParam;
import com.lgoshop.dto.OmsUpdateStatusParam;
import com.lgoshop.model.OmsOrderReturnApply;
import com.lgoshop.model.OmsOrderReturnApplyExample;
import com.lgoshop.service.OmsOrderReturnApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单退货管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {
    @Autowired
    private OmsOrderReturnApplyMapper returnApplyMapper;

    @Override
    public IPage<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        Page<OmsOrderReturnApply> page = new Page<>(pageNum, pageSize);
        return returnApplyMapper.getList(page, queryParam);
    }

    @Override
    public int delete(List<Long> ids) {
        return returnApplyMapper.delete(new LambdaQueryWrapper<OmsOrderReturnApply>());
    }

    @Override
    public int updateStatus(Long id, OmsUpdateStatusParam statusParam) {
        Integer status = statusParam.getStatus();
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        if (status.equals(1)) {
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(statusParam.getReturnAmount());
            returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        } else if (status.equals(2)) {
            returnApply.setId(id);
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new Date());
            returnApply.setReceiveMan(statusParam.getReceiveMan());
            returnApply.setReceiveNote(statusParam.getReceiveNote());
        } else if (status.equals(3)) {
            returnApply.setId(id);
            returnApply.setStatus(3);
        } else {
            return 0;
        }
        return returnApplyMapper.updateById(returnApply);
    }

    @Override
    public OmsOrderReturnApplyResult getItem(Long id) {
        return returnApplyMapper.getDetail(id);
    }
}
