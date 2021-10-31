package com.zw;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.mapper.CommonMapper;
import com.zw.mapper.UserMapper;
import com.zw.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonMapper commonMapper;

    @Test
    public void contextLoads(){

    }

    @Test
    public void selectAllUser() {
        int currentPage = 1;
        int pageSize = 5;
        String orderBy = "id desc";
        PageHelper.startPage(currentPage, pageSize, orderBy);
        List<User> userList = userMapper.selectAllUser();
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        Assert.assertEquals(5, userPageInfo.getSize());
        Assert.assertTrue(CollUtil.isNotEmpty(userList));
        log.info("<<<<<<<<<<<<[selectAllUser] is " + userPageInfo);
    }

    @Test
    public void selectUserById() {
        User user = userMapper.selectUserById(1L);
        Assert.assertTrue(ObjectUtil.isNotEmpty(user));
        log.info("<<<<<<<<<<<<[selectUserById] is " + user);
    }

    @Test
    public void saveUser() {
        String salt = IdUtil.fastSimpleUUID();
        User user = User.builder().name("testSave3").password(SecureUtil.md5("123456" + salt)).salt(salt).email("testSave3@xkcoding.com").phoneNumber("17300000003").status(1).lastLoginTime(new DateTime()).createTime(new DateTime()).lastUpdateTime(new DateTime()).build();
        int i = userMapper.saveUser(user);
        Assert.assertEquals(1, i);
    }

    @Test
    public void deleteById() {
        int i = userMapper.deleteById(1l);
        Assert.assertEquals(1, i);
    }

    @Test
    public void deleteAll(){
        Long primaryKey = 16l;
        User user = commonMapper.selectByPrimaryKey(primaryKey);
        log.info("this 16 user is :"+ user);
       int d =  commonMapper.deleteByPrimaryKey(primaryKey);
        log.info("dell 16 result :"+ d);
        User user2 = commonMapper.selectByPrimaryKey(primaryKey);
        log.info("all del the user final :"+ user2);
    }

    /**
     * 测试通用Mapper - 批量保存
     */
    @Test
    public void testInsertList() {
        for (int i = 1; i < 14; i++) {
			String salt = IdUtil.fastSimpleUUID();
			User user = User.builder().name("mybatis-test"+i).password(SecureUtil.md5("123456"+salt)).salt(salt).email("test"+i+"@163.com").phoneNumber("1730000100"+i).status(1).createTime(new DateTime()).lastLoginTime(new DateTime()).lastUpdateTime(new DateTime()).build();
            int n = userMapper.saveUser(user);
            Assert.assertEquals(1,n);
        }
    }

    @Test
    public void testPageSelect(){
        int currentPage = 1;
        int pageSize = 5;
        String orderBy = "id desc";
        int count = commonMapper.selectCount(null);
        PageHelper.startPage(currentPage, pageSize, orderBy);
        List<User> users = userMapper.selectAllUser();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        Assert.assertEquals(5, userPageInfo.getSize());
        Assert.assertEquals(count, userPageInfo.getTotal());
        log.debug("【userPageInfo】= {}", users);
	}
}
