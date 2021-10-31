package com.zw.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zw.entity.SecUser;
import com.zw.entity.SecUserRole;
import com.zw.mapper.SecUserRoleMapper;
import com.zw.service.SecUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author weizhang
 * @since 2021-06-15
 */
@Service
public class SecUserRoleServiceImpl extends ServiceImpl<SecUserRoleMapper, SecUserRole> implements SecUserRoleService {
    /**
     * @description 通过用户获取角色id列表
     * @param secUser
     * @return java.util.List<java.lang.Long>
     */
    public List<Long> getRoleIds(SecUser secUser){
        QueryWrapper<SecUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",secUser.getId());
        return list(queryWrapper).stream().map(SecUserRole::getRoleId).collect(Collectors.toList());
    }
}
