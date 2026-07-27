package com.lgoshop.mapper;

import com.lgoshop.model.UmsIntegrationChangeHistory;
import com.lgoshop.model.UmsIntegrationChangeHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsIntegrationChangeHistoryMapper extends BaseMapper<UmsIntegrationChangeHistory> {
    long countByExample(UmsIntegrationChangeHistoryExample example);
    int deleteByExample(UmsIntegrationChangeHistoryExample example);
    int insert(UmsIntegrationChangeHistory record);
    int insertSelective(UmsIntegrationChangeHistory record);
    List<UmsIntegrationChangeHistory> selectByExample(UmsIntegrationChangeHistoryExample example);
    int updateByExampleSelective(@Param("record") UmsIntegrationChangeHistory record, @Param("example") UmsIntegrationChangeHistoryExample example);
    int updateByExample(@Param("record") UmsIntegrationChangeHistory record, @Param("example") UmsIntegrationChangeHistoryExample example);
}
