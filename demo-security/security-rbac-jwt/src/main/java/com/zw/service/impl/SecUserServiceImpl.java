package com.zw.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zw.entity.SecUser;
import com.zw.mapper.SecUserMapper;
import com.zw.service.SecUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author weizhang
 * @since 2021-06-15
 */
@Service
public class SecUserServiceImpl extends ServiceImpl<SecUserMapper, SecUser> implements SecUserService {

    /**
     * @description 通过用户名/邮件/手机号 获取用户
     * @param usernameOrEmailOrPhone
     * @return com.zw.entity.SecUser
     */
    public SecUser getUser(String usernameOrEmailOrPhone){
        QueryWrapper<SecUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",usernameOrEmailOrPhone).or().eq("phone",usernameOrEmailOrPhone).or().eq("email",usernameOrEmailOrPhone).orderByAsc("id");
        return getOne(userQueryWrapper);
    }

    /**
     * @description 根据username 获取用户列表
     * @param usernames
     * @return java.util.List<com.zw.entity.SecUser>
     */
    public List<SecUser> getUsersByUserName(List<String> usernames){
        QueryWrapper<SecUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.in("username",usernames);
        return list(userQueryWrapper);
    }
}
