package com.lgoshop.mapper;

import com.lgoshop.model.OmsOrderItem;
import com.lgoshop.model.OmsOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface OmsOrderItemMapper extends BaseMapper<OmsOrderItem> {
    long countByExample(OmsOrderItemExample example);
    int deleteByExample(OmsOrderItemExample example);
    int insert(OmsOrderItem record);
    int insertSelective(OmsOrderItem record);
    List<OmsOrderItem> selectByExample(OmsOrderItemExample example);
    int updateByExampleSelective(@Param("record") OmsOrderItem record, @Param("example") OmsOrderItemExample example);
    int updateByExample(@Param("record") OmsOrderItem record, @Param("example") OmsOrderItemExample example);
}
