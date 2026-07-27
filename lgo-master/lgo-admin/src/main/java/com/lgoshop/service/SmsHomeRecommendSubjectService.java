package com.lgoshop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgoshop.model.SmsHomeRecommendSubject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页专题推荐管理Service
 * Created by lgo-shop.
 */
public interface SmsHomeRecommendSubjectService {
    /**
     * 添加专题推荐
     */
    @Transactional
    int create(List<SmsHomeRecommendSubject> recommendSubjectList);

    /**
     * 修改专题推荐排序
     */
    int updateSort(Long id, Integer sort);

    /**
     * 批量删除专题推荐
     */
    int delete(List<Long> ids);

    /**
     * 批量更新专题推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 分页查询专题推荐
     */
    IPage<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
