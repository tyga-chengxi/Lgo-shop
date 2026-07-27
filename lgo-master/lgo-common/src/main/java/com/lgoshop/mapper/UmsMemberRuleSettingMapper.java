package com.lgoshop.mapper;

import com.lgoshop.model.UmsMemberRuleSetting;
import com.lgoshop.model.UmsMemberRuleSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsMemberRuleSettingMapper extends BaseMapper<UmsMemberRuleSetting> {
    long countByExample(UmsMemberRuleSettingExample example);
    int deleteByExample(UmsMemberRuleSettingExample example);
    int insert(UmsMemberRuleSetting record);
    int insertSelective(UmsMemberRuleSetting record);
    List<UmsMemberRuleSetting> selectByExample(UmsMemberRuleSettingExample example);
    int updateByExampleSelective(@Param("record") UmsMemberRuleSetting record, @Param("example") UmsMemberRuleSettingExample example);
    int updateByExample(@Param("record") UmsMemberRuleSetting record, @Param("example") UmsMemberRuleSettingExample example);
}
