package com.lgoshop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.CmsSubjectMapper;
import com.lgoshop.model.CmsSubject;
import com.lgoshop.model.CmsSubjectExample;
import com.lgoshop.service.CmsSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 商品专题管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {
    @Autowired
    private CmsSubjectMapper subjectMapper;

    @Override
    public List<CmsSubject> listAll() {
        return subjectMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public IPage<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
        Page<CmsSubject> page = new Page<>();
        LambdaQueryWrapper<CmsSubject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(keyword), CmsSubject::getTitle, keyword);
        return subjectMapper.selectPage(page, queryWrapper);
    }
}
