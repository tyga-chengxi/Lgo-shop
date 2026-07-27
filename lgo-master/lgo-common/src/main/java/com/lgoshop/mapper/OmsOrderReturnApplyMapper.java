package com.lgoshop.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.model.OmsOrderReturnApply;
import com.lgoshop.dto.*;
import com.lgoshop.model.OmsOrderReturnApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface OmsOrderReturnApplyMapper extends BaseMapper<OmsOrderReturnApply> {
    long countByExample(OmsOrderReturnApplyExample example);
    int deleteByExample(OmsOrderReturnApplyExample example);
    int insert(OmsOrderReturnApply record);
    int insertSelective(OmsOrderReturnApply record);
    List<OmsOrderReturnApply> selectByExample(OmsOrderReturnApplyExample example);
    int updateByExampleSelective(@Param("record") OmsOrderReturnApply record, @Param("example") OmsOrderReturnApplyExample example);
    int updateByExample(@Param("record") OmsOrderReturnApply record, @Param("example") OmsOrderReturnApplyExample example);
/**
* 查询申请列表
*/
List<OmsOrderReturnApply> getList(@Param("queryParam") OmsReturnApplyQueryParam queryParam);

IPage<OmsOrderReturnApply> getList(Page<?> page, @Param("queryParam") OmsReturnApplyQueryParam queryParam);

//* 获取申请详情
OmsOrderReturnApplyResult getDetail(@Param("id")Long id);
}
