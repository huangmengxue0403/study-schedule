package com.snow.schedule.demo04.demo01;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/16 14:26
 */
@Configuration
public class QuartzJobConfig {
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(MyJob01.class)
                .withIdentity("myDetail-1", "demo-detail-1")
                .storeDurably()
                .build();
    }

//    @Bean
    public Trigger trigger() {
        return TriggerBuilder.newTrigger()
                .startNow()
                .withIdentity("myTrigger-1", "demo-trigger-1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .forJob(jobDetail())
                .build();
    }
}
