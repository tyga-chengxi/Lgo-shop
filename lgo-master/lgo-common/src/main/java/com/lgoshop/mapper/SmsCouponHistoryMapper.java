package com.lgoshop.mapper;

import com.lgoshop.model.SmsCouponHistory;
import com.lgoshop.model.SmsCouponHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsCouponHistoryMapper extends BaseMapper<SmsCouponHistory> {
    long countByExample(SmsCouponHistoryExample example);
    int deleteByExample(SmsCouponHistoryExample example);
    int insert(SmsCouponHistory record);
    int insertSelective(SmsCouponHistory record);
    List<SmsCouponHistory> selectByExample(SmsCouponHistoryExample example);
    int updateByExampleSelective(@Param("record") SmsCouponHistory record, @Param("example") SmsCouponHistoryExample example);
    int updateByExample(@Param("record") SmsCouponHistory record, @Param("example") SmsCouponHistoryExample example);
}
