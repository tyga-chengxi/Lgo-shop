package com.lgoshop.controller;

import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.PmsSkuStock;
import com.lgoshop.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品SKU库存管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "PmsSkuStockController")
@Tag(name = "PmsSkuStockController", description = "sku商品库存管理")
@RequestMapping("/sku")
@LgoWebLog
public class PmsSkuStockController {
    @Autowired
    private PmsSkuStockService skuStockService;

    @ApiOperation("根据商品ID及sku编码模糊搜索sku库存")
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<PmsSkuStock>> getList(@PathVariable Long pid, @RequestParam(value = "keyword",required = false) String keyword) {
        List<PmsSkuStock> skuStockList = skuStockService.getList(pid, keyword);
        return LgoResult.success(skuStockList);
    }
    @ApiOperation("根据商品ID批量更新sku库存信息")
    @RequestMapping(value ="/update/{pid}",method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long pid,@RequestBody List<PmsSkuStock> skuStockList){
        int count = skuStockService.update(pid,skuStockList);
        if(count>0){
            return LgoResult.success(count);
        }else{
            return LgoResult.failed();
        }
    }
}
