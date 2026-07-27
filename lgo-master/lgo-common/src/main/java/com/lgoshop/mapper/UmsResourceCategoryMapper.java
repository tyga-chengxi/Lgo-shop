package com.lgoshop.mapper;

import com.lgoshop.model.UmsResourceCategory;
import com.lgoshop.model.UmsResourceCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsResourceCategoryMapper extends BaseMapper<UmsResourceCategory> {
    long countByExample(UmsResourceCategoryExample example);
    int deleteByExample(UmsResourceCategoryExample example);
    int insert(UmsResourceCategory record);
    int insertSelective(UmsResourceCategory record);
    List<UmsResourceCategory> selectByExample(UmsResourceCategoryExample example);
    int updateByExampleSelective(@Param("record") UmsResourceCategory record, @Param("example") UmsResourceCategoryExample example);
    int updateByExample(@Param("record") UmsResourceCategory record, @Param("example") UmsResourceCategoryExample example);
}
