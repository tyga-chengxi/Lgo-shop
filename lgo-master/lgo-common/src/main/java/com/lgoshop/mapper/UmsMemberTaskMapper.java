package com.lgoshop.mapper;

import com.lgoshop.model.UmsMemberTask;
import com.lgoshop.model.UmsMemberTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsMemberTaskMapper extends BaseMapper<UmsMemberTask> {
    long countByExample(UmsMemberTaskExample example);
    int deleteByExample(UmsMemberTaskExample example);
    int insert(UmsMemberTask record);
    int insertSelective(UmsMemberTask record);
    List<UmsMemberTask> selectByExample(UmsMemberTaskExample example);
    int updateByExampleSelective(@Param("record") UmsMemberTask record, @Param("example") UmsMemberTaskExample example);
    int updateByExample(@Param("record") UmsMemberTask record, @Param("example") UmsMemberTaskExample example);
}
