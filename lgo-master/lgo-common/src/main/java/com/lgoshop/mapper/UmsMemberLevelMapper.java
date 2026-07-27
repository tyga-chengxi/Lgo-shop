package com.lgoshop.mapper;

import com.lgoshop.model.UmsMemberLevel;
import com.lgoshop.model.UmsMemberLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsMemberLevelMapper extends BaseMapper<UmsMemberLevel> {
    long countByExample(UmsMemberLevelExample example);
    int deleteByExample(UmsMemberLevelExample example);
    int insert(UmsMemberLevel record);
    int insertSelective(UmsMemberLevel record);
    List<UmsMemberLevel> selectByExample(UmsMemberLevelExample example);
    int updateByExampleSelective(@Param("record") UmsMemberLevel record, @Param("example") UmsMemberLevelExample example);
    int updateByExample(@Param("record") UmsMemberLevel record, @Param("example") UmsMemberLevelExample example);
}
