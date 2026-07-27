package com.lgoshop.mapper;

import com.lgoshop.model.PmsProductCategoryAttributeRelation;
import com.lgoshop.model.PmsProductCategoryAttributeRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsProductCategoryAttributeRelationMapper extends BaseMapper<PmsProductCategoryAttributeRelation> {
    long countByExample(PmsProductCategoryAttributeRelationExample example);
    int deleteByExample(PmsProductCategoryAttributeRelationExample example);
    int insert(PmsProductCategoryAttributeRelation record);
    int insertSelective(PmsProductCategoryAttributeRelation record);
    List<PmsProductCategoryAttributeRelation> selectByExample(PmsProductCategoryAttributeRelationExample example);
    int updateByExampleSelective(@Param("record") PmsProductCategoryAttributeRelation record, @Param("example") PmsProductCategoryAttributeRelationExample example);
    int updateByExample(@Param("record") PmsProductCategoryAttributeRelation record, @Param("example") PmsProductCategoryAttributeRelationExample example);
/**
* 批量创建
*/
int insertList(@Param("list") List<PmsProductCategoryAttributeRelation> productCategoryAttributeRelationList);
}
