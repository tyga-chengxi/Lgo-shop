package com.lgoshop.mapper;

import com.lgoshop.model.UmsMember;
import com.lgoshop.model.UmsMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsMemberMapper extends BaseMapper<UmsMember> {
    long countByExample(UmsMemberExample example);
    int deleteByExample(UmsMemberExample example);
    int insert(UmsMember record);
    int insertSelective(UmsMember record);
    List<UmsMember> selectByExample(UmsMemberExample example);
    int updateByExampleSelective(@Param("record") UmsMember record, @Param("example") UmsMemberExample example);
    int updateByExample(@Param("record") UmsMember record, @Param("example") UmsMemberExample example);
}
