package com.lgoshop.mapper;

import com.lgoshop.model.CmsSubject;
import com.lgoshop.model.CmsSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface CmsSubjectMapper extends BaseMapper<CmsSubject> {
    long countByExample(CmsSubjectExample example);
    int deleteByExample(CmsSubjectExample example);
    int insert(CmsSubject record);
    int insertSelective(CmsSubject record);
    List<CmsSubject> selectByExampleWithBLOBs(CmsSubjectExample example);
    List<CmsSubject> selectByExample(CmsSubjectExample example);
    int updateByExampleSelective(@Param("record") CmsSubject record, @Param("example") CmsSubjectExample example);
    int updateByExampleWithBLOBs(@Param("record") CmsSubject record, @Param("example") CmsSubjectExample example);
    int updateByExample(@Param("record") CmsSubject record, @Param("example") CmsSubjectExample example);
    int updateByPrimaryKeyWithBLOBs(CmsSubject record);
}
