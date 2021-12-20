package com.snow.schedule.demo04.demo03.job;

import org.quartz.*;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/18 13:39
 */
public class Job01 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 获取JobTrigger
        CronTrigger trigger = (CronTrigger) context.getTrigger();
        System.out.println(trigger.hashCode());
        System.out.println(trigger.getCronExpression());
        System.out.println("job1");
    }
}
