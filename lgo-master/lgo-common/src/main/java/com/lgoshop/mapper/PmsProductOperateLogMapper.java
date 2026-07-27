package com.lgoshop.mapper;

import com.lgoshop.model.PmsProductOperateLog;
import com.lgoshop.model.PmsProductOperateLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsProductOperateLogMapper extends BaseMapper<PmsProductOperateLog> {
    long countByExample(PmsProductOperateLogExample example);
    int deleteByExample(PmsProductOperateLogExample example);
    int insert(PmsProductOperateLog record);
    int insertSelective(PmsProductOperateLog record);
    List<PmsProductOperateLog> selectByExample(PmsProductOperateLogExample example);
    int updateByExampleSelective(@Param("record") PmsProductOperateLog record, @Param("example") PmsProductOperateLogExample example);
    int updateByExample(@Param("record") PmsProductOperateLog record, @Param("example") PmsProductOperateLogExample example);
}
