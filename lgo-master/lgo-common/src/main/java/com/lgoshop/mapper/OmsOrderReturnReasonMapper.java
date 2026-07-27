package com.lgoshop.mapper;

import com.lgoshop.model.OmsOrderReturnReason;
import com.lgoshop.model.OmsOrderReturnReasonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface OmsOrderReturnReasonMapper extends BaseMapper<OmsOrderReturnReason> {
    long countByExample(OmsOrderReturnReasonExample example);
    int deleteByExample(OmsOrderReturnReasonExample example);
    int insert(OmsOrderReturnReason record);
    int insertSelective(OmsOrderReturnReason record);
    List<OmsOrderReturnReason> selectByExample(OmsOrderReturnReasonExample example);
    int updateByExampleSelective(@Param("record") OmsOrderReturnReason record, @Param("example") OmsOrderReturnReasonExample example);
    int updateByPrimaryKey(OmsOrderReturnReason record);
    int updateByExample(@Param("record") OmsOrderReturnReason record, @Param("example") OmsOrderReturnReasonExample example);
}
