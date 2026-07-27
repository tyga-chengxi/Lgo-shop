package com.lgoshop.mapper;

import com.lgoshop.model.SmsHomeAdvertise;
import com.lgoshop.model.SmsHomeAdvertiseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsHomeAdvertiseMapper extends BaseMapper<SmsHomeAdvertise> {
    long countByExample(SmsHomeAdvertiseExample example);
    int deleteByExample(SmsHomeAdvertiseExample example);
    int insert(SmsHomeAdvertise record);
    int insertSelective(SmsHomeAdvertise record);
    List<SmsHomeAdvertise> selectByExample(SmsHomeAdvertiseExample example);
    int updateByExampleSelective(@Param("record") SmsHomeAdvertise record, @Param("example") SmsHomeAdvertiseExample example);
    int updateByExample(@Param("record") SmsHomeAdvertise record, @Param("example") SmsHomeAdvertiseExample example);
}
