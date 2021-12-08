package com.snow.schedule.dynamic.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.snow.schedule.dynamic.entity.ScheduleConfigEntity;
import com.snow.schedule.dynamic.mapper.ScheduleConfigMapper;
import com.snow.schedule.dynamic.task.MyScheduleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author huangmengxue
 * @description 定时任务配置类
 * @date 2021/12/7 14:55
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    private ScheduledTaskRegistrar scheduledTaskRegistrar;
    @Autowired
    private ScheduleConfigMapper scheduleConfigMapper;
    @Autowired
    private ApplicationContext context;

    public ScheduledTaskRegistrar getScheduledTaskRegistrar() {
        return scheduledTaskRegistrar;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 配置多线程
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
        this.scheduledTaskRegistrar = taskRegistrar;

        LambdaQueryWrapper<ScheduleConfigEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ScheduleConfigEntity::getStatus,1);
        List<ScheduleConfigEntity> list = scheduleConfigMapper.selectList(queryWrapper);
        list.forEach(i->{
            MyScheduleTask task = getTask(i);
            task.setId(i.getId());
            task.setCron(i.getCron());
            taskRegistrar.addTriggerTask(task,
                    triggerContext -> new CronTrigger(task.getCron()).nextExecutionTime(triggerContext)
            );
        });
        System.out.println("success");
    }

    private MyScheduleTask getTask(ScheduleConfigEntity entity) {
        Class<?> cl;
        MyScheduleTask task;
        try {
            cl = Class.forName(entity.getClassName());
            task = (MyScheduleTask) context.getBean(cl);
            task.setCron(entity.getCron());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return task;
    }


}
