package com.lgoshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lgoshop.model.CmsHelp;
import com.lgoshop.model.CmsHelpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface CmsHelpMapper extends BaseMapper {
    long countByExample(CmsHelpExample example);
    int deleteByExample(CmsHelpExample example);
    int insert(CmsHelp record);
    int insertSelective(CmsHelp record);
    List<CmsHelp> selectByExampleWithBLOBs(CmsHelpExample example);
    List<CmsHelp> selectByExample(CmsHelpExample example);
    int updateByExampleSelective(@Param("record") CmsHelp record, @Param("example") CmsHelpExample example);
    int updateByExampleWithBLOBs(@Param("record") CmsHelp record, @Param("example") CmsHelpExample example);
    int updateByExample(@Param("record") CmsHelp record, @Param("example") CmsHelpExample example);
    int updateByPrimaryKeyWithBLOBs(CmsHelp record);
}
