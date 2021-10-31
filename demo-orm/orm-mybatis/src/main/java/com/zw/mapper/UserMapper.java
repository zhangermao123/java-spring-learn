package com.zw.mapper;

import com.zw.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.mapper
 * @descripation TODO
 * @date 2021-05-28
 */
@Component
public interface UserMapper {
    //注解搜索所有
    @Select("SELECT * FROM orm_user")
    List<User> selectAllUser();

    //注解搜索一个，通过Param入参
    @Select("SELECT * FROM orm_user where id = #{id}")
    User selectUserById(@Param("id") Long id);

    //xml引用 saveUser 同xml一直，并且xml引用mapper
    int saveUser(@Param("user") User user);

    //xml引用
    int deleteById(@Param("id") Long id);
}
