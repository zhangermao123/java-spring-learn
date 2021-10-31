package com.zw.service;

import com.zw.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.catalina.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zwq
 * @since 2021-05-25
 */
public interface UserInfoService extends IService<UserInfo> {
    //获取user
    List<UserInfo> getUsers();
}
