package com.snow.schedule.demo04.demo03.config;

import com.snow.schedule.demo04.demo03.job.Job01;
import com.snow.schedule.demo04.demo03.job.Job02;
import org.quartz.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/18 14:21
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<Integer, Trigger> triggerMap = JobConfig.triggerMap;
        // 向触发器容器中添加触发器(可以是配置在数据库中的,这里我们添加的是基于cron表达式的trigger)
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("trigger-1", "group")
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        Trigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("trigger-2", "group")
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        Trigger trigger3 = TriggerBuilder.newTrigger()
                .withIdentity("trigger-3", "group")
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        triggerMap.put(1, trigger1);
        triggerMap.put(2, trigger2);
        triggerMap.put(3, trigger3);
        System.out.println(trigger1.hashCode());
        System.out.println(trigger2.hashCode());
        System.out.println(trigger3.hashCode());
        // 触发器初始化完成
        System.out.println("触发器初始化完成");

        // 初始化任务
        Map<Integer, JobDetail> jobDetailMap = JobConfig.jobDetailMap;
        JobDetail jobDetail01 = JobBuilder.newJob(Job01.class)
                .withIdentity("job-1", "group")
                .withDescription("第一个定时任务")
                .storeDurably()
                .build();

        JobDetail jobDetail02 = JobBuilder.newJob(Job02.class)
                .withIdentity("job-2", "group")
                .withDescription("第二个定时任务")
                .storeDurably()
                .build();
        jobDetailMap.put(1, jobDetail01);
        jobDetailMap.put(2, jobDetail02);
        System.out.println("初始化任务完成");

    }
}
