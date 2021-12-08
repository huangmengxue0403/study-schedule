package com.snow.schedule.dynamic.task.impl;

import com.snow.schedule.dynamic.task.MyScheduleTask;
import org.springframework.stereotype.Component;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/7 15:12
 */
@Component
public class MyTask1 extends MyScheduleTask {

    @Override
    public void execute() {
        System.out.println(System.currentTimeMillis() / 1000 + "-" + Thread.currentThread().getName() + ": task--1");
    }

}
