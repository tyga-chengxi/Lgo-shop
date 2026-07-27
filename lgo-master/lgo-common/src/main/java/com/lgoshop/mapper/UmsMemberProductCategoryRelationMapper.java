package com.lgoshop.mapper;

import com.lgoshop.model.UmsMemberProductCategoryRelation;
import com.lgoshop.model.UmsMemberProductCategoryRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsMemberProductCategoryRelationMapper extends BaseMapper<UmsMemberProductCategoryRelation> {
    long countByExample(UmsMemberProductCategoryRelationExample example);
    int deleteByExample(UmsMemberProductCategoryRelationExample example);
    int insert(UmsMemberProductCategoryRelation record);
    int insertSelective(UmsMemberProductCategoryRelation record);
    List<UmsMemberProductCategoryRelation> selectByExample(UmsMemberProductCategoryRelationExample example);
    int updateByExampleSelective(@Param("record") UmsMemberProductCategoryRelation record, @Param("example") UmsMemberProductCategoryRelationExample example);
    int updateByExample(@Param("record") UmsMemberProductCategoryRelation record, @Param("example") UmsMemberProductCategoryRelationExample example);
}
