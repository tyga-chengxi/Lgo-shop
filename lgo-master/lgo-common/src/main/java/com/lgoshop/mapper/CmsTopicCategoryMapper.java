package com.lgoshop.mapper;

import com.lgoshop.model.CmsTopicCategory;
import com.lgoshop.model.CmsTopicCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface CmsTopicCategoryMapper extends BaseMapper<CmsTopicCategory> {
    long countByExample(CmsTopicCategoryExample example);
    int deleteByExample(CmsTopicCategoryExample example);
    int insert(CmsTopicCategory record);
    int insertSelective(CmsTopicCategory record);
    List<CmsTopicCategory> selectByExample(CmsTopicCategoryExample example);
    int updateByExampleSelective(@Param("record") CmsTopicCategory record, @Param("example") CmsTopicCategoryExample example);
    int updateByExample(@Param("record") CmsTopicCategory record, @Param("example") CmsTopicCategoryExample example);
}
