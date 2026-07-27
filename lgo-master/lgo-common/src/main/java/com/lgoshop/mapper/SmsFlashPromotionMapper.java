package com.lgoshop.mapper;

import com.lgoshop.model.SmsFlashPromotion;
import com.lgoshop.model.SmsFlashPromotionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsFlashPromotionMapper extends BaseMapper<SmsFlashPromotion> {
    long countByExample(SmsFlashPromotionExample example);
    int deleteByExample(SmsFlashPromotionExample example);
    int insert(SmsFlashPromotion record);
    int insertSelective(SmsFlashPromotion record);
    List<SmsFlashPromotion> selectByExample(SmsFlashPromotionExample example);
    int updateByExampleSelective(@Param("record") SmsFlashPromotion record, @Param("example") SmsFlashPromotionExample example);
    int updateByPrimaryKey(SmsFlashPromotion record);
    int updateByExample(@Param("record") SmsFlashPromotion record, @Param("example") SmsFlashPromotionExample example);
}
