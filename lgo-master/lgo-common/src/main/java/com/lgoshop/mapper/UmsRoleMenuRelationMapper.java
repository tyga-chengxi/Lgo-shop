package com.lgoshop.mapper;

import com.lgoshop.model.UmsRoleMenuRelation;
import com.lgoshop.model.UmsRoleMenuRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsRoleMenuRelationMapper extends BaseMapper<UmsRoleMenuRelation> {
    long countByExample(UmsRoleMenuRelationExample example);
    int deleteByExample(UmsRoleMenuRelationExample example);
    int insert(UmsRoleMenuRelation record);
    int insertSelective(UmsRoleMenuRelation record);
    List<UmsRoleMenuRelation> selectByExample(UmsRoleMenuRelationExample example);
    int updateByExampleSelective(@Param("record") UmsRoleMenuRelation record, @Param("example") UmsRoleMenuRelationExample example);
    int updateByExample(@Param("record") UmsRoleMenuRelation record, @Param("example") UmsRoleMenuRelationExample example);
}
