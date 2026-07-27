package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.dto.PmsBrandParam;
import com.lgoshop.model.PmsBrand;
import com.lgoshop.service.PmsBrandService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoOperateLog;
import com.lgoshop.common.annotation.LgoWebLog;
import com.lgoshop.common.enums.BusinessType;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "PmsBrandController")
@Tag(name = "PmsBrandController", description = "商品品牌管理")
@RequestMapping("/brand")
@LgoWebLog
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<PmsBrand>> getList() {
        return LgoResult.success(brandService.listAllBrand());
    }

    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @LgoOperateLog(title = "品牌管理", businessType = BusinessType.INSERT)
    @ResponseBody
    public LgoResult create(@Validated @RequestBody PmsBrandParam pmsBrand) {
        LgoResult result;
        int count = brandService.createBrand(pmsBrand);
        if (count == 1) {
            result = LgoResult.success(count);
        } else {
            result = LgoResult.failed();
        }
        return result;
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @LgoOperateLog(title = "品牌管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public LgoResult update(@PathVariable("id") Long id,
                               @Validated @RequestBody PmsBrandParam pmsBrandParam) {
        LgoResult result;
        int count = brandService.updateBrand(id, pmsBrandParam);
        if (count == 1) {
            result = LgoResult.success(count);
        } else {
            result = LgoResult.failed();
        }
        return result;
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @LgoOperateLog(title = "品牌管理", businessType = BusinessType.DELETE)
    @ResponseBody
    public LgoResult delete(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            return LgoResult.success(null);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<PmsBrand>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                      @RequestParam(value = "showStatus",required = false) Integer showStatus,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        IPage<PmsBrand> brandList = brandService.listBrand(keyword,showStatus,pageNum, pageSize);
        return LgoResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<PmsBrand> getItem(@PathVariable("id") Long id) {
        return LgoResult.success(brandService.getBrand(id));
    }

    @ApiOperation(value = "批量删除品牌")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult deleteBatch(@RequestParam("ids") List<Long> ids) {
        int count = brandService.deleteBrand(ids);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation(value = "批量更新显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateShowStatus(@RequestParam("ids") List<Long> ids,
                                   @RequestParam("showStatus") Integer showStatus) {
        int count = brandService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation(value = "批量更新厂家制造商状态")
    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("factoryStatus") Integer factoryStatus) {
        int count = brandService.updateFactoryStatus(ids, factoryStatus);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }
}
