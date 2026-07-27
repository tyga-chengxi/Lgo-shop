package com.lgoshop.mapper;

import com.lgoshop.model.CmsMemberReport;
import com.lgoshop.model.CmsMemberReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface CmsMemberReportMapper extends BaseMapper<CmsMemberReport> {
    long countByExample(CmsMemberReportExample example);
    int deleteByExample(CmsMemberReportExample example);
    int insert(CmsMemberReport record);
    int insertSelective(CmsMemberReport record);
    List<CmsMemberReport> selectByExample(CmsMemberReportExample example);
    int updateByExampleSelective(@Param("record") CmsMemberReport record, @Param("example") CmsMemberReportExample example);
    int updateByExample(@Param("record") CmsMemberReport record, @Param("example") CmsMemberReportExample example);
}
