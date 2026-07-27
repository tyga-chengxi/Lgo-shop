package com.lgoshop.mapper;

import com.lgoshop.model.PmsCommentReplay;
import com.lgoshop.model.PmsCommentReplayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface PmsCommentReplayMapper extends BaseMapper<PmsCommentReplay> {
    long countByExample(PmsCommentReplayExample example);
    int deleteByExample(PmsCommentReplayExample example);
    int insert(PmsCommentReplay record);
    int insertSelective(PmsCommentReplay record);
    List<PmsCommentReplay> selectByExample(PmsCommentReplayExample example);
    int updateByExampleSelective(@Param("record") PmsCommentReplay record, @Param("example") PmsCommentReplayExample example);
    int updateByExample(@Param("record") PmsCommentReplay record, @Param("example") PmsCommentReplayExample example);
}
