package com.zw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.mapper.JobMapper;
import com.zw.pojo.domain.JobAndTrigger;
import com.zw.pojo.form.JobForm;
import com.zw.service.JobService;
import com.zw.util.JobUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.service.impl
 * @descripation TODO
 * @date 2021-06-07
 */
@Slf4j
@Service
public class JobServiceImpl implements JobService {
    private final Scheduler scheduler;
    private final JobMapper jobMapper;

    @Autowired
    public JobServiceImpl(Scheduler scheduler, JobMapper jobMapper) {
        this.scheduler = scheduler;
        this.jobMapper = jobMapper;
    }

    @Override
    public void addJob(JobForm form) throws Exception {
        //调度开启
        scheduler.start();
        /*Job：调度任务执行的接口，也即定时任务执行的方法
        JobDetail：定时任务作业的实例
        JobBuilder：关联具体的Job，用于构建JobDetail*/
        //构建jobDetail 实例
        JobDetail jobDetail = JobBuilder.newJob(JobUtil.getClass(form.getJobClassName()).getClass()).withIdentity(form.getJobClassName(),form.getJobGroupName()).build();
        //Cron表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(form.getCronExpression());
        //根据Cron表达式构建一个Trigger
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(form.getJobClassName(), form.getJobGroupName()).withSchedule(cron).build();
        try {
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }catch (Exception e){
            log.error("[addJob] error : "+ e );
            throw  e;
        }


    }

    @Override
    public void deleteJob(JobForm form) throws SchedulerException {
        //暂停触发器 trigger
        scheduler.pauseTrigger(TriggerKey.triggerKey(form.getJobClassName(),form.getJobGroupName()));
        //移除触发器 trigger
        scheduler.unscheduleJob(TriggerKey.triggerKey(form.getJobClassName(),form.getJobGroupName()));
        //删除 job
        scheduler.deleteJob(JobKey.jobKey(form.getJobClassName(),form.getJobGroupName()));
    }

    @Override
    public void pauseJob(JobForm form) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(form.getJobClassName(),form.getJobGroupName()));
    }

    @Override
    public void resumeJob(JobForm form) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(form.getJobClassName(),form.getJobGroupName()));
    }

    @Override
    public void cronJob(JobForm form) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(form.getJobClassName(),form.getJobGroupName());

            //重新构建Cron表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(form.getCronExpression());

            //获取运行的trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //根据新的cron重新构建trigger,每次修改要添加withIdentity构建
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cron).build();
            //重新执行触发器 trigger
            scheduler.rescheduleJob(triggerKey,trigger);
        }catch (SchedulerException e){
            throw new Exception(e);
        }
    }

    @Override
    public PageInfo<JobAndTrigger> list(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<JobAndTrigger> list = jobMapper.list();
        return new PageInfo<>(list);
    }

}
