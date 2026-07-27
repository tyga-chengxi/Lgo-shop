package com.lgoshop.mapper;

import com.lgoshop.model.UmsMemberTag;
import com.lgoshop.model.UmsMemberTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsMemberTagMapper extends BaseMapper<UmsMemberTag> {
    long countByExample(UmsMemberTagExample example);
    int deleteByExample(UmsMemberTagExample example);
    int insert(UmsMemberTag record);
    int insertSelective(UmsMemberTag record);
    List<UmsMemberTag> selectByExample(UmsMemberTagExample example);
    int updateByExampleSelective(@Param("record") UmsMemberTag record, @Param("example") UmsMemberTagExample example);
    int updateByExample(@Param("record") UmsMemberTag record, @Param("example") UmsMemberTagExample example);
}
