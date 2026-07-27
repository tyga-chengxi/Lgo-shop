package com.lgoshop.mapper;

import com.lgoshop.model.OmsCompanyAddress;
import com.lgoshop.model.OmsCompanyAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface OmsCompanyAddressMapper extends BaseMapper<OmsCompanyAddress> {
    long countByExample(OmsCompanyAddressExample example);
    int deleteByExample(OmsCompanyAddressExample example);
    int insert(OmsCompanyAddress record);
    int insertSelective(OmsCompanyAddress record);
    List<OmsCompanyAddress> selectByExample(OmsCompanyAddressExample example);
    int updateByExampleSelective(@Param("record") OmsCompanyAddress record, @Param("example") OmsCompanyAddressExample example);
    int updateByExample(@Param("record") OmsCompanyAddress record, @Param("example") OmsCompanyAddressExample example);
}
