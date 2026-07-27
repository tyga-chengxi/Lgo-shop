package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.bo.AdminUserDetails;
import com.lgoshop.common.exception.Asserts;
import com.lgoshop.common.util.RequestUtil;
import com.lgoshop.dto.UmsAdminParam;
import com.lgoshop.dto.UpdateAdminPasswordParam;
import com.lgoshop.mapper.UmsAdminLoginLogMapper;
import com.lgoshop.mapper.UmsAdminMapper;
import com.lgoshop.mapper.UmsAdminRoleRelationMapper;
import com.lgoshop.model.UmsAdmin;
import com.lgoshop.model.UmsAdminLoginLog;
import com.lgoshop.model.UmsAdminRoleRelation;
import com.lgoshop.model.UmsAdminExample;
import com.lgoshop.model.UmsAdminRoleRelationExample;
import com.lgoshop.model.UmsResource;
import com.lgoshop.model.UmsRole;
import com.lgoshop.security.util.JwtTokenUtil;
import com.lgoshop.security.util.SpringUtil;
import com.lgoshop.service.UmsAdminCacheService;
import com.lgoshop.service.UmsAdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台用户管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin admin = getCacheService().getAdmin(username);
        if (admin != null) return admin;
        List<UmsAdmin> adminList = adminMapper.selectList(new LambdaQueryWrapper<UmsAdmin>().eq(UmsAdmin::getUsername, username));
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            getCacheService().setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        List<UmsAdmin> umsAdminList = adminMapper.selectList(new LambdaQueryWrapper<UmsAdmin>().eq(UmsAdmin::getUsername, umsAdmin.getUsername()));
        if (umsAdminList.size() > 0) {
            return null;
        }
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                Asserts.fail("密码不正确");
            }
            if (!userDetails.isEnabled()) {
                Asserts.fail("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin == null) return;
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        loginLogMapper.insert(loginLog);
    }

    private void updateLoginTimeByUsername(String username) {
        UmsAdmin record = new UmsAdmin();
        record.setLoginTime(new Date());
        adminMapper.update(record, new LambdaUpdateWrapper<>());
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return adminMapper.selectById(id);
    }

    @Override
    public IPage<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<UmsAdmin> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UmsAdmin> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.and(w -> w
                    .like(UmsAdmin::getUsername, keyword)
                    .like(UmsAdmin::getNickName, keyword));
        }
        return adminMapper.selectPage(page, wrapper);
    }

    @Override
    public int update(Long id, UmsAdmin admin) {
        admin.setId(id);
        UmsAdmin rawAdmin = adminMapper.selectById(id);
        if (rawAdmin.getPassword().equals(admin.getPassword())) {
            admin.setPassword(null);
        } else {
            if (StrUtil.isEmpty(admin.getPassword())) {
                admin.setPassword(null);
            } else {
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        int count = adminMapper.updateById(admin);
        getCacheService().delAdmin(id);
        return count;
    }

    @Override
    public int delete(Long id) {
        getCacheService().delAdmin(id);
        int count = adminMapper.deleteById(id);
        getCacheService().delResourceList(id);
        return count;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        adminRoleRelationMapper.delete(new LambdaQueryWrapper<UmsAdminRoleRelation>());
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationMapper.insertList(list);
        }
        getCacheService().delResourceList(adminId);
        return count;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationMapper.getRoleList(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        List<UmsResource> resourceList = getCacheService().getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = adminRoleRelationMapper.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            getCacheService().setResourceList(adminId, resourceList);
        }
        return resourceList;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        if (StrUtil.isEmpty(param.getUsername())
                || StrUtil.isEmpty(param.getOldPassword())
                || StrUtil.isEmpty(param.getNewPassword())) {
            return -1;
        }
        List<UmsAdmin> adminList = adminMapper.selectList(new LambdaQueryWrapper<UmsAdmin>().eq(UmsAdmin::getUsername, param.getUsername()));
        if (CollUtil.isEmpty(adminList)) {
            return -2;
        }
        UmsAdmin umsAdmin = adminList.get(0);
        if (!passwordEncoder.matches(param.getOldPassword(), umsAdmin.getPassword())) {
            return -3;
        }
        umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        adminMapper.updateById(umsAdmin);
        getCacheService().delAdmin(umsAdmin.getId());
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public UmsAdminCacheService getCacheService() {
        return SpringUtil.getBean(UmsAdminCacheService.class);
    }

    @Override
    public void logout(String username) {
        UmsAdmin admin = getCacheService().getAdmin(username);
        getCacheService().delAdmin(admin.getId());
        getCacheService().delResourceList(admin.getId());
    }
}
