package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.SmsHomeBrand;
import com.lgoshop.service.SmsHomeBrandService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页品牌管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "SmsHomeBrandController")
@Tag(name = "SmsHomeBrandController", description = "首页品牌管理")
@RequestMapping("/home/brand")
@LgoWebLog
public class SmsHomeBrandController {
    @Autowired
    private SmsHomeBrandService homeBrandService;

    @ApiOperation("添加首页推荐品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody List<SmsHomeBrand> homeBrandList) {
        int count = homeBrandService.create(homeBrandList);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("修改推荐品牌排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateSort(@PathVariable Long id, Integer sort) {
        int count = homeBrandService.updateSort(id, sort);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("批量删除推荐品牌")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@RequestParam("ids") List<Long> ids) {
        int count = homeBrandService.delete(ids);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("批量修改推荐品牌状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = homeBrandService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("分页查询推荐品牌")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<SmsHomeBrand>> list(@RequestParam(value = "brandName", required = false) String brandName,
                                                       @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<SmsHomeBrand> homeBrandList = homeBrandService.list(brandName, recommendStatus, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(homeBrandList));
    }
}
