package com.zw;

import cn.hutool.core.util.StrUtil;
import com.zw.entity.SecPermission;
import com.zw.entity.SecRole;
import com.zw.entity.SecUser;
import com.zw.service.impl.*;
import com.zw.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {
	@Autowired
	SecUserServiceImpl userService;

	@Autowired
	SecUserRoleServiceImpl userRoleService;

	@Autowired
	SecRoleServiceImpl roleService;

	@Autowired
	SecRolePermissionServiceImpl rolePermissionService;

	@Autowired
	SecPermissionServiceImpl permissionService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private StringEncryptor encryptor;

	@Test
	public void contextLoads() {

	}

	//测试mybatis
	@Test
	public void testMyBatis(){
		SecUser secUser = userService.getUser("user");
		System.out.println("[testMyBatis secUser]:>>>>>>>>>" +  secUser);

		List<Long> roleIds = userRoleService.getRoleIds(secUser);
		System.out.println("[testMyBatis roleIds]:>>>>>>>>>" + roleIds );

		Collection<SecRole> roleslist =  roleService.getList(roleIds);
		System.out.println("[testMyBatis roleslist]:>>>>>>>>>" + roleslist);

		Collection<Long> permissionIds =  rolePermissionService.getPermissionId(roleIds);
		System.out.println("[testMyBatis permissionIds]:>>>>>>>>>" + permissionIds);

		Collection<SecPermission> permissionsList =  permissionService.getList(roleIds);
		System.out.println("[testMyBatis permissionsList]:>>>>>>>>>" + permissionsList);
	}

	//测试redis
	@Test
	public void testRedis(){
		stringRedisTemplate.opsForValue().set("testRedis","test a litter");
		System.out.println("[testRedis]:>>>>>>>>>" + stringRedisTemplate.opsForValue().get("testRedis"));
	}

	//测试jwtUtils
	@Test
	public void testJwt(){
		SecUser secUser = userService.getUser("user");
		List<Long> roleIds = userRoleService.getRoleIds(secUser);
		Collection<SecRole> roleslist =  roleService.getList(roleIds);
		List<String> roles = roleslist.stream().map(SecRole::getName).collect(Collectors.toList());
		Collection<SecPermission> permissionsList =  permissionService.getList(roleIds);
		List<GrantedAuthority> authorities= permissionsList.stream().filter(permission -> StrUtil.isNotBlank(permission.getPermission())).map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toList());
		String jwtStr = jwtUtils.createJWT(secUser.getId(),secUser.getUsername(),roles,authorities,false);
		System.out.println("[testJwt createJWT]: "+ jwtStr);

		Claims claims = jwtUtils.parseJWT(jwtStr);
		System.out.println("[testJwt parseJWT]: "+ claims);
	}

	//测试一下securityConfig过滤是否设置成功
	@Test
	public void testTestController(){

	}

	//测试JwtAuthenticationFilter是否设置成功。调用AuthController.login 获取token值
	@Test
	public void testJwtFileter(){

	}

	//使用jasypt加密
	@Test
	public void testJasyptPassWord(){
		//        encryptor.setPassword("security-rbac-jwt-zhangwei-2021");
		String jwtSalt = encryptor.encrypt("rbac");
		String dataName = encryptor.encrypt("root");
		String dataPwd = encryptor.encrypt("backend2020");
		System.out.println("=========================== 开始");
		System.out.println("加密jwtSaltEN: "+jwtSalt);
		System.out.println("加密dataNameEN: "+dataName);
		System.out.println("加密dataPwdEN: "+dataPwd);
		System.out.println("=========================== 结束");


		System.out.println("=========================== 解密开始");
		String jwtSaltEN = encryptor.decrypt("8dox1aZJpg53GnEvAUya5g==");
		String dataNameEN = encryptor.decrypt("nEiZoYXDIhLAUbeGtjHvzg==");
		String dataPwdEN = encryptor.decrypt("EhJHpN6tW/T22VV3/Pun3T639rjK/Z1p");
		System.out.println("解密jwtSalt: "+jwtSaltEN);
		System.out.println("解密dataName: "+dataNameEN);
		System.out.println("解密dataPwd: "+dataPwdEN);
		System.out.println("=========================== 解密结束");
	}
}
