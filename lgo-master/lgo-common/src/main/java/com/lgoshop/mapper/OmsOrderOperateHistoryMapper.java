package com.lgoshop.mapper;

import com.lgoshop.model.OmsOrderOperateHistory;
import com.lgoshop.model.OmsOrderOperateHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface OmsOrderOperateHistoryMapper extends BaseMapper<OmsOrderOperateHistory> {
    long countByExample(OmsOrderOperateHistoryExample example);
    int deleteByExample(OmsOrderOperateHistoryExample example);
    int insert(OmsOrderOperateHistory record);
    int insertSelective(OmsOrderOperateHistory record);
    List<OmsOrderOperateHistory> selectByExample(OmsOrderOperateHistoryExample example);
    int updateByExampleSelective(@Param("record") OmsOrderOperateHistory record, @Param("example") OmsOrderOperateHistoryExample example);
    int updateByExample(@Param("record") OmsOrderOperateHistory record, @Param("example") OmsOrderOperateHistoryExample example);
/**
* 批量创建
*/
int insertList(@Param("list") List<OmsOrderOperateHistory> orderOperateHistoryList);
}
