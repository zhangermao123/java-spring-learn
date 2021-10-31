package com.zw.service.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.zw.contant.ParamKey;
import com.zw.entity.SecUser;
import com.zw.entity.request.PageCondition;
import com.zw.entity.request.PageResult;
import com.zw.entity.vo.OnlineUser;
import com.zw.utils.RedisUtils;
import com.zw.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 监控 Service
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-12 00:55
 */
@Slf4j
@Service
public class MonitorService {
    @Autowired
    private RedisUtils redisUtil;

    @Autowired
    private SecUserServiceImpl userService;

    /**
     * 在线用户分页列表
     *
     * @param pageCondition 分页参数
     * @return 在线用户分页列表
     */
    public PageResult<OnlineUser> onlineUser(PageCondition pageCondition) {
        //遍历获取所有redis中已ParamKey.REDIS_JWT_KEY_PREFIX的key
        PageResult<String> keys = redisUtil.findKeysForPage(ParamKey.REDIS_JWT_KEY_PREFIX + ParamKey.SYMBOL_STAR, pageCondition.getCurrentPage(), pageCondition.getPageSize());
        List<String> rows = keys.getRows();
        Long total = keys.getTotal();

        // 根据 redis 中键获取用户名列表
        List<String> usernameList = rows.stream().map(s -> StrUtil.subAfter(s, ParamKey.REDIS_JWT_KEY_PREFIX, true)).collect(Collectors.toList());
        // 根据用户名查询用户信息
        List<SecUser> userList = userService.getUsersByUserName(usernameList);

        // 封装在线用户信息
        List<OnlineUser> onlineUserList = Lists.newArrayList();
        userList.forEach(user -> onlineUserList.add(OnlineUser.create(user)));

        return new PageResult<>(onlineUserList, total);
    }

    /**
     * 踢出在线用户
     *
     * @param names 用户名列表
     */
    public void kickout(List<String> names) {
        // 清除 Redis 中的 JWT 信息
        List<String> redisKeys = names.parallelStream().map(s -> ParamKey.REDIS_JWT_KEY_PREFIX + s).collect(Collectors.toList());
        redisUtil.delete(redisKeys);

        // 获取当前用户名
        String currentUsername = SecurityUtil.getCurrentUsername();
        names.parallelStream().forEach(name -> {
            // TODO: 通知被踢出的用户已被当前登录用户踢出，
            //  后期考虑使用 websocket 实现，具体伪代码实现如下。
            //  String message = "您已被用户【" + currentUsername + "】手动下线！";
            log.debug("用户【{}】被用户【{}】手动下线！", name, currentUsername);
        });
    }
}
