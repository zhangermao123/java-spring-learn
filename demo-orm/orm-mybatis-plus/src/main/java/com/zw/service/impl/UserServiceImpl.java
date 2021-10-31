package com.zw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.mapper.UserMapper;
import com.zw.pojo.User;
import com.zw.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.service.impl
 * @descripation TODO
 * @date 2021-06-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
