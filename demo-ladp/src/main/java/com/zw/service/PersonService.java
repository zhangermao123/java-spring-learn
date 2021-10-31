package com.zw.service;

import com.zw.entity.Person;
import com.zw.entity.request.LoginRequest;

import java.util.List;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.service
 * @descripation TODO
 * @date 2021-07-06
 */
public interface PersonService {

    Person login(LoginRequest request);

    List<Person> selectAll();

    void save(Person person);

    void delete(Person person);

}
