package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.lgoshop.mapper.OmsCompanyAddressMapper;
import com.lgoshop.model.OmsCompanyAddress;
import com.lgoshop.model.OmsCompanyAddressExample;
import com.lgoshop.service.OmsCompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 收货地址管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {
    @Autowired
    private OmsCompanyAddressMapper companyAddressMapper;
    @Override
    public List<OmsCompanyAddress> list() {
        return companyAddressMapper.selectList(new LambdaQueryWrapper<>());
    }
}
