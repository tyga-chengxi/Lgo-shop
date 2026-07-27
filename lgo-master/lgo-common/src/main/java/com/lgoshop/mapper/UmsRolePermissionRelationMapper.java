package com.lgoshop.mapper;

import com.lgoshop.model.UmsRolePermissionRelation;
import com.lgoshop.model.UmsRolePermissionRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsRolePermissionRelationMapper extends BaseMapper<UmsRolePermissionRelation> {
    long countByExample(UmsRolePermissionRelationExample example);
    int deleteByExample(UmsRolePermissionRelationExample example);
    int insert(UmsRolePermissionRelation record);
    int insertSelective(UmsRolePermissionRelation record);
    List<UmsRolePermissionRelation> selectByExample(UmsRolePermissionRelationExample example);
    int updateByExampleSelective(@Param("record") UmsRolePermissionRelation record, @Param("example") UmsRolePermissionRelationExample example);
    int updateByExample(@Param("record") UmsRolePermissionRelation record, @Param("example") UmsRolePermissionRelationExample example);
}
