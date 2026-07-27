package com.lgoshop.mapper;

import com.lgoshop.model.UmsMenu;
import com.lgoshop.model.UmsResource;
import com.lgoshop.model.UmsRole;
import com.lgoshop.dto.*;
import com.lgoshop.model.UmsRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsRoleMapper extends BaseMapper<UmsRole> {
    long countByExample(UmsRoleExample example);
    int deleteByExample(UmsRoleExample example);
    int insert(UmsRole record);
    int insertSelective(UmsRole record);
    List<UmsRole> selectByExample(UmsRoleExample example);
    int updateByExampleSelective(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);
    int updateByExample(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);
/**
* 根据后台用户ID获取菜单
*/
List<UmsMenu> getMenuList(@Param("adminId") Long adminId);
//* 根据角色ID获取菜单
List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);
//* 根据角色ID获取资源
List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
