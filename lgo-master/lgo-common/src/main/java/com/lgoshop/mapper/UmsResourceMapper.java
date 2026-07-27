package com.lgoshop.mapper;

import com.lgoshop.model.UmsResource;
import com.lgoshop.model.UmsResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsResourceMapper extends BaseMapper<UmsResource> {
    long countByExample(UmsResourceExample example);
    int deleteByExample(UmsResourceExample example);
    int insert(UmsResource record);
    int insertSelective(UmsResource record);
    List<UmsResource> selectByExample(UmsResourceExample example);
    int updateByExampleSelective(@Param("record") UmsResource record, @Param("example") UmsResourceExample example);
    int updateByExample(@Param("record") UmsResource record, @Param("example") UmsResourceExample example);
}
