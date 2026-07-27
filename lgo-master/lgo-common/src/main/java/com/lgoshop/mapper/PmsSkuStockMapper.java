package com.lgoshop.mapper;

import com.lgoshop.model.PmsSkuStock;
import com.lgoshop.model.PmsSkuStockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsSkuStockMapper extends BaseMapper<PmsSkuStock> {
    long countByExample(PmsSkuStockExample example);
    int deleteByExample(PmsSkuStockExample example);
    int insert(PmsSkuStock record);
    int insertSelective(PmsSkuStock record);
    List<PmsSkuStock> selectByExample(PmsSkuStockExample example);
    int updateByExampleSelective(@Param("record") PmsSkuStock record, @Param("example") PmsSkuStockExample example);
    int updateByExample(@Param("record") PmsSkuStock record, @Param("example") PmsSkuStockExample example);
/**
* 批量插入操作
*/
int insertList(@Param("list")List<PmsSkuStock> skuStockList);
//* 批量插入或替换操作
int replaceList(@Param("list")List<PmsSkuStock> skuStockList);
}
