package com.lgoshop.mapper;

import com.lgoshop.model.UmsAdminLoginLog;
import com.lgoshop.model.UmsAdminLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsAdminLoginLogMapper extends BaseMapper<UmsAdminLoginLog> {
    long countByExample(UmsAdminLoginLogExample example);
    int deleteByExample(UmsAdminLoginLogExample example);
    int insert(UmsAdminLoginLog record);
    int insertSelective(UmsAdminLoginLog record);
    List<UmsAdminLoginLog> selectByExample(UmsAdminLoginLogExample example);
    int updateByExampleSelective(@Param("record") UmsAdminLoginLog record, @Param("example") UmsAdminLoginLogExample example);
    int updateByExample(@Param("record") UmsAdminLoginLog record, @Param("example") UmsAdminLoginLogExample example);
}
