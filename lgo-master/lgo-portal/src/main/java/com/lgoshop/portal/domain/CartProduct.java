package com.lgoshop.portal.domain;

import com.lgoshop.model.PmsProduct;
import com.lgoshop.model.PmsProductAttribute;
import com.lgoshop.model.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 购物车中带商品属性和SKU库存的商品对象
 * Created by lgo-shop.
 */
@Getter
@Setter
public class CartProduct extends PmsProduct {
    @ApiModelProperty("商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
    @ApiModelProperty("商品SKU库存列表")
    private List<PmsSkuStock> skuStockList;
}
