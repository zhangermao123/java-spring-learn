package com.zw.service.impl;

import com.zw.service.QuartzService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.service.impl
 * @descripation TODO
 * @date 2021-06-04
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    Scheduler schedulerF;


    @Override
    public void pauseJob() throws SchedulerException {
        //这个JobKey值同config中设置的name和group有关
        schedulerF.pauseJob(JobKey.jobKey("test--quartz-job"));
    }

    @Override
    public void resumeJob() throws SchedulerException {
        schedulerF.resumeJob(JobKey.jobKey("test--quartz-job"));
    }

    @Override
    public void cronJob() throws SchedulerException {
        CronTrigger trigger = (CronTrigger) schedulerF.getTrigger(TriggerKey.triggerKey("cronTrigger"));
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        trigger.getTriggerBuilder().withIdentity(TriggerKey.triggerKey("cronTrigger")).withSchedule(scheduleBuilder).build();
        schedulerF.rescheduleJob(TriggerKey.triggerKey("cronTrigger"),trigger);
    }

    @Override
    public void deleteJob() throws SchedulerException {
        //先暂停trigger,然后再unscheduleJob，最后删除job
        schedulerF.pauseTrigger(TriggerKey.triggerKey("cronTrigger"));
        schedulerF.unscheduleJob(TriggerKey.triggerKey("cronTrigger"));
        schedulerF.deleteJob(JobKey.jobKey("test--quartz-job"));

    }
}
