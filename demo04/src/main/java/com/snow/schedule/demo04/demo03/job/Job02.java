package com.snow.schedule.demo04.demo03.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/18 13:41
 */
public class Job02 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("job2");
    }
}
