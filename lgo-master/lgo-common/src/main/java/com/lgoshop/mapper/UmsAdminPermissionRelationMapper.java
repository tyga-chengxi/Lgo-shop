package com.lgoshop.mapper;

import com.lgoshop.model.UmsAdminPermissionRelation;
import com.lgoshop.model.UmsAdminPermissionRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsAdminPermissionRelationMapper extends BaseMapper<UmsAdminPermissionRelation> {
    long countByExample(UmsAdminPermissionRelationExample example);
    int deleteByExample(UmsAdminPermissionRelationExample example);
    int insert(UmsAdminPermissionRelation record);
    int insertSelective(UmsAdminPermissionRelation record);
    List<UmsAdminPermissionRelation> selectByExample(UmsAdminPermissionRelationExample example);
    int updateByExampleSelective(@Param("record") UmsAdminPermissionRelation record, @Param("example") UmsAdminPermissionRelationExample example);
    int updateByExample(@Param("record") UmsAdminPermissionRelation record, @Param("example") UmsAdminPermissionRelationExample example);
}
