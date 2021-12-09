package com.snow.schedule.demo02.task.impl;

import com.snow.schedule.demo02.task.MyScheduleTask;
import org.springframework.stereotype.Component;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/7 15:23
 */
@Component
public class MyTask3 extends MyScheduleTask {

    @Override
    public void execute() {
        System.out.println(System.currentTimeMillis() / 1000  + "-" + Thread.currentThread().getName() + ": task--3");
    }
}
