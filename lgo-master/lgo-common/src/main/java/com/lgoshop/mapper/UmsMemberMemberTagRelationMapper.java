package com.lgoshop.mapper;

import com.lgoshop.model.UmsMemberMemberTagRelation;
import com.lgoshop.model.UmsMemberMemberTagRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsMemberMemberTagRelationMapper extends BaseMapper<UmsMemberMemberTagRelation> {
    long countByExample(UmsMemberMemberTagRelationExample example);
    int deleteByExample(UmsMemberMemberTagRelationExample example);
    int insert(UmsMemberMemberTagRelation record);
    int insertSelective(UmsMemberMemberTagRelation record);
    List<UmsMemberMemberTagRelation> selectByExample(UmsMemberMemberTagRelationExample example);
    int updateByExampleSelective(@Param("record") UmsMemberMemberTagRelation record, @Param("example") UmsMemberMemberTagRelationExample example);
    int updateByExample(@Param("record") UmsMemberMemberTagRelation record, @Param("example") UmsMemberMemberTagRelationExample example);
}
