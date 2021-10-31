package com.zw;

import com.zw.dao.UserDao;
import com.zw.model.Role;
import com.zw.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityWithJpaApplicationTests {
	@Autowired
	UserDao userDao;

	@Test
	public void contextLoads() {
		User useDetail = new User();
		useDetail.setUsername("java");
		useDetail.setPassword("123");
		List<Role> rs1 = new ArrayList<>();
		Role r1 = new Role();
		r1.setName("ROLE_user");
		r1.setNameZh("用户");
		rs1.add(r1);
		useDetail.setRoles(rs1);
		userDao.save(useDetail);
	}

}
