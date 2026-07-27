package com.lgoshop.mapper;

import com.lgoshop.model.OmsCartItem;
import com.lgoshop.model.OmsCartItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface OmsCartItemMapper extends BaseMapper<OmsCartItem> {
    long countByExample(OmsCartItemExample example);
    int deleteByExample(OmsCartItemExample example);
    int insert(OmsCartItem record);
    int insertSelective(OmsCartItem record);
    List<OmsCartItem> selectByExample(OmsCartItemExample example);
    int updateByExampleSelective(@Param("record") OmsCartItem record, @Param("example") OmsCartItemExample example);
    int updateByExample(@Param("record") OmsCartItem record, @Param("example") OmsCartItemExample example);
}
