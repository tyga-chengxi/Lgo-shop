package com.lgoshop.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.model.OmsOrder;
import com.lgoshop.dto.*;
import com.lgoshop.model.OmsOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {
    long countByExample(OmsOrderExample example);
    int deleteByExample(OmsOrderExample example);
    int insert(OmsOrder record);
    int insertSelective(OmsOrder record);
    List<OmsOrder> selectByExample(OmsOrderExample example);
    int updateByExampleSelective(@Param("record") OmsOrder record, @Param("example") OmsOrderExample example);
    int updateByExample(@Param("record") OmsOrder record, @Param("example") OmsOrderExample example);
    IPage<OmsOrder> selectOrderPage(Page<OmsOrder> page, @Param("queryParam") OmsOrderQueryParam queryParam);
/**
* 条件查询订单
*/
List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);
//* 批量发货
int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);
//* 获取订单详情
OmsOrderDetail getDetail(@Param("id") Long id);
}
