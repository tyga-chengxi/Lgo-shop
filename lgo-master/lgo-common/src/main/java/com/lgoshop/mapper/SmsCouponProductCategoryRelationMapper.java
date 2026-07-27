package com.lgoshop.mapper;

import com.lgoshop.model.SmsCouponProductCategoryRelation;
import com.lgoshop.model.SmsCouponProductCategoryRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface SmsCouponProductCategoryRelationMapper extends BaseMapper<SmsCouponProductCategoryRelation> {
    long countByExample(SmsCouponProductCategoryRelationExample example);
    int deleteByExample(SmsCouponProductCategoryRelationExample example);
    int insert(SmsCouponProductCategoryRelation record);
    int insertSelective(SmsCouponProductCategoryRelation record);
    List<SmsCouponProductCategoryRelation> selectByExample(SmsCouponProductCategoryRelationExample example);
    int updateByExampleSelective(@Param("record") SmsCouponProductCategoryRelation record, @Param("example") SmsCouponProductCategoryRelationExample example);
    int updateByExample(@Param("record") SmsCouponProductCategoryRelation record, @Param("example") SmsCouponProductCategoryRelationExample example);
/**
* 批量创建
*/
int insertList(@Param("list")List<SmsCouponProductCategoryRelation> productCategoryRelationList);
}
