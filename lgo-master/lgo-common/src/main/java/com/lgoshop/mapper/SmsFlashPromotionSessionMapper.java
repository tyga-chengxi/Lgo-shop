package com.lgoshop.mapper;

import com.lgoshop.model.SmsFlashPromotionSession;
import com.lgoshop.model.SmsFlashPromotionSessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsFlashPromotionSessionMapper extends BaseMapper<SmsFlashPromotionSession> {
    long countByExample(SmsFlashPromotionSessionExample example);
    int deleteByExample(SmsFlashPromotionSessionExample example);
    int insert(SmsFlashPromotionSession record);
    int insertSelective(SmsFlashPromotionSession record);
    List<SmsFlashPromotionSession> selectByExample(SmsFlashPromotionSessionExample example);
    int updateByExampleSelective(@Param("record") SmsFlashPromotionSession record, @Param("example") SmsFlashPromotionSessionExample example);
    int updateByPrimaryKey(SmsFlashPromotionSession record);
    int updateByExample(@Param("record") SmsFlashPromotionSession record, @Param("example") SmsFlashPromotionSessionExample example);
}
