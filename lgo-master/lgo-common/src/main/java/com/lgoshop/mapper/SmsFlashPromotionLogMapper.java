package com.lgoshop.mapper;

import com.lgoshop.model.SmsFlashPromotionLog;
import com.lgoshop.model.SmsFlashPromotionLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsFlashPromotionLogMapper extends BaseMapper<SmsFlashPromotionLog> {
    long countByExample(SmsFlashPromotionLogExample example);
    int deleteByExample(SmsFlashPromotionLogExample example);
    int deleteByPrimaryKey(Integer id);
    int insert(SmsFlashPromotionLog record);
    int insertSelective(SmsFlashPromotionLog record);
    List<SmsFlashPromotionLog> selectByExample(SmsFlashPromotionLogExample example);
    SmsFlashPromotionLog selectByPrimaryKey(Integer id);
    int updateByExampleSelective(@Param("record") SmsFlashPromotionLog record, @Param("example") SmsFlashPromotionLogExample example);
    int updateByExample(@Param("record") SmsFlashPromotionLog record, @Param("example") SmsFlashPromotionLogExample example);
}
