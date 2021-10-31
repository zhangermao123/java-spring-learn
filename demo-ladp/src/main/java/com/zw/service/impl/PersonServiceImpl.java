package com.zw.service.impl;

import com.zw.entity.Person;
import com.zw.entity.request.LoginRequest;
import com.zw.exception.LdapException;
import com.zw.repository.PersonRepository;
import com.zw.service.PersonService;
import com.zw.utils.LdapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.service.impl
 * @descripation TODO
 * @date 2021-07-06
 */
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository repository;

    @Override
    public Person login(LoginRequest request) {
        log.info("[login]<<<<<<<<<<<<<<<<<请求 "+ request);
       Person person = repository.findByUid(request.getUserName());
        if(ObjectUtils.isEmpty(person)){
            throw new LdapException(402,"该用户不存在");
        }else if (ObjectUtils.isEmpty(person.getUserPassword())){
            throw new LdapException(403,"该用户密码有误");
        }else{
            person.setUserPassword(LdapUtils.asciiToString(person.getUserPassword()));
            try {
                if(!LdapUtils.verify(person.getUserPassword(),request.getPassWord())){
                    throw new LdapException(402,"密码登录错误");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new LdapException(402,e.getMessage());
            }
        }

        return person;
    }

    @Override
    public List<Person> selectAll() {
        Iterable<Person> persons = repository.findAll();
        List<Person> personList = new ArrayList<>();
        persons.forEach(person -> {
            person.setUserPassword(LdapUtils.asciiToString(person.getUserPassword()));
            personList.add(person);});
        log.info("[selectAll]<<<<<<<<<<<<<<<<<,搜索全部 "+ personList);
        return personList;
    }

    @Override
    public void save(Person person) {
        repository.save(person);
        log.info("[save]<<<<<<<<<<<<<<<<<,保存 "+ person);
    }

    @Override
    public void delete(Person person) {
        repository.delete(person);
        log.info("[delete]<<<<<<<<<<<<<<<<<,删除 "+ person);
    }

}
