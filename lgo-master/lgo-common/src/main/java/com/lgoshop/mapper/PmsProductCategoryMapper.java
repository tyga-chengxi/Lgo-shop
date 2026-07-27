package com.lgoshop.mapper;

import com.lgoshop.model.PmsProductCategory;
import com.lgoshop.dto.*;
import com.lgoshop.model.PmsProductCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {
    long countByExample(PmsProductCategoryExample example);
    int deleteByExample(PmsProductCategoryExample example);
    int insert(PmsProductCategory record);
    int insertSelective(PmsProductCategory record);
    List<PmsProductCategory> selectByExampleWithBLOBs(PmsProductCategoryExample example);
    List<PmsProductCategory> selectByExample(PmsProductCategoryExample example);
    int updateByExampleSelective(@Param("record") PmsProductCategory record, @Param("example") PmsProductCategoryExample example);
    int updateByExampleWithBLOBs(@Param("record") PmsProductCategory record, @Param("example") PmsProductCategoryExample example);
    int updateByExample(@Param("record") PmsProductCategory record, @Param("example") PmsProductCategoryExample example);
    int updateByPrimaryKeyWithBLOBs(PmsProductCategory record);
/**
* 获取商品分类及其子分类
*/
List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
