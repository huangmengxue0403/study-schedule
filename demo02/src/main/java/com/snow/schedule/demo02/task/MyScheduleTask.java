package com.snow.schedule.demo02.task;

import com.snow.schedule.demo02.entity.ScheduleConfigEntity;
import com.snow.schedule.demo02.mapper.ScheduleConfigMapper;
import com.snow.schedule.demo02.utils.ScheduleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/7 14:57
 */
public abstract class MyScheduleTask implements Runnable {
    private String cron;
    private Integer id;
    @Autowired
    private ScheduleConfigMapper configMapper;
    @Autowired
    private ApplicationContext context;

    /**
     * 抽象方法-子类实现此抽象方法
     */
    public abstract void execute();

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("--------------run----------------");
        // 修改定时任务的时间
        ScheduleConfigEntity entity = configMapper.selectById(id);
        MyScheduleTask task = getTask(entity);
        task.setCron(entity.getCron());
        // 状态为暂停
        if (entity.getStatus() == 0) {
            return;
        }
        // 执行方法
        execute();
        // 修改上次执行时间和下次执行时间
        Long last = System.currentTimeMillis() / 1000;
        entity.setLastTime(last);
        Long next = ScheduleUtil.getNextTriggerTime(entity.getCron()) / 1000;
        entity.setNextTime(next);
        configMapper.updateById(entity);
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
