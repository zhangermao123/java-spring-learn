package com.zw.repository;

import com.zw.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.naming.Name;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.repository
 * @descripation TODO
 * @date 2021-07-06
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Name> {

    /**
     * @description 通过uid找到person
     * @return Person
     */
    Person findByUid(String uid);
}
