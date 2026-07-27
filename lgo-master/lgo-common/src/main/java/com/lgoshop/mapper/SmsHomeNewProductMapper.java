package com.lgoshop.mapper;

import com.lgoshop.model.SmsHomeNewProduct;
import com.lgoshop.model.SmsHomeNewProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsHomeNewProductMapper extends BaseMapper<SmsHomeNewProduct> {
    long countByExample(SmsHomeNewProductExample example);
    int deleteByExample(SmsHomeNewProductExample example);
    int insert(SmsHomeNewProduct record);
    int insertSelective(SmsHomeNewProduct record);
    List<SmsHomeNewProduct> selectByExample(SmsHomeNewProductExample example);
    int updateByExampleSelective(@Param("record") SmsHomeNewProduct record, @Param("example") SmsHomeNewProductExample example);
    int updateByExample(@Param("record") SmsHomeNewProduct record, @Param("example") SmsHomeNewProductExample example);
}
