package com.snow.schedule.demo03.task.impl;

import com.snow.schedule.demo03.task.MyScheduleTask;
import org.springframework.stereotype.Component;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/9 11:04
 */
@Component
public class MyTask03 extends MyScheduleTask {
    @Override
    public void execute() {
        System.out.println(System.currentTimeMillis() / 1000 + "-" + Thread.currentThread().getName() + ": task--3");
    }
}
