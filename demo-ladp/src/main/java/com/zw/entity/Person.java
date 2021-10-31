package com.zw.entity;

import lombok.*;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.io.Serializable;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.entity
 * @descripation TODO
 * @date 2021-07-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entry(base = "ou=people",objectClasses = "inetOrgPerson")
public class Person implements Serializable {

    private static final long serialVersionUID = 5563932397933303458L;

    @Id
    private Name id;
//
//    /**
//     * 用户id
//     */
//    private String uidNumber;

    /**
     * 用户名
     */
    @DnAttribute(value = "uid",index = 1)
    private String uid;

    /**
     * 姓名
     */
    @Attribute(name = "cn")
    private String personName;

    /**
     * 密码
     */
    private String userPassword;

//    /**
//     * 名字
//     */
//    private String givenName;

    /**
     * 姓氏
     */
    @Attribute(name = "sn")
    private String surname;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 职位
     */
    private String title;

//    /**
//     * 部门
//     */
//    private String departmentNumber;

//    /**
//     * 部门id
//     */
//    private String gidNumber;

//    /**
//     * 根目录
//     */
//    private String homeDirectory;

//    /**
//     * loginShell
//     */
//    private String loginShell;
}
