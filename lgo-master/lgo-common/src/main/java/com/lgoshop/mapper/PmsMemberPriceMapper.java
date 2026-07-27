package com.lgoshop.mapper;

import com.lgoshop.model.PmsMemberPrice;
import com.lgoshop.model.PmsMemberPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsMemberPriceMapper extends BaseMapper<PmsMemberPrice> {
    long countByExample(PmsMemberPriceExample example);
    int deleteByExample(PmsMemberPriceExample example);
    int insert(PmsMemberPrice record);
    int insertSelective(PmsMemberPrice record);
    List<PmsMemberPrice> selectByExample(PmsMemberPriceExample example);
    int updateByExampleSelective(@Param("record") PmsMemberPrice record, @Param("example") PmsMemberPriceExample example);
    int updateByExample(@Param("record") PmsMemberPrice record, @Param("example") PmsMemberPriceExample example);
/**
* 批量创建
*/
int insertList(@Param("list") List<PmsMemberPrice> memberPriceList);
}
