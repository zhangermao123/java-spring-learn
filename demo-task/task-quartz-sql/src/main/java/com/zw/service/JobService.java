package com.zw.service;

import com.github.pagehelper.PageInfo;
import com.zw.pojo.domain.JobAndTrigger;
import com.zw.pojo.form.JobForm;
import org.quartz.SchedulerException;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.service
 * @descripation TODO
 * @date 2021-06-07
 */
public interface JobService {
    /**
     * 添加并启动定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws Exception 异常
     */
    void addJob(JobForm form)throws Exception;
    /**
     * 删除定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    void deleteJob(JobForm form)throws SchedulerException;
    /**
     * 暂停定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    void pauseJob(JobForm form)throws SchedulerException;
    /**
     * 恢复定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    void resumeJob(JobForm form)throws SchedulerException;
    /**
     * 重新配置定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws Exception 异常
     */
    void cronJob(JobForm form)throws Exception;
    /**
     * 查询定时任务列表
     *
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @return 定时任务列表
     */
    PageInfo<JobAndTrigger> list(Integer currentPage, Integer pageSize);
}
