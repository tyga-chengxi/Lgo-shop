package com.lgoshop.service;

import com.lgoshop.model.OmsCompanyAddress;

import java.util.List;

/**
 * 收货地址管理Service
 * Created by lgo-shop.
 */
public interface OmsCompanyAddressService {
    /**
     * 获取全部收货地址
     */
    List<OmsCompanyAddress> list();
}
