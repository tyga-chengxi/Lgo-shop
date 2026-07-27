package com.lgoshop.mapper;

import com.lgoshop.model.UmsMemberLoginLog;
import com.lgoshop.model.UmsMemberLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsMemberLoginLogMapper extends BaseMapper<UmsMemberLoginLog> {
    long countByExample(UmsMemberLoginLogExample example);
    int deleteByExample(UmsMemberLoginLogExample example);
    int insert(UmsMemberLoginLog record);
    int insertSelective(UmsMemberLoginLog record);
    List<UmsMemberLoginLog> selectByExample(UmsMemberLoginLogExample example);
    int updateByExampleSelective(@Param("record") UmsMemberLoginLog record, @Param("example") UmsMemberLoginLogExample example);
    int updateByExample(@Param("record") UmsMemberLoginLog record, @Param("example") UmsMemberLoginLogExample example);
}
