package com.lgoshop.mapper;

import com.lgoshop.model.UmsMemberReceiveAddress;
import com.lgoshop.model.UmsMemberReceiveAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface UmsMemberReceiveAddressMapper extends BaseMapper<UmsMemberReceiveAddress> {
    long countByExample(UmsMemberReceiveAddressExample example);
    int deleteByExample(UmsMemberReceiveAddressExample example);
    int insert(UmsMemberReceiveAddress record);
    int insertSelective(UmsMemberReceiveAddress record);
    List<UmsMemberReceiveAddress> selectByExample(UmsMemberReceiveAddressExample example);
    int updateByExampleSelective(@Param("record") UmsMemberReceiveAddress record, @Param("example") UmsMemberReceiveAddressExample example);
    int updateByExample(@Param("record") UmsMemberReceiveAddress record, @Param("example") UmsMemberReceiveAddressExample example);
}
