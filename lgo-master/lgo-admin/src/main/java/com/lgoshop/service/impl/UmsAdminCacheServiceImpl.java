package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import cn.hutool.core.collection.CollUtil;
import com.lgoshop.common.service.RedisService;
import com.lgoshop.mapper.UmsAdminRoleRelationMapper;
import com.lgoshop.model.UmsAdmin;
import com.lgoshop.model.UmsAdminRoleRelation;
import com.lgoshop.model.UmsAdminRoleRelationExample;
import com.lgoshop.model.UmsResource;
import com.lgoshop.service.UmsAdminCacheService;
import com.lgoshop.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户缓存管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin admin = adminService.getItem(adminId);
        if (admin != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Long roleId) {
        List<UmsAdminRoleRelation> relationList = adminRoleRelationMapper.selectList(new LambdaQueryWrapper<>());
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        List<UmsAdminRoleRelation> relationList = adminRoleRelationMapper.selectList(new LambdaQueryWrapper<>());
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByResource(Long resourceId) {
        List<Long> adminIdList = adminRoleRelationMapper.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UmsResource> getResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        return (List<UmsResource>) redisService.get(key);
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key, resourceList, REDIS_EXPIRE);
    }
}
