package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.lgoshop.mapper.UmsMemberLevelMapper;
import com.lgoshop.model.UmsMemberLevel;
import com.lgoshop.model.UmsMemberLevelExample;
import com.lgoshop.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 会员等级管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService{
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;
    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        return memberLevelMapper.selectList(new LambdaQueryWrapper<>());
    }
}
