package com.zw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SecurityLogin1ApplicationTests {

	//第一段是用户名，这个无需质疑。
	//第二段看起来是一个时间戳，我们通过在线工具或者 Java 代码解析后发现，这是一个两周后的数据。
	//第三段这是使用 MD5 散列函数算出来的值，他的明文格式是 username + ":" + tokenExpiryTime + ":" + password + ":" + key。 最后的 key 是一个散列盐值，可以用来防治令牌被修改TokenBasedRememberMeServices
	//登录成功调用TokenBasedRememberMeServices#onLoginSuccess
	@Test
	public void contextLoads() {
		String str = "amF2YWJveToxNjI0NTIxMjM2OTI3OmU1MmI4ZDQwOGYxNDViYWM1MmZiY2NmNzA3ZTc4ODM5";
		String s = new String(Base64.getDecoder().decode(str));
		System.out.println("<<<<<<<<<<< "+ s);
	}

}
