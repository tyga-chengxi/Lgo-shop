package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.*;
import com.lgoshop.service.UmsRoleService;
import com.lgoshop.common.annotation.LgoOperateLog;
import com.lgoshop.common.annotation.LgoWebLog;
import com.lgoshop.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户角色管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "UmsRoleController")
@Tag(name = "UmsRoleController", description = "后台用户角色管理")
@LgoWebLog
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    private UmsRoleService roleService;

    @ApiOperation("添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @LgoOperateLog(title = "角色管理", businessType = BusinessType.INSERT)
    @ResponseBody
    public LgoResult create(@RequestBody UmsRole role) {
        int count = roleService.create(role);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @LgoOperateLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public LgoResult update(@PathVariable Long id, @RequestBody UmsRole role) {
        int count = roleService.update(id, role);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("批量删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @LgoOperateLog(title = "角色管理", businessType = BusinessType.DELETE)
    @ResponseBody
    public LgoResult delete(@RequestParam("ids") List<Long> ids) {
        int count = roleService.delete(ids);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<UmsRole>> listAll() {
        List<UmsRole> roleList = roleService.list();
        return LgoResult.success(roleList);
    }

    @ApiOperation("根据角色名称分页获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<UmsRole> roleList = roleService.list(keyword, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(roleList));
    }

    @ApiOperation("修改角色状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        UmsRole umsRole = new UmsRole();
        umsRole.setStatus(status);
        int count = roleService.update(id, umsRole);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("根据角色ID获取菜单列表")
    @RequestMapping(value = "/listMenu/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<UmsMenu>> listMenu(@PathVariable Long roleId) {
        List<UmsMenu> roleList = roleService.listMenu(roleId);
        return LgoResult.success(roleList);
    }

    @ApiOperation("获取角色相关资源")
    @RequestMapping(value = "/listResource/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<UmsResource>> listResource(@PathVariable Long roleId) {
        List<UmsResource> roleList = roleService.listResource(roleId);
        return LgoResult.success(roleList);
    }

    @ApiOperation("给角色分配菜单")
    @RequestMapping(value = "/allocMenu", method = RequestMethod.POST)
    @LgoOperateLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public LgoResult allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        return LgoResult.success(count);
    }

    @ApiOperation("给角色分配资源")
    @RequestMapping(value = "/allocResource", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        int count = roleService.allocResource(roleId, resourceIds);
        return LgoResult.success(count);
    }

}
