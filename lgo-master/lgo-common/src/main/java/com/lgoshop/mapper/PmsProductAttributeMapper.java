package com.lgoshop.mapper;

import com.lgoshop.model.PmsProductAttribute;
import com.lgoshop.dto.*;
import com.lgoshop.model.PmsProductAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsProductAttributeMapper extends BaseMapper<PmsProductAttribute> {
    long countByExample(PmsProductAttributeExample example);
    int deleteByExample(PmsProductAttributeExample example);
    int insert(PmsProductAttribute record);
    int insertSelective(PmsProductAttribute record);
    List<PmsProductAttribute> selectByExample(PmsProductAttributeExample example);
    int updateByExampleSelective(@Param("record") PmsProductAttribute record, @Param("example") PmsProductAttributeExample example);
    int updateByExample(@Param("record") PmsProductAttribute record, @Param("example") PmsProductAttributeExample example);
/**
* 获取商品属性信息
*/
List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);
}
