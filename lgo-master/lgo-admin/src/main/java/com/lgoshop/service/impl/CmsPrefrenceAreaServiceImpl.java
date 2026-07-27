package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.lgoshop.mapper.CmsPrefrenceAreaMapper;
import com.lgoshop.model.CmsPrefrenceArea;
import com.lgoshop.model.CmsPrefrenceAreaExample;
import com.lgoshop.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 商品优选管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;
    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectList(new LambdaQueryWrapper<>());
    }
}
