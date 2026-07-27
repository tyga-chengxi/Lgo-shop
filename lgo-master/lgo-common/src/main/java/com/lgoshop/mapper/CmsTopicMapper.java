package com.lgoshop.mapper;

import com.lgoshop.model.CmsTopic;
import com.lgoshop.model.CmsTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface CmsTopicMapper extends BaseMapper<CmsTopic> {
    long countByExample(CmsTopicExample example);
    int deleteByExample(CmsTopicExample example);
    int insert(CmsTopic record);
    int insertSelective(CmsTopic record);
    List<CmsTopic> selectByExampleWithBLOBs(CmsTopicExample example);
    List<CmsTopic> selectByExample(CmsTopicExample example);
    int updateByExampleSelective(@Param("record") CmsTopic record, @Param("example") CmsTopicExample example);
    int updateByExampleWithBLOBs(@Param("record") CmsTopic record, @Param("example") CmsTopicExample example);
    int updateByExample(@Param("record") CmsTopic record, @Param("example") CmsTopicExample example);
    int updateByPrimaryKeyWithBLOBs(CmsTopic record);
}
