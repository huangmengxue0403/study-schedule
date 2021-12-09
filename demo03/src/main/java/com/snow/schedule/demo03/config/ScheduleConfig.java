package com.snow.schedule.demo03.config;

import com.snow.schedule.demo03.utils.BeanUtils;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/9 10:17
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    private ScheduledTaskRegistrar taskRegistrar;
    private TaskScheduler taskScheduler;
    /**
     * 保存已经开启的定时任务
     */
    private Map<Integer, ScheduledFuture<?>> futureMap = new ConcurrentHashMap<>();

    @SneakyThrows
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 配置线程池
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
        this.taskRegistrar = taskRegistrar;
        // 通过反射获取ScheduledTaskRegistrar#taskScheduler属性
        this.taskScheduler = (TaskScheduler) BeanUtils.getProperty(taskRegistrar, "taskScheduler");
    }

    public ScheduledTaskRegistrar getTaskRegistrar() {
        return taskRegistrar;
    }

    public void setTaskRegistrar(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
    }

    public TaskScheduler getTaskScheduler() {
        return taskScheduler;
    }

    public void setTaskScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    public Map<Integer, ScheduledFuture<?>> getFutureMap() {
        return futureMap;
    }

    public void setFutureMap(Map<Integer, ScheduledFuture<?>> futureMap) {
        this.futureMap = futureMap;
    }


}
