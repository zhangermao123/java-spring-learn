package com.zw.controller;

import com.zw.utils.RedissonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-06-23
 */
@RestController
public class RedissonController {
    @Autowired
    RedissonUtil redissonUtil;

    @GetMapping("/redisson")
    public void testRedisson() {
        String key = "redisson_key";
        for (int i = 0;i<100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.err.println("=============线程开启============" + Thread.currentThread().getName());
                        /*
                         * redissonUtil.lock(key,10L); //直接加锁，获取不到锁则一直等待获取锁
                         * Thread.sleep(100); //获得锁之后可以进行相应的处理
                         * System.err.println("======获得锁后进行相应的操作======"+Thread.
                         * currentThread().getName());
                         * redissonUtil.unlock(key); //解锁
                         * System.err.println("============================="+
                         * Thread.currentThread().getName());
                         */
                        boolean isGetLock = redissonUtil.tryLock(key, TimeUnit.SECONDS, 5L, 10L); // 尝试获取锁，等待5秒，自己获得锁后一直不解锁则10秒后自动解锁
                        if (isGetLock) {
                            System.out.println("线程:" + Thread.currentThread().getName() + ",获取到了锁");
                            Thread.sleep(100); // 获得锁之后可以进行相应的处理
                            System.err.println("======获得锁后进行相应的操作======" + Thread.currentThread().getName());
                            redissonUtil.unlock(key);
                            System.err.println("=============================" + Thread.currentThread().getName());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }
}
