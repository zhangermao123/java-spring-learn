package com.zw.utils;

import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.utils
 * @descripation TODO
 * @date 2021-06-23
 */
@Component
public class RedissonUtil {
    @Autowired
    private RedissonClient redissonClient;

    //lock(), 拿不到lock就不罢休，不然线程就一直block
    public RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        //加锁
        lock.lock();
        return lock;
    }

    //leaseTime为加锁时间，单位为秒
    public RLock lock(String lockKey, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        //加锁，leaseTime-最长加锁时间
        lock.lock(leaseTime, TimeUnit.SECONDS);
        return null;
    }

    // timeout为加锁时间，时间单位由unit确定
    public RLock lock(String lockKey, TimeUnit unit, long timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, unit);
        return lock;
    }


    /**
    * 尝试获取锁
    * @param lockKey
    * @param unit   单位
    * @param waitTime 最多等待时间
    * @param leaseTime 上锁后自动释放锁时间
    * @return
    */
    public boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            //尝试加锁
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    //解锁
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

    //解锁
    public void unlock(RLock lock) {
        lock.unlock();
    }

    //获取计数器
    public RCountDownLatch getCountDownLatch(String lockKey){
        return redissonClient.getCountDownLatch(lockKey);
    }
}
