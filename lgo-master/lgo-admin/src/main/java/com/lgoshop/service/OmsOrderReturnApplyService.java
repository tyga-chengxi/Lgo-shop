package com.lgoshop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgoshop.dto.OmsOrderReturnApplyResult;
import com.lgoshop.dto.OmsReturnApplyQueryParam;
import com.lgoshop.dto.OmsUpdateStatusParam;
import com.lgoshop.model.OmsOrderReturnApply;

import java.util.List;

/**
 * 退货申请管理Service
 * Created by lgo-shop.
 */
public interface OmsOrderReturnApplyService {
    /**
     * 分页查询申请
     */
    IPage<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量删除申请
     */
    int delete(List<Long> ids);

    /**
     * 修改指定申请状态
     */
    int updateStatus(Long id, OmsUpdateStatusParam statusParam);

    /**
     * 获取指定申请详情
     */
    OmsOrderReturnApplyResult getItem(Long id);
}
