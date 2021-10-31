package com.zw;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw
 * @descripation TODO
 * @date 2021-06-02
 */
public class PassWordJasyptTest extends DemoMailApplicationTests{
    @Autowired
    private StringEncryptor encryptor;

    /**
     * @description 将发送邮箱地址，密码，接受邮箱地址加密
     * @return void
     */
    @Test
    public void testJasyptPassWord(){
//        encryptor.setPassword("spring-mail-zhangwei-2021");
        String sendMailEN = encryptor.encrypt("1050380959@qq.com");
        String sendPsEN = encryptor.encrypt("bbkatojyzwzzbffi");
        String toMailEN = encryptor.encrypt("goodgood129@163.com");

        String sendMailDE = encryptor.decrypt(sendMailEN);
        String sendPsDE = encryptor.decrypt(sendPsEN);
        String toMailDE = encryptor.decrypt(toMailEN);

        System.out.println("=========================== 开始");
        System.out.println("加密sendMailEN: "+sendMailEN);
        System.out.println("解密sendMailDE: "+sendMailDE);

        System.out.println("加密sendPsEN: "+sendPsEN);
        System.out.println("解密sendPsDE: "+sendPsDE);


        System.out.println("加密toMailEN: "+toMailEN);
        System.out.println("解密toMailDE: "+toMailDE);
        System.out.println("=========================== 结束");

    }

    @Test
    public void getVWOptionSet(){
        String toMailDE = encryptor.decrypt("HIBCzY5+QtcJJ7SaKVUeijAVPGjKz/tg76TzNoSPe8k=");

        System.out.println("=========================== 开始");
        System.out.println("=========================== toMailDE ："+ toMailDE);
        System.out.println("=========================== 结束");
    }
}
