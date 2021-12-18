package com.snow.schedule.demo04.demo01;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.AbstractTrigger;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author huangmengxue
 * @description 1.定义任务
 * @date 2021/12/15 17:14
 */
@Component
public class MyJob01 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 当前时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        // 获取JobDetail携带的信息
        JobDetailImpl detail = (JobDetailImpl) context.getJobDetail();
        String detailGroup = detail.getGroup();
        String detailName = detail.getName();

        AbstractTrigger trigger = (AbstractTrigger) context.getTrigger();
        String triggerName = trigger.getName();
        String triggerGroup = trigger.getGroup();
        String stringFormat = "执行时间:%s,下次执行时间:%s,jobDetail:[%s,%s],trigger:[%s,%s]";
        // 下次执行时间
        DateFormat df3 = DateFormat.getTimeInstance();
        String nextTime = df3.format(trigger.getNextFireTime());
        String result = String.format(stringFormat, LocalDateTime.now().format(formatter), nextTime, detailGroup, detailName, triggerGroup, triggerName);
        System.out.println(result);
    }
}
