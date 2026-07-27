package com.lgoshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lgoshop.model.CmsHelpCategory;
import com.lgoshop.model.CmsHelpCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface CmsHelpCategoryMapper extends BaseMapper {
    long countByExample(CmsHelpCategoryExample example);
    int deleteByExample(CmsHelpCategoryExample example);
    int insert(CmsHelpCategory record);
    int insertSelective(CmsHelpCategory record);
    List<CmsHelpCategory> selectByExample(CmsHelpCategoryExample example);
    int updateByExampleSelective(@Param("record") CmsHelpCategory record, @Param("example") CmsHelpCategoryExample example);
    int updateByExample(@Param("record") CmsHelpCategory record, @Param("example") CmsHelpCategoryExample example);
}
