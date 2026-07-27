package com.lgoshop.mapper;

import com.lgoshop.model.CmsPrefrenceArea;
import com.lgoshop.model.CmsPrefrenceAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface CmsPrefrenceAreaMapper extends BaseMapper<CmsPrefrenceArea> {
    long countByExample(CmsPrefrenceAreaExample example);
    int deleteByExample(CmsPrefrenceAreaExample example);
    int insert(CmsPrefrenceArea record);
    int insertSelective(CmsPrefrenceArea record);
    List<CmsPrefrenceArea> selectByExampleWithBLOBs(CmsPrefrenceAreaExample example);
    List<CmsPrefrenceArea> selectByExample(CmsPrefrenceAreaExample example);
    int updateByExampleSelective(@Param("record") CmsPrefrenceArea record, @Param("example") CmsPrefrenceAreaExample example);
    int updateByExampleWithBLOBs(@Param("record") CmsPrefrenceArea record, @Param("example") CmsPrefrenceAreaExample example);
    int updateByExample(@Param("record") CmsPrefrenceArea record, @Param("example") CmsPrefrenceAreaExample example);
    int updateByPrimaryKeyWithBLOBs(CmsPrefrenceArea record);
}
