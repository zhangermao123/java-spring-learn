package com.zw.pojo.result;

import lombok.Data;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.pojo.response
 * @descripation TODO
 * @date 2021-05-27
 */
@Data
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }
}
