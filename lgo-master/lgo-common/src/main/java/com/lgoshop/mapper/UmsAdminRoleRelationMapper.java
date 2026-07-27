package com.lgoshop.mapper;

import com.lgoshop.model.UmsAdminRoleRelation;
import com.lgoshop.dto.*;
import com.lgoshop.model.UmsAdminRoleRelationExample;
import java.util.List;

import com.lgoshop.model.UmsResource;
import com.lgoshop.model.UmsRole;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsAdminRoleRelationMapper extends BaseMapper<UmsAdminRoleRelation> {
    long countByExample(UmsAdminRoleRelationExample example);
    int deleteByExample(UmsAdminRoleRelationExample example);
    int insert(UmsAdminRoleRelation record);
    int insertSelective(UmsAdminRoleRelation record);
    List<UmsAdminRoleRelation> selectByExample(UmsAdminRoleRelationExample example);
    int updateByExampleSelective(@Param("record") UmsAdminRoleRelation record, @Param("example") UmsAdminRoleRelationExample example);
    int updateByExample(@Param("record") UmsAdminRoleRelation record, @Param("example") UmsAdminRoleRelationExample example);
/**
* 批量插入用户角色关系
*/
int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);
//* 获取用于所有角色
List<UmsRole> getRoleList(@Param("adminId") Long adminId);
//* 获取用户所有可访问资源
List<UmsResource> getResourceList(@Param("adminId") Long adminId);
//* 获取资源相关用户ID列表
List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}
