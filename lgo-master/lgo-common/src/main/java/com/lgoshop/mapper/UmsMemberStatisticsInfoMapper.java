package com.lgoshop.mapper;

import com.lgoshop.model.UmsMemberStatisticsInfo;
import com.lgoshop.model.UmsMemberStatisticsInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsMemberStatisticsInfoMapper extends BaseMapper<UmsMemberStatisticsInfo> {
    long countByExample(UmsMemberStatisticsInfoExample example);
    int deleteByExample(UmsMemberStatisticsInfoExample example);
    int insert(UmsMemberStatisticsInfo record);
    int insertSelective(UmsMemberStatisticsInfo record);
    List<UmsMemberStatisticsInfo> selectByExample(UmsMemberStatisticsInfoExample example);
    int updateByExampleSelective(@Param("record") UmsMemberStatisticsInfo record, @Param("example") UmsMemberStatisticsInfoExample example);
    int updateByExample(@Param("record") UmsMemberStatisticsInfo record, @Param("example") UmsMemberStatisticsInfoExample example);
}
