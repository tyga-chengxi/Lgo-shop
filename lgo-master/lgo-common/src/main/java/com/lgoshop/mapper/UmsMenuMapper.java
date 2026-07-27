package com.lgoshop.mapper;

import com.lgoshop.model.UmsMenu;
import com.lgoshop.model.UmsMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {
    long countByExample(UmsMenuExample example);
    int deleteByExample(UmsMenuExample example);
    int insert(UmsMenu record);
    int insertSelective(UmsMenu record);
    List<UmsMenu> selectByExample(UmsMenuExample example);
    int updateByExampleSelective(@Param("record") UmsMenu record, @Param("example") UmsMenuExample example);
    int updateByExample(@Param("record") UmsMenu record, @Param("example") UmsMenuExample example);
}
