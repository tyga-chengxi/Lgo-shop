package com.lgoshop.mapper;

import com.lgoshop.model.OmsOrderSetting;
import com.lgoshop.model.OmsOrderSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface OmsOrderSettingMapper extends BaseMapper<OmsOrderSetting> {
    long countByExample(OmsOrderSettingExample example);
    int deleteByExample(OmsOrderSettingExample example);
    int insert(OmsOrderSetting record);
    int insertSelective(OmsOrderSetting record);
    List<OmsOrderSetting> selectByExample(OmsOrderSettingExample example);
    int updateByExampleSelective(@Param("record") OmsOrderSetting record, @Param("example") OmsOrderSettingExample example);
    int updateByPrimaryKey(OmsOrderSetting record);
    int updateByExample(@Param("record") OmsOrderSetting record, @Param("example") OmsOrderSettingExample example);
}
