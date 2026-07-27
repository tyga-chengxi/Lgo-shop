package com.lgoshop.mapper;

import com.lgoshop.model.UmsGrowthChangeHistory;
import com.lgoshop.model.UmsGrowthChangeHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsGrowthChangeHistoryMapper extends BaseMapper<UmsGrowthChangeHistory> {
    long countByExample(UmsGrowthChangeHistoryExample example);
    int deleteByExample(UmsGrowthChangeHistoryExample example);
    int insert(UmsGrowthChangeHistory record);
    int insertSelective(UmsGrowthChangeHistory record);
    List<UmsGrowthChangeHistory> selectByExample(UmsGrowthChangeHistoryExample example);
    int updateByExampleSelective(@Param("record") UmsGrowthChangeHistory record, @Param("example") UmsGrowthChangeHistoryExample example);
    int updateByExample(@Param("record") UmsGrowthChangeHistory record, @Param("example") UmsGrowthChangeHistoryExample example);
}
