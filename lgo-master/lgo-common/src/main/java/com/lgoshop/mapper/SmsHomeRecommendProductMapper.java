package com.lgoshop.mapper;

import com.lgoshop.model.SmsHomeRecommendProduct;
import com.lgoshop.model.SmsHomeRecommendProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsHomeRecommendProductMapper extends BaseMapper<SmsHomeRecommendProduct> {
    long countByExample(SmsHomeRecommendProductExample example);
    int deleteByExample(SmsHomeRecommendProductExample example);
    int insert(SmsHomeRecommendProduct record);
    int insertSelective(SmsHomeRecommendProduct record);
    List<SmsHomeRecommendProduct> selectByExample(SmsHomeRecommendProductExample example);
    int updateByExampleSelective(@Param("record") SmsHomeRecommendProduct record, @Param("example") SmsHomeRecommendProductExample example);
    int updateByExample(@Param("record") SmsHomeRecommendProduct record, @Param("example") SmsHomeRecommendProductExample example);
}
