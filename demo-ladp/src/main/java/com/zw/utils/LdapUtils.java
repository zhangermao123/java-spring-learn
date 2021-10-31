package com.zw.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.utils
 * @descripation TODO
 * @date 2021-07-06
 */
public class LdapUtils {


    /**
     * 校验密码
     *
     * @param ldapPassword  ldap 加密密码
     * @param inputPassword 用户输入
     * @return boolean
     * @throws NoSuchAlgorithmException 加解密异常
     */
    public static boolean verify(String ldapPassword, String inputPassword) throws NoSuchAlgorithmException {
        String inputPasswordByte = SecureUtil.sha1(inputPassword);
        return ObjectUtil.equals(ldapPassword,inputPasswordByte);
    }

    public static String asciiToString(String value) {
        StringBuilder sbu = new StringBuilder();
        String[] chars = value.split(",");
        for (String aChar : chars) {
            sbu.append((char) Integer.parseInt(aChar));
        }
        return sbu.toString();
    }
}
