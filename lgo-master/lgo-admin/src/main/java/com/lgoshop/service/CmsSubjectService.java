package com.lgoshop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgoshop.model.CmsSubject;

import java.util.List;

/**
 * 商品专题管理Service
 * Created by lgo-shop.
 */
public interface CmsSubjectService {
    /**
     * 查询所有专题
     */
    List<CmsSubject> listAll();

    /**
     * 分页查询专题
     */
    IPage<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);
}
