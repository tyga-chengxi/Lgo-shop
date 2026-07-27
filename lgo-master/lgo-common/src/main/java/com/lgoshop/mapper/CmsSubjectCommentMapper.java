package com.lgoshop.mapper;

import com.lgoshop.model.CmsSubjectComment;
import com.lgoshop.model.CmsSubjectCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface CmsSubjectCommentMapper extends BaseMapper<CmsSubjectComment> {
    long countByExample(CmsSubjectCommentExample example);
    int deleteByExample(CmsSubjectCommentExample example);
    int insert(CmsSubjectComment record);
    int insertSelective(CmsSubjectComment record);
    List<CmsSubjectComment> selectByExample(CmsSubjectCommentExample example);
    int updateByExampleSelective(@Param("record") CmsSubjectComment record, @Param("example") CmsSubjectCommentExample example);
    int updateByExample(@Param("record") CmsSubjectComment record, @Param("example") CmsSubjectCommentExample example);
}
