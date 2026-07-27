package com.lgoshop.mapper;

import com.lgoshop.model.SmsCouponProductRelation;
import com.lgoshop.model.SmsCouponProductRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsCouponProductRelationMapper extends BaseMapper<SmsCouponProductRelation> {
    long countByExample(SmsCouponProductRelationExample example);
    int deleteByExample(SmsCouponProductRelationExample example);
    int insert(SmsCouponProductRelation record);
    int insertSelective(SmsCouponProductRelation record);
    List<SmsCouponProductRelation> selectByExample(SmsCouponProductRelationExample example);
    int updateByExampleSelective(@Param("record") SmsCouponProductRelation record, @Param("example") SmsCouponProductRelationExample example);
    int updateByExample(@Param("record") SmsCouponProductRelation record, @Param("example") SmsCouponProductRelationExample example);
/**
* 批量创建
*/
int insertList(@Param("list")List<SmsCouponProductRelation> productRelationList);
}
