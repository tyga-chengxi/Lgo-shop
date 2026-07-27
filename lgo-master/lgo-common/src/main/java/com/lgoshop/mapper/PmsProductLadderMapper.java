package com.lgoshop.mapper;

import com.lgoshop.model.PmsProductLadder;
import com.lgoshop.model.PmsProductLadderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsProductLadderMapper extends BaseMapper<PmsProductLadder> {
    long countByExample(PmsProductLadderExample example);
    int deleteByExample(PmsProductLadderExample example);
    int insert(PmsProductLadder record);
    int insertSelective(PmsProductLadder record);
    List<PmsProductLadder> selectByExample(PmsProductLadderExample example);
    int updateByExampleSelective(@Param("record") PmsProductLadder record, @Param("example") PmsProductLadderExample example);
    int updateByExample(@Param("record") PmsProductLadder record, @Param("example") PmsProductLadderExample example);
/**
* 批量创建
*/
int insertList(@Param("list") List<PmsProductLadder> productLadderList);
}
