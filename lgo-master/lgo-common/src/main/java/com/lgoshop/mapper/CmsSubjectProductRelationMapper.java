package com.lgoshop.mapper;

import com.lgoshop.model.CmsSubjectProductRelation;
import com.lgoshop.model.CmsSubjectProductRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface CmsSubjectProductRelationMapper extends BaseMapper<CmsSubjectProductRelation> {
    long countByExample(CmsSubjectProductRelationExample example);
    int deleteByExample(CmsSubjectProductRelationExample example);
    int insert(CmsSubjectProductRelation record);
    int insertSelective(CmsSubjectProductRelation record);
    List<CmsSubjectProductRelation> selectByExample(CmsSubjectProductRelationExample example);
    int updateByExampleSelective(@Param("record") CmsSubjectProductRelation record, @Param("example") CmsSubjectProductRelationExample example);
    int updateByExample(@Param("record") CmsSubjectProductRelation record, @Param("example") CmsSubjectProductRelationExample example);
/**
* 批量创建
*/
int insertList(@Param("list") List<CmsSubjectProductRelation> subjectProductRelationList);
}
