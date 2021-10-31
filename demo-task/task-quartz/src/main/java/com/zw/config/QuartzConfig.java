package com.zw.config;

import com.zw.job.MyJob2;
import com.zw.job.QuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.*;

import java.util.Date;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.config
 * @descripation 首先执行的jobDetail。然后再Trigger 是指触发器 触发jobDetail
 * @date 2021-06-04
 */
@Configuration
@EnableScheduling
public class QuartzConfig {

    /**
     * @description MethodInvokingJobDetailFactoryBean 的jobDetail 这种方式无法入参
     * @return org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean
     */
    @Bean(name = "mIJobDetail")
   public MethodInvokingJobDetailFactoryBean methodInvokingFactoryBean(MyJob2 myJob2){
       MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        jobDetail.setTargetObject(myJob2);
        jobDetail.setTargetMethod("sayHello");
       return jobDetail;
   }

    /**
     * @description JobDetailFactoryBean 的jobDetail,可以传递参数author 对应QuartzJob.class 属性
     * @return org.springframework.scheduling.quartz.JobDetailFactoryBean
     */
    @Bean(name = "jobDetailF")
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(QuartzJob.class);
        jobDetail.setName("test--quartz-job");
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("author","zhangwei");
        jobDetail.setJobDataMap(jobDataMap);
        return jobDetail;
    }

    /**
     * @description trigger 定时触发器 触发MethodInvokingJobDetailFactoryBean
     * @param mIJobDetail
     * @return org.springframework.scheduling.quartz.SimpleTriggerFactoryBean
     */
    @Bean(name = "simpleTrigger")
    public SimpleTriggerFactoryBean simpleTrigger(JobDetail mIJobDetail) {

        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setStartTime(new Date());
        trigger.setRepeatCount(5);
        trigger.setJobDetail(mIJobDetail);
        trigger.setRepeatInterval(3000);
        return trigger;
    }

    /**
     * @description trigger 任务定时触发器 触发JobDetailFactoryBean
     * @param jobDetailF
     * @return org.springframework.scheduling.quartz.CronTriggerFactoryBean
     */
    @Bean(name = "cronTrigger")
    public CronTriggerFactoryBean cronTrigger(JobDetail jobDetailF) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setCronExpression("0/7 * * * * ?");
        trigger.setJobDetail(jobDetailF);
        return trigger;
    }


    /**
     * 调度工厂
     * @return
     */
    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(Trigger simpleTrigger,Trigger cronTrigger ) {

        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();

        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        scheduler.setOverwriteExistingJobs(true);

        // 延时启动，应用启动1秒后 先启动再注册触发器
        scheduler.setStartupDelay(1);

        // 注册触发器
        scheduler.setTriggers(simpleTrigger,cronTrigger);
        return scheduler;
    }

    //获取scheduler
    @Bean(name = "schedulerF")
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactory){
        return schedulerFactory.getScheduler();
    }

}
