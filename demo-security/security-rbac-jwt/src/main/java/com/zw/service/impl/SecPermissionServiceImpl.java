package com.zw.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.zw.entity.SecPermission;
import com.zw.mapper.SecPermissionMapper;
import com.zw.service.SecPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author weizhang
 * @since 2021-06-15
 */
@Service
public class SecPermissionServiceImpl extends ServiceImpl<SecPermissionMapper, SecPermission> implements SecPermissionService {
    @Autowired
    SecRolePermissionServiceImpl rolePermissionService;

    /**
     * @description 测试使用
     * @param roleIds
     * @return java.util.Collection<com.zw.entity.SecPermission>
     */
    public Collection<SecPermission> getList(Collection<Long> roleIds){
        Collection<Long> permissionId = rolePermissionService.getPermissionId(roleIds);
        if(ObjectUtil.isEmpty(permissionId)){
            return null;
        }
        return listByIds(permissionId);
    }
}
