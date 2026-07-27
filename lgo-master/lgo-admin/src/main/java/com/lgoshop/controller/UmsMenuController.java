package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.dto.UmsMenuNode;
import com.lgoshop.model.UmsMenu;
import com.lgoshop.service.UmsMenuService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台菜单管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "UmsMenuController")
@Tag(name = "UmsMenuController", description = "后台菜单管理")
@RequestMapping("/menu")
@LgoWebLog
public class UmsMenuController {

    @Autowired
    private UmsMenuService menuService;

    @ApiOperation("添加后台菜单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody UmsMenu umsMenu) {
        int count = menuService.create(umsMenu);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("修改后台菜单")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long id,
                               @RequestBody UmsMenu umsMenu) {
        int count = menuService.update(id, umsMenu);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("根据ID获取菜单详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<UmsMenu> getItem(@PathVariable Long id) {
        UmsMenu umsMenu = menuService.getItem(id);
        return LgoResult.success(umsMenu);
    }

    @ApiOperation("根据ID删除后台菜单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@PathVariable Long id) {
        int count = menuService.delete(id);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("根据上级菜单ID分页查询菜单")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<UmsMenu>> list(@PathVariable Long parentId,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<UmsMenu> menuList = menuService.list(parentId, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(menuList));
    }

    @ApiOperation("树形结构返回所有菜单列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<UmsMenuNode>> treeList() {
        List<UmsMenuNode> list = menuService.treeList();
        return LgoResult.success(list);
    }

    @ApiOperation("根据ID修改菜单显示状态")
    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
        int count = menuService.updateHidden(id, hidden);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }
}
