package com.zw.job.base;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.job.base
 * @descripation Job：调度任务执行的接口，也即定时任务执行的方法
 *               JobDetail：定时任务作业的实例
 *               JobBuilder：关联具体的Job，用于构建JobDetail
 * @date 2021-06-07
 */
public interface BaseJob extends Job {
    @Override
    void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;
}
