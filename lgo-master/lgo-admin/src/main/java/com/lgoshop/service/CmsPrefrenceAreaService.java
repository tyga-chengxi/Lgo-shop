package com.lgoshop.service;

import com.lgoshop.model.CmsPrefrenceArea;

import java.util.List;

/**
 * 优选专区管理Service
 * Created by lgo-shop.
 */
public interface CmsPrefrenceAreaService {
    /**
     * 获取所有优选专区
     */
    List<CmsPrefrenceArea> listAll();
}
