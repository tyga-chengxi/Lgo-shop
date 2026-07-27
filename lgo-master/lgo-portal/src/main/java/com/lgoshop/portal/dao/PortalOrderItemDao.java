package com.lgoshop.portal.dao;

import com.lgoshop.model.OmsOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单商品信息管理自定义Dao
 * Created by lgo-shop.
 */
public interface PortalOrderItemDao {
    /**
     * 批量插入
     */
    int insertList(@Param("list") List<OmsOrderItem> list);
}
