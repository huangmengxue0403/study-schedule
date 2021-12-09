package com.snow.schedule.demo03.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snow.schedule.demo03.config.ScheduleConfig;
import com.snow.schedule.demo03.entity.ScheduleConfigEntity;
import com.snow.schedule.demo03.mapper.ScheduleConfigMapper;
import com.snow.schedule.demo03.task.MyScheduleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/9 11:05
 */
@Service
public class TaskService {
    @Autowired
    private ScheduleConfigMapper scheduleConfigMapper;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private ScheduleConfig scheduleConfig;

    public void start(Integer id) {
        ScheduleConfigEntity entity = scheduleConfigMapper.selectById(id);
        MyScheduleTask task = getTask(entity);
        TriggerTask triggerTask = new CronTask(task, entity.getCron());
        // 判断任务是否已经开启
        if (scheduleConfig.getFutureMap().containsKey(id)) {
            return;
        }
        ScheduledFuture<?> future = scheduleConfig.getTaskScheduler().schedule(triggerTask.getRunnable(), triggerTask.getTrigger());
        scheduleConfig.getFutureMap().put(entity.getId(), future);
        entity.setStatus(1);
        scheduleConfigMapper.updateById(entity);
    }

    public void stop(Integer id) {
        ScheduleConfigEntity entity = scheduleConfigMapper.selectById(id);
        ScheduledFuture<?> future = scheduleConfig.getFutureMap().get(id);
        if (null == future) {
            return;
        }
        future.cancel(true);
        scheduleConfig.getFutureMap().remove(id);
        entity.setStatus(0);
        scheduleConfigMapper.updateById(entity);
    }

    public List<ScheduleConfigEntity> list() {
        return scheduleConfigMapper.selectList(new QueryWrapper<>());
    }


    public void updateById(Integer id, String cron) {
        ScheduleConfigEntity entity = scheduleConfigMapper.selectById(id);
        entity.setCron(cron);
        String str = Thread.currentThread().getName();
        System.out.println();
        scheduleConfigMapper.updateById(entity);
        // 任务正在运行 不可修改
        ScheduledFuture<?> future = scheduleConfig.getFutureMap().get(id);
        if (scheduleConfig.getFutureMap().containsKey(id)) {
            System.out.println("为啥不执行呢");
            // 先暂停
            stop(id);
            // 再开始
            start(id);
        }

    }

    private MyScheduleTask getTask(ScheduleConfigEntity entity) {
        Class<?> cl;
        MyScheduleTask task;
        try {
            cl = Class.forName(entity.getClassName());
            task = (MyScheduleTask) context.getBean(cl);
            task.setCron(entity.getCron());
            task.setId(entity.getId());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return task;
    }
}
