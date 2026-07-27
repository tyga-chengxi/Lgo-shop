package com.lgoshop.service.impl;

import com.lgoshop.mapper.OmsOrderSettingMapper;
import com.lgoshop.model.OmsOrderSetting;
import com.lgoshop.service.OmsOrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单设置管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {
    @Autowired
    private OmsOrderSettingMapper orderSettingMapper;

    @Override
    public OmsOrderSetting getItem(Long id) {
        return orderSettingMapper.selectById(id);
    }

    @Override
    public int update(Long id, OmsOrderSetting orderSetting) {
        return orderSettingMapper.updateById(orderSetting);
    }
}
