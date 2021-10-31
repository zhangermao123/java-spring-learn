package com.zw;

import cn.hutool.crypto.SecureUtil;
import com.sun.crypto.provider.HmacSHA1;
import com.zw.entity.Person;
import com.zw.entity.request.LoginRequest;
import com.zw.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.MessageDigest;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class DemoApplicationTests {
	@Autowired
	PersonService personService;

	@Test
	public void contextLoads() {

	}
	@Test
	public void testSave() throws Exception{
		Person person = new Person();

		person.setUid("zhaosi");

		person.setSurname("赵");
//		person.setGivenName("四");

		person.setUserPassword(SecureUtil.sha1("123456"));

		// required field
		person.setPersonName("赵四");
//		person.setUidNumber("666");
//		person.setGidNumber("666");
//		person.setHomeDirectory("/home/zhaosi");
//		person.setLoginShell("/bin/bash");

		personService.save(person);
	}

	@Test
	public void testLogin(){
		personService.login(LoginRequest.builder().userName("zhaosi").passWord("123456").build());
	}

	@Test
	public void testFindAll(){

		personService.selectAll().forEach(person -> {
			System.out.println("person == " +person);
		});
	}

	@Test
	public void testDelete(){
		personService.delete(Person.builder().uid("zhaosi").build());
	}
}
