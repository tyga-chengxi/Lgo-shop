package com.lgoshop.mapper;

import com.lgoshop.model.PmsProductFullReduction;
import com.lgoshop.model.PmsProductFullReductionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsProductFullReductionMapper extends BaseMapper<PmsProductFullReduction> {
    long countByExample(PmsProductFullReductionExample example);
    int deleteByExample(PmsProductFullReductionExample example);
    int insert(PmsProductFullReduction record);
    int insertSelective(PmsProductFullReduction record);
    List<PmsProductFullReduction> selectByExample(PmsProductFullReductionExample example);
    int updateByExampleSelective(@Param("record") PmsProductFullReduction record, @Param("example") PmsProductFullReductionExample example);
    int updateByExample(@Param("record") PmsProductFullReduction record, @Param("example") PmsProductFullReductionExample example);
/**
* 批量创建
*/
int insertList(@Param("list") List<PmsProductFullReduction> productFullReductionList);
}
