package com.lgoshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.dto.UmsMenuNode;
import com.lgoshop.mapper.UmsMenuMapper;
import com.lgoshop.model.UmsMenu;
import com.lgoshop.model.UmsMenuExample;
import com.lgoshop.model.UmsRole;
import com.lgoshop.service.UmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台菜单管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    @Autowired
    private UmsMenuMapper menuMapper;

    @Override
    public int create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu);
        return menuMapper.insert(umsMenu);
    }

    private void updateLevel(UmsMenu umsMenu) {
        if (umsMenu.getParentId() == 0) {
            umsMenu.setLevel(0);
        } else {
            UmsMenu parentMenu = menuMapper.selectById(umsMenu.getParentId());
            if (parentMenu != null) {
                umsMenu.setLevel(parentMenu.getLevel() + 1);
            } else {
                umsMenu.setLevel(0);
            }
        }
    }

    @Override
    public int update(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        updateLevel(umsMenu);
        return menuMapper.updateById(umsMenu);
    }

    @Override
    public UmsMenu getItem(Long id) {
        return menuMapper.selectById(id);
    }

    @Override
    public int delete(Long id) {
        return menuMapper.deleteById(id);
    }

    @Override
    public IPage<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        Page<UmsMenu> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UmsMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsMenu::getParentId, parentId);
        return menuMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = menuMapper.selectList(new LambdaQueryWrapper<>());
        List<UmsMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setId(id);
        umsMenu.setHidden(hidden);
        return menuMapper.updateById(umsMenu);
    }

    private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
