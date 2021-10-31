package com.zw.dao;

import com.zw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.dao
 * @descripation TODO
 * @date 2021-06-10
 */
public interface UserDao extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
