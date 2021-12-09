package com.snow.schedule.annotation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/9 10:04
 */
@Component
public class MySchedule {
    @Scheduled(cron = "0/5 * * * * ?")
    public void test() {
        System.out.println(System.currentTimeMillis()/1000 + ": hello world");
    }
}
