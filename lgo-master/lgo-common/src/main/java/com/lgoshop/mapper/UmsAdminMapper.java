package com.lgoshop.mapper;

import com.lgoshop.model.UmsAdmin;
import com.lgoshop.model.UmsAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {
    long countByExample(UmsAdminExample example);
    int deleteByExample(UmsAdminExample example);
    int insert(UmsAdmin record);
    int insertSelective(UmsAdmin record);
    List<UmsAdmin> selectByExample(UmsAdminExample example);
    int updateByExampleSelective(@Param("record") UmsAdmin record, @Param("example") UmsAdminExample example);
    int updateByPrimaryKey(UmsAdmin record);
    int updateByExample(@Param("record") UmsAdmin record, @Param("example") UmsAdminExample example);
}
