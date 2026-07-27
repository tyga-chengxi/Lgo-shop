package com.lgoshop.mapper;

import com.lgoshop.model.PmsProductAttributeCategory;
import com.lgoshop.dto.*;
import com.lgoshop.model.PmsProductAttributeCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsProductAttributeCategoryMapper extends BaseMapper<PmsProductAttributeCategory> {
    long countByExample(PmsProductAttributeCategoryExample example);
    int deleteByExample(PmsProductAttributeCategoryExample example);
    int insert(PmsProductAttributeCategory record);
    int insertSelective(PmsProductAttributeCategory record);
    List<PmsProductAttributeCategory> selectByExample(PmsProductAttributeCategoryExample example);
    int updateByExampleSelective(@Param("record") PmsProductAttributeCategory record, @Param("example") PmsProductAttributeCategoryExample example);
    int updateByPrimaryKey(PmsProductAttributeCategory record);
    int updateByExample(@Param("record") PmsProductAttributeCategory record, @Param("example") PmsProductAttributeCategoryExample example);
/**
* 获取包含属性的商品属性分类
*/
List<PmsProductAttributeCategoryItem> getListWithAttr();
}
