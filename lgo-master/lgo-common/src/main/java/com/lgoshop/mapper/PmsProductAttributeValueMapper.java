package com.lgoshop.mapper;

import com.lgoshop.model.PmsProductAttributeValue;
import com.lgoshop.model.PmsProductAttributeValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsProductAttributeValueMapper extends BaseMapper<PmsProductAttributeValue> {
    long countByExample(PmsProductAttributeValueExample example);
    int deleteByExample(PmsProductAttributeValueExample example);
    int insert(PmsProductAttributeValue record);
    int insertSelective(PmsProductAttributeValue record);
    List<PmsProductAttributeValue> selectByExample(PmsProductAttributeValueExample example);
    int updateByExampleSelective(@Param("record") PmsProductAttributeValue record, @Param("example") PmsProductAttributeValueExample example);
    int updateByExample(@Param("record") PmsProductAttributeValue record, @Param("example") PmsProductAttributeValueExample example);
/**
* 批量创建
*/
int insertList(@Param("list")List<PmsProductAttributeValue> productAttributeValueList);
}
