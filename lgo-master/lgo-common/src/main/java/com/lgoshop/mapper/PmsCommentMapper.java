package com.lgoshop.mapper;

import com.lgoshop.model.PmsComment;
import com.lgoshop.model.PmsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsCommentMapper extends BaseMapper<PmsComment> {
    long countByExample(PmsCommentExample example);
    int deleteByExample(PmsCommentExample example);
    int insert(PmsComment record);
    int insertSelective(PmsComment record);
    List<PmsComment> selectByExampleWithBLOBs(PmsCommentExample example);
    List<PmsComment> selectByExample(PmsCommentExample example);
    int updateByExampleSelective(@Param("record") PmsComment record, @Param("example") PmsCommentExample example);
    int updateByExampleWithBLOBs(@Param("record") PmsComment record, @Param("example") PmsCommentExample example);
    int updateByExample(@Param("record") PmsComment record, @Param("example") PmsCommentExample example);
    int updateByPrimaryKeyWithBLOBs(PmsComment record);
}
