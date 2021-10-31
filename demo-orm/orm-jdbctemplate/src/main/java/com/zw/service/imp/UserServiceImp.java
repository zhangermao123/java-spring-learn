package com.zw.service.imp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.zw.constant.Const;
import com.zw.dao.UserDao;
import com.zw.pojo.User;
import com.zw.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.service.imp
 * @descripation TODO
 * @date 2021-05-28
 */
@Service
public class UserServiceImp implements IUserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Boolean save(User user) {
        String rawPass = user.getPassword();
        String salt = IdUtil.simpleUUID();
        String pass = SecureUtil.md5(rawPass + Const.PASS_PREFIX + salt);
        user.setPassword(pass);
        user.setSalt(salt);
        return userDao.insert(user)>0;
    }

    @Override
    public Boolean delete(Long id) {
        return userDao.delete(id)>0;
    }

    @Override
    public Boolean update(User user, Long id) {
        User exist = getUser(id);
        if (StrUtil.isNotBlank(user.getPassword())) {
            String rawPass = user.getPassword();
            String salt = IdUtil.simpleUUID();
            String pass = SecureUtil.md5(rawPass + Const.PASS_PREFIX + salt);
            user.setPassword(pass);
            user.setSalt(salt);
        }
        //将user
        BeanUtil.copyProperties(user, exist, CopyOptions.create().setIgnoreNullValue(true));
        exist.setLastUpdateTime(new DateTime());
        return userDao.update(exist, id) > 0;
    }

    @Override
    public User getUser(Long id) {
        return userDao.findOneById(id);
    }

    @Override
    public List<User> getUser(User user) {
        return userDao.findByExample(user);
    }
}
