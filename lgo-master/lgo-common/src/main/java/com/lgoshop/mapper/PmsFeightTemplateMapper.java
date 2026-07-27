package com.lgoshop.mapper;

import com.lgoshop.model.PmsFeightTemplate;
import com.lgoshop.model.PmsFeightTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsFeightTemplateMapper extends BaseMapper<PmsFeightTemplate> {
    long countByExample(PmsFeightTemplateExample example);
    int deleteByExample(PmsFeightTemplateExample example);
    int insert(PmsFeightTemplate record);
    int insertSelective(PmsFeightTemplate record);
    List<PmsFeightTemplate> selectByExample(PmsFeightTemplateExample example);
    int updateByExampleSelective(@Param("record") PmsFeightTemplate record, @Param("example") PmsFeightTemplateExample example);
    int updateByExample(@Param("record") PmsFeightTemplate record, @Param("example") PmsFeightTemplateExample example);
}
