package com.lgoshop.mapper;

import com.lgoshop.model.UmsRoleResourceRelation;
import com.lgoshop.model.UmsRoleResourceRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsRoleResourceRelationMapper extends BaseMapper<UmsRoleResourceRelation> {
    long countByExample(UmsRoleResourceRelationExample example);
    int deleteByExample(UmsRoleResourceRelationExample example);
    int insert(UmsRoleResourceRelation record);
    int insertSelective(UmsRoleResourceRelation record);
    List<UmsRoleResourceRelation> selectByExample(UmsRoleResourceRelationExample example);
    int updateByExampleSelective(@Param("record") UmsRoleResourceRelation record, @Param("example") UmsRoleResourceRelationExample example);
    int updateByExample(@Param("record") UmsRoleResourceRelation record, @Param("example") UmsRoleResourceRelationExample example);
}
