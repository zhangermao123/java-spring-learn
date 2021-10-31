package com.zw.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.config
 * @descripation 一般任务调度直接写config比较好，也会添加EnableAsync
 * @date 2021-06-04
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.zw.job")
@EnableAsync
public class TaskConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor(){
        return new ScheduledThreadPoolExecutor(20, new BasicThreadFactory.Builder().namingPattern("Task-QUICK--%d").build());
    }

//    @Bean("taskExecutor")
//    public ThreadPoolTaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        taskExecutor.setCorePoolSize(4);
//        taskExecutor.setMaxPoolSize(4);
//        taskExecutor.setQueueCapacity(25);
//        taskExecutor.setKeepAliveSeconds(200);
//        taskExecutor.setThreadNamePrefix("WS_Task-");
//        // 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
//        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        //调度器shutdown被调用时等待当前被调度的任务完成
//        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
//        //等待时长
//        taskExecutor.setAwaitTerminationSeconds(60);
//        taskExecutor.initialize();
//        return taskExecutor;
//    }
}
