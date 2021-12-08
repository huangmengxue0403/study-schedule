package com.snow.schedule.dynamic.task.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snow.schedule.dynamic.entity.ScheduleConfigEntity;
import com.snow.schedule.dynamic.mapper.ScheduleConfigMapper;
import com.snow.schedule.dynamic.task.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/7 17:27
 */
@Service
public class ScheduledTaskServiceImpl implements ScheduledTaskService {
    @Autowired
    private ScheduleConfigMapper scheduleConfigMapper;


    @Override
    public List<ScheduleConfigEntity> list() {
        return scheduleConfigMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public void updateById(Integer id, String cron) {
        ScheduleConfigEntity entity = scheduleConfigMapper.selectById(id);
        entity.setCron(cron);
        scheduleConfigMapper.updateById(entity);
    }

    @Override
    public void stop(Integer id) {
        ScheduleConfigEntity entity = scheduleConfigMapper.selectById(id);
        entity.setStatus(0);
        scheduleConfigMapper.updateById(entity);
    }

    @Override
    public void start(Integer id) {
        ScheduleConfigEntity entity = scheduleConfigMapper.selectById(id);
        entity.setStatus(1);
        scheduleConfigMapper.updateById(entity);

    }


}
