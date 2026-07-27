package com.lgoshop.mapper;

import com.lgoshop.model.SmsHomeBrand;
import com.lgoshop.model.SmsHomeBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsHomeBrandMapper extends BaseMapper<SmsHomeBrand> {
    long countByExample(SmsHomeBrandExample example);
    int deleteByExample(SmsHomeBrandExample example);
    int insert(SmsHomeBrand record);
    int insertSelective(SmsHomeBrand record);
    List<SmsHomeBrand> selectByExample(SmsHomeBrandExample example);
    int updateByExampleSelective(@Param("record") SmsHomeBrand record, @Param("example") SmsHomeBrandExample example);
    int updateByExample(@Param("record") SmsHomeBrand record, @Param("example") SmsHomeBrandExample example);
}
