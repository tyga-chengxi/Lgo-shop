package com.lgoshop.mapper;

import com.lgoshop.model.SmsCoupon;
import com.lgoshop.dto.*;
import com.lgoshop.model.SmsCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsCouponMapper extends BaseMapper<SmsCoupon> {
    long countByExample(SmsCouponExample example);
    int deleteByExample(SmsCouponExample example);
    int insert(SmsCoupon record);
    int insertSelective(SmsCoupon record);
    List<SmsCoupon> selectByExample(SmsCouponExample example);
    int updateByExampleSelective(@Param("record") SmsCoupon record, @Param("example") SmsCouponExample example);
    int updateByPrimaryKey(SmsCoupon record);
    int updateByExample(@Param("record") SmsCoupon record, @Param("example") SmsCouponExample example);
/**
* 获取优惠券详情包括绑定关系
*/
SmsCouponParam getItem(@Param("id") Long id);
}
