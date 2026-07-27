package com.lgoshop.mapper;

import com.lgoshop.model.CmsPrefrenceAreaProductRelation;
import com.lgoshop.model.CmsPrefrenceAreaProductRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface CmsPrefrenceAreaProductRelationMapper extends BaseMapper<CmsPrefrenceAreaProductRelation> {
    long countByExample(CmsPrefrenceAreaProductRelationExample example);
    int deleteByExample(CmsPrefrenceAreaProductRelationExample example);
    int insert(CmsPrefrenceAreaProductRelation record);
    int insertSelective(CmsPrefrenceAreaProductRelation record);
    List<CmsPrefrenceAreaProductRelation> selectByExample(CmsPrefrenceAreaProductRelationExample example);
    int updateByExampleSelective(@Param("record") CmsPrefrenceAreaProductRelation record, @Param("example") CmsPrefrenceAreaProductRelationExample example);
    int updateByExample(@Param("record") CmsPrefrenceAreaProductRelation record, @Param("example") CmsPrefrenceAreaProductRelationExample example);
/**
* 批量创建
*/
int insertList(@Param("list") List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList);
}
