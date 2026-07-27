package com.lgoshop.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.dto.SmsFlashPromotionProduct;
import com.lgoshop.model.SmsFlashPromotionProductRelation;
import com.lgoshop.model.SmsFlashPromotionProductRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsFlashPromotionProductRelationMapper extends BaseMapper<SmsFlashPromotionProductRelation> {
    long countByExample(SmsFlashPromotionProductRelationExample example);
    int deleteByExample(SmsFlashPromotionProductRelationExample example);
    int insert(SmsFlashPromotionProductRelation record);
    int insertSelective(SmsFlashPromotionProductRelation record);
    List<SmsFlashPromotionProductRelation> selectByExample(SmsFlashPromotionProductRelationExample example);
    int updateByExampleSelective(@Param("record") SmsFlashPromotionProductRelation record, @Param("example") SmsFlashPromotionProductRelationExample example);
    int updateByPrimaryKey(SmsFlashPromotionProductRelation record);
    int updateByExample(@Param("record") SmsFlashPromotionProductRelation record, @Param("example") SmsFlashPromotionProductRelationExample example);
    IPage<SmsFlashPromotionProduct> selectFlashPage(Page<SmsFlashPromotionProduct> page,
        @Param("flashPromotionId") Long flashPromotionId,
        @Param("flashPromotionSessionId") Long flashPromotionSessionId);
/**
* 获取限时购及相关商品信息
*/
List<SmsFlashPromotionProduct> getList(@Param("flashPromotionId") Long flashPromotionId, @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}
