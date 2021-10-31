package com.zw.job;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.job
 * @descripation TODO
 * @date 2021-06-04
 */
@Component
@Slf4j
public class TaskJob {

    /**
     * 按照标准时间来算，每隔 5s 执行一次
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void job1(){
        log.info("[job1] 开始执行*******************" + new Date());
    }

    /**
     * 从启动时间开始，初始延迟1s 间隔 2s 执行
     * 固定间隔时间
     */
    @Scheduled(initialDelay = 1000,fixedRate = 2000)
    public void job2(){
        log.info("[job2] 执行*******************" + new Date());
    }

    /**
     * 从启动时间开始，延迟 5s 后间隔 4s 执行
     * 固定等待时间
     */
    @Scheduled(initialDelay = 5000,fixedDelay = 4000)
    public void job3(){
        log.info("[job3] 执行*******************" + new Date());
    }
}
