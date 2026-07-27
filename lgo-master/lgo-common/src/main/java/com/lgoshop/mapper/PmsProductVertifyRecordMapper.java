package com.lgoshop.mapper;

import com.lgoshop.model.PmsProductVertifyRecord;
import com.lgoshop.model.PmsProductVertifyRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsProductVertifyRecordMapper extends BaseMapper<PmsProductVertifyRecord> {
    long countByExample(PmsProductVertifyRecordExample example);
    int deleteByExample(PmsProductVertifyRecordExample example);
    int insert(PmsProductVertifyRecord record);
    int insertSelective(PmsProductVertifyRecord record);
    List<PmsProductVertifyRecord> selectByExample(PmsProductVertifyRecordExample example);
    int updateByExampleSelective(@Param("record") PmsProductVertifyRecord record, @Param("example") PmsProductVertifyRecordExample example);
    int updateByExample(@Param("record") PmsProductVertifyRecord record, @Param("example") PmsProductVertifyRecordExample example);
/**
* 批量创建
*/
int insertList(@Param("list") List<PmsProductVertifyRecord> list);
}
