package com.lgoshop.mapper;

import com.lgoshop.model.SmsHomeRecommendSubject;
import com.lgoshop.model.SmsHomeRecommendSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsHomeRecommendSubjectMapper extends BaseMapper<SmsHomeRecommendSubject> {
    long countByExample(SmsHomeRecommendSubjectExample example);
    int deleteByExample(SmsHomeRecommendSubjectExample example);
    int insert(SmsHomeRecommendSubject record);
    int insertSelective(SmsHomeRecommendSubject record);
    List<SmsHomeRecommendSubject> selectByExample(SmsHomeRecommendSubjectExample example);
    int updateByExampleSelective(@Param("record") SmsHomeRecommendSubject record, @Param("example") SmsHomeRecommendSubjectExample example);
    int updateByExample(@Param("record") SmsHomeRecommendSubject record, @Param("example") SmsHomeRecommendSubjectExample example);
}
