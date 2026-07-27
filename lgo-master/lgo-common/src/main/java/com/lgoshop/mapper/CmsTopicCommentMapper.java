package com.lgoshop.mapper;

import com.lgoshop.model.CmsTopicComment;
import com.lgoshop.model.CmsTopicCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface CmsTopicCommentMapper extends BaseMapper<CmsTopicComment> {
    long countByExample(CmsTopicCommentExample example);
    int deleteByExample(CmsTopicCommentExample example);
    int insert(CmsTopicComment record);
    int insertSelective(CmsTopicComment record);
    List<CmsTopicComment> selectByExample(CmsTopicCommentExample example);
    int updateByExampleSelective(@Param("record") CmsTopicComment record, @Param("example") CmsTopicCommentExample example);
    int updateByExample(@Param("record") CmsTopicComment record, @Param("example") CmsTopicCommentExample example);
}
