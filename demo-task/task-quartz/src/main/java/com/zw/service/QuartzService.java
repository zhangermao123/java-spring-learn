package com.zw.service;

import org.quartz.SchedulerException;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.service
 * @descripation TODO
 * @date 2021-06-04
 */
public interface QuartzService {

    void pauseJob() throws SchedulerException;

    void resumeJob() throws SchedulerException;

    void cronJob() throws SchedulerException;

    void deleteJob() throws SchedulerException;
}
