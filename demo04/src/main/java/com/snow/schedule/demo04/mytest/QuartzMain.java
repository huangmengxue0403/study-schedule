package com.snow.schedule.demo04.mytest;

import com.snow.schedule.demo04.demo01.MyJob01;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/16 16:24
 */
public class QuartzMain {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 通过SchedulerFactory创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 创建任务(与Job的实现类进行绑定 一对一)
        JobDataMap dataMap = new JobDataMap();
        dataMap.putAsString("id", 1);
        JobDetail jobDetail = JobBuilder.newJob(MyJob01.class)
                .withIdentity("myJob", "my_job_group")
                .setJobData(dataMap)
                .build();
        // 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        // 将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);
        // 调度器开始调度任务
        scheduler.start();
    }
}
