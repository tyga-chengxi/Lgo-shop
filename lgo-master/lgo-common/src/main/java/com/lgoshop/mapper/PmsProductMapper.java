package com.lgoshop.mapper;

import com.lgoshop.model.PmsProduct;
import com.lgoshop.dto.*;
import com.lgoshop.model.PmsProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsProductMapper extends BaseMapper<PmsProduct> {
    long countByExample(PmsProductExample example);
    int deleteByExample(PmsProductExample example);
    int insert(PmsProduct record);
    int insertSelective(PmsProduct record);
    List<PmsProduct> selectByExampleWithBLOBs(PmsProductExample example);
    List<PmsProduct> selectByExample(PmsProductExample example);
    int updateByExampleSelective(@Param("record") PmsProduct record, @Param("example") PmsProductExample example);
    int updateByExampleWithBLOBs(@Param("record") PmsProduct record, @Param("example") PmsProductExample example);
    int updateByExample(@Param("record") PmsProduct record, @Param("example") PmsProductExample example);
    int updateByPrimaryKeyWithBLOBs(PmsProduct record);
/**
* 获取商品编辑信息
*/
PmsProductResult getUpdateInfo(@Param("id") Long id);
}
