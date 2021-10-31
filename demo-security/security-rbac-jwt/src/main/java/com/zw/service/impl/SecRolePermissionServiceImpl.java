package com.zw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zw.entity.SecRolePermission;
import com.zw.mapper.SecRolePermissionMapper;
import com.zw.service.SecRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限关系表 服务实现类
 * </p>
 *
 * @author weizhang
 * @since 2021-06-15
 */
@Service
public class SecRolePermissionServiceImpl extends ServiceImpl<SecRolePermissionMapper, SecRolePermission> implements SecRolePermissionService {

    /**
     * @description 通过roleIds获取PermissionId
     * @param roleIds
     * @return java.util.Collection<java.lang.Long>
     */
    public Collection<Long> getPermissionId(Collection<Long> roleIds){
        QueryWrapper<SecRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id",roleIds);
        return list(queryWrapper).stream().map(SecRolePermission::getPermissionId).collect(Collectors.toList());
    }
}
