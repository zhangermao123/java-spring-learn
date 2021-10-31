package com.zw.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zw.entity.SecRole;
import com.zw.entity.SecUser;
import com.zw.mapper.SecRoleMapper;
import com.zw.service.SecRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author weizhang
 * @since 2021-06-15
 */
@Service
public class SecRoleServiceImpl extends ServiceImpl<SecRoleMapper, SecRole> implements SecRoleService {
    /**
     * @description 通过roleIds获取
     * @param roleIds
     * @return java.util.Collection<com.zw.entity.SecRole>
     */
    public Collection<SecRole> getList(Collection<Long> roleIds) {
        return listByIds(roleIds);
    }
}
