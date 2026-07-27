package com.lgoshop.controller;

import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.UmsResourceCategory;
import com.lgoshop.service.UmsResourceCategoryService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台资源分类管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "UmsResourceCategoryController")
@Tag(name = "UmsResourceCategoryController", description = "后台资源分类管理")
@RequestMapping("/resourceCategory")
@LgoWebLog
public class UmsResourceCategoryController {
    @Autowired
    private UmsResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<UmsResourceCategory>> listAll() {
        List<UmsResourceCategory> resourceList = resourceCategoryService.listAll();
        return LgoResult.success(resourceList);
    }

    @ApiOperation("添加后台资源分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody UmsResourceCategory umsResourceCategory) {
        int count = resourceCategoryService.create(umsResourceCategory);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("修改后台资源分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long id,
                               @RequestBody UmsResourceCategory umsResourceCategory) {
        int count = resourceCategoryService.update(id, umsResourceCategory);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("根据ID删除后台资源分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@PathVariable Long id) {
        int count = resourceCategoryService.delete(id);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }
}
