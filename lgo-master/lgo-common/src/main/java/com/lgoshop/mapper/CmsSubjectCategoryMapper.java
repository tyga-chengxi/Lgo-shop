package com.lgoshop.mapper;

import com.lgoshop.model.CmsSubjectCategory;
import com.lgoshop.model.CmsSubjectCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface CmsSubjectCategoryMapper extends BaseMapper<CmsSubjectCategory> {
    long countByExample(CmsSubjectCategoryExample example);
    int deleteByExample(CmsSubjectCategoryExample example);
    int insert(CmsSubjectCategory record);
    int insertSelective(CmsSubjectCategory record);
    List<CmsSubjectCategory> selectByExample(CmsSubjectCategoryExample example);
    int updateByExampleSelective(@Param("record") CmsSubjectCategory record, @Param("example") CmsSubjectCategoryExample example);
    int updateByExample(@Param("record") CmsSubjectCategory record, @Param("example") CmsSubjectCategoryExample example);
}
