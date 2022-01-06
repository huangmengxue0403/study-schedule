package com.snow.schedule.annotation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author huangmengxue
 * @description
 * @date 2022/1/5 14:44
 */
@Component
public class MySchedule02 {
    @Scheduled(cron = "0/5 * * * * ?")
    public void test01() throws InterruptedException {
        Thread.sleep(10 * 1000);
        System.out.println(System.currentTimeMillis()/1000 + ": test02");
    }
}
