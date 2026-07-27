package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.SmsHomeRecommendSubjectMapper;
import com.lgoshop.model.SmsHomeRecommendSubject;
import com.lgoshop.model.SmsHomeRecommendSubjectExample;
import com.lgoshop.service.SmsHomeRecommendSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页专题推荐管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectService {
    @Autowired
    private SmsHomeRecommendSubjectMapper smsHomeRecommendSubjectMapper;

    @Override
    public int create(List<SmsHomeRecommendSubject> recommendSubjectList) {
        for (SmsHomeRecommendSubject recommendSubject : recommendSubjectList) {
            recommendSubject.setRecommendStatus(1);
            recommendSubject.setSort(0);
            smsHomeRecommendSubjectMapper.insert(recommendSubject);
        }
        return recommendSubjectList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendSubject recommendSubject = new SmsHomeRecommendSubject();
        recommendSubject.setId(id);
        recommendSubject.setSort(sort);
        return smsHomeRecommendSubjectMapper.updateById(recommendSubject);
    }

    @Override
    public int delete(List<Long> ids) {
        return smsHomeRecommendSubjectMapper.delete(new LambdaQueryWrapper<SmsHomeRecommendSubject>());
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendSubject record = new SmsHomeRecommendSubject();
        record.setRecommendStatus(recommendStatus);
        return smsHomeRecommendSubjectMapper.update(record, new LambdaUpdateWrapper<>());
    }

    @Override
    public IPage<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeRecommendSubject> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SmsHomeRecommendSubject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(subjectName), SmsHomeRecommendSubject::getSubjectName, subjectName)
                .eq(recommendStatus != null, SmsHomeRecommendSubject::getRecommendStatus, recommendStatus);
        return smsHomeRecommendSubjectMapper.selectPage(page, queryWrapper);
    }
}
