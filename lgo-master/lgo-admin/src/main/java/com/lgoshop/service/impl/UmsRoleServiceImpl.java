package com.lgoshop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.UmsRoleMapper;
import com.lgoshop.mapper.UmsRoleMenuRelationMapper;
import com.lgoshop.mapper.UmsRoleResourceRelationMapper;
import com.lgoshop.model.UmsMenu;
import com.lgoshop.model.UmsResource;
import com.lgoshop.model.UmsRole;
import com.lgoshop.model.UmsRoleExample;
import com.lgoshop.model.UmsRoleMenuRelation;
import com.lgoshop.model.UmsRoleMenuRelationExample;
import com.lgoshop.model.UmsRoleResourceRelation;
import com.lgoshop.model.UmsRoleResourceRelationExample;
import com.lgoshop.service.UmsAdminCacheService;
import com.lgoshop.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台角色管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Autowired
    private UmsRoleMapper roleMapper;
    @Autowired
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;
    @Autowired
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Override
    public int create(UmsRole role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }

    @Override
    public int update(Long id, UmsRole role) {
        role.setId(id);
        return roleMapper.updateById(role);
    }

    @Override
    public int delete(List<Long> ids) {
        int count = roleMapper.delete(new LambdaQueryWrapper<UmsRole>().in(UmsRole::getId, ids));
        adminCacheService.delResourceListByRoleIds(ids);
        return count;
    }

    @Override
    public List<UmsRole> list() {
        return roleMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public IPage<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<UmsRole> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UmsRole> queryWrapper = new LambdaQueryWrapper<>();
        if (!StrUtil.isEmpty(keyword)) {
            queryWrapper.like(UmsRole::getName, keyword);
        }
        return roleMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleMapper.getMenuList(adminId);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return roleMapper.getMenuListByRoleId(roleId);
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return roleMapper.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        roleMenuRelationMapper.delete(new LambdaQueryWrapper<UmsRoleMenuRelation>());
        for (Long menuId : menuIds) {
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            roleMenuRelationMapper.insert(relation);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        roleResourceRelationMapper.delete(new LambdaQueryWrapper<UmsRoleResourceRelation>());
        for (Long resourceId : resourceIds) {
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            roleResourceRelationMapper.insert(relation);
        }
        adminCacheService.delResourceListByRole(roleId);
        return resourceIds.size();
    }
}
