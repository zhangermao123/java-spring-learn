package com.zw.controller;

import com.zw.service.QuartzService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-06-04
 */
@RestController
public class QuartzController {
    @Autowired
    QuartzService service;

    @GetMapping("/pause")
    public void pauseJob() throws SchedulerException {
        service.pauseJob();
    }

    @GetMapping("/resume")
    public void resumeJob() throws SchedulerException {
        service.resumeJob();
    }

    @GetMapping("/change")
    public void changeJob() throws SchedulerException {
        service.cronJob();
    }

    @GetMapping("/del")
    public void delJob() throws SchedulerException {
        service.deleteJob();
    }
}
