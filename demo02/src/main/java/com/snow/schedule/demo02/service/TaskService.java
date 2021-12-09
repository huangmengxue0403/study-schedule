package com.snow.schedule.demo02.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snow.schedule.demo02.entity.ScheduleConfigEntity;
import com.snow.schedule.demo02.mapper.ScheduleConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/7 17:27
 */
@Service
public class TaskService {
    @Autowired
    private ScheduleConfigMapper scheduleConfigMapper;


    public List<ScheduleConfigEntity> list() {
        return scheduleConfigMapper.selectList(new QueryWrapper<>());
    }


    public void updateById(Integer id, String cron) {
        ScheduleConfigEntity entity = scheduleConfigMapper.selectById(id);
        entity.setCron(cron);
        scheduleConfigMapper.updateById(entity);
    }


    public void stop(Integer id) {
        ScheduleConfigEntity entity = scheduleConfigMapper.selectById(id);
        entity.setStatus(0);
        scheduleConfigMapper.updateById(entity);
    }


    public void start(Integer id) {
        ScheduleConfigEntity entity = scheduleConfigMapper.selectById(id);
        entity.setStatus(1);
        scheduleConfigMapper.updateById(entity);

    }


}
