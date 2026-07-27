package com.lgoshop.mapper;

import com.lgoshop.model.UmsIntegrationConsumeSetting;
import com.lgoshop.model.UmsIntegrationConsumeSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsIntegrationConsumeSettingMapper extends BaseMapper<UmsIntegrationConsumeSetting> {
    long countByExample(UmsIntegrationConsumeSettingExample example);
    int deleteByExample(UmsIntegrationConsumeSettingExample example);
    int insert(UmsIntegrationConsumeSetting record);
    int insertSelective(UmsIntegrationConsumeSetting record);
    List<UmsIntegrationConsumeSetting> selectByExample(UmsIntegrationConsumeSettingExample example);
    int updateByExampleSelective(@Param("record") UmsIntegrationConsumeSetting record, @Param("example") UmsIntegrationConsumeSettingExample example);
    int updateByExample(@Param("record") UmsIntegrationConsumeSetting record, @Param("example") UmsIntegrationConsumeSettingExample example);
}
