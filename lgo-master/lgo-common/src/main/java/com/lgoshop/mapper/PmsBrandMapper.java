package com.lgoshop.mapper;

import com.lgoshop.model.PmsBrand;
import com.lgoshop.model.PmsBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsBrandMapper extends BaseMapper<PmsBrand> {
    long countByExample(PmsBrandExample example);
    int deleteByExample(PmsBrandExample example);
    int insert(PmsBrand record);
    int insertSelective(PmsBrand record);
    List<PmsBrand> selectByExampleWithBLOBs(PmsBrandExample example);
    List<PmsBrand> selectByExample(PmsBrandExample example);
    int updateByExampleSelective(@Param("record") PmsBrand record, @Param("example") PmsBrandExample example);
    int updateByExampleWithBLOBs(@Param("record") PmsBrand record, @Param("example") PmsBrandExample example);
    int updateByExample(@Param("record") PmsBrand record, @Param("example") PmsBrandExample example);
    int updateByPrimaryKeyWithBLOBs(PmsBrand record);
}
