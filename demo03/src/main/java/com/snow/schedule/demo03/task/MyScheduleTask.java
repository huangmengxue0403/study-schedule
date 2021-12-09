package com.snow.schedule.demo03.task;

import com.snow.schedule.demo03.entity.ScheduleConfigEntity;
import com.snow.schedule.demo03.mapper.ScheduleConfigMapper;
import com.snow.schedule.demo03.utils.ScheduleUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/9 11:01
 */
public abstract class MyScheduleTask implements Runnable {
    private String cron;
    private Integer id;
    @Autowired
    private ScheduleConfigMapper configMapper;

    /**
     * 抽象方法-子类实现此抽象方法
     */
    public abstract void execute();


    @Override
    public void run() {
        System.out.println("----run----");
        // 修改执行时间
        execute();
        ScheduleConfigEntity entity = configMapper.selectById(id);
        // 修改上次执行时间和下次执行时间
        Long last = System.currentTimeMillis() / 1000;
        entity.setLastTime(last);
        Long next = ScheduleUtil.getNextTriggerTime(entity.getCron()) / 1000;
        entity.setNextTime(next);
        configMapper.updateById(entity);
    }

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
}
