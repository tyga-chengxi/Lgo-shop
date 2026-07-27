package com.lgoshop.mapper;

import com.lgoshop.model.UmsPermission;
import com.lgoshop.model.UmsPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsPermissionMapper extends BaseMapper<UmsPermission> {
    long countByExample(UmsPermissionExample example);
    int deleteByExample(UmsPermissionExample example);
    int insert(UmsPermission record);
    int insertSelective(UmsPermission record);
    List<UmsPermission> selectByExample(UmsPermissionExample example);
    int updateByExampleSelective(@Param("record") UmsPermission record, @Param("example") UmsPermissionExample example);
    int updateByExample(@Param("record") UmsPermission record, @Param("example") UmsPermissionExample example);
}
