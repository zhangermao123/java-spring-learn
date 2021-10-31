package com.zw.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zw.entity.SecPermission;
import com.zw.entity.SecRole;
import com.zw.entity.SecUser;
import com.zw.entity.vo.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.service.impl
 * @descripation TODO
 * @date 2021-06-16
 */
@Service
@Slf4j
public class CustomUserDetailsService
        implements UserDetailsService {
    @Autowired
    SecUserServiceImpl userService;

    @Autowired
    SecUserRoleServiceImpl userRoleService;

    @Autowired
    SecRoleServiceImpl roleService;

    @Autowired
    SecPermissionServiceImpl permissionService;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {

        //通过usernameOrEmailOrPhone 获取SecUser
        SecUser secUser = userService.getUser(usernameOrEmailOrPhone);
        log.info("[loadUserByUsername] 通过usernameOrEmailOrPhone 获取secUser>>>>>>>: "+ usernameOrEmailOrPhone + " : "+ secUser);
        //通过user获取roles 的list id
        if(ObjectUtil.isEmpty(secUser) || ObjectUtil.isEmpty(secUser.getId())){
            return null;
        }
        //通过SecUser获取角色id列表
        List<Long> roleIds = userRoleService.getRoleIds(secUser);
        log.info("[loadUserByUsername] 通过secUser获取的角色id列表>>>>>>>: "+ secUser + " : "+ roleIds);
        if(ObjectUtil.isEmpty(roleIds)){
            return null;
        }
        //通过角色id列表获取secRoles是
        Collection<SecRole> secRoles = roleService.getList(roleIds);
        log.info("[loadUserByUsername] 通过角色id列表获取的secRoles>>>>>>>: "+ roleIds + " : "+ secRoles);
        if(ObjectUtil.isEmpty(secRoles)){
            return null;
        }
        //通过角色id列表获取权限列表
        Collection<SecPermission> secPermissions = permissionService.getList(roleIds);
        log.info("[loadUserByUsername] 通过角色id列表获取的secPermissions>>>>>>>: "+ roleIds + " : "+ secPermissions);
        if(ObjectUtil.isEmpty(secPermissions)){
            return null;
        }
        return UserPrincipal.getInstance(secUser, secRoles,secPermissions);
    }
}
