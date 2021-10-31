package com.zw.service;

import com.zw.dao.RoleDao;
import com.zw.dao.UserDao;
import com.zw.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.service
 * @descripation TODO
 * @date 2021-06-10
 */
@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(">>>>>>>>>>>>>>>>>>,username  :" +username);
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
