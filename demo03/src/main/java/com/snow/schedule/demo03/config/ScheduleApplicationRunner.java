package com.snow.schedule.demo03.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.snow.schedule.demo03.service.TaskService;
import com.snow.schedule.demo03.entity.ScheduleConfigEntity;
import com.snow.schedule.demo03.mapper.ScheduleConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/9 15:02
 */
@Component
public class ScheduleApplicationRunner implements ApplicationRunner {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ScheduleConfigMapper scheduleConfigMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LambdaQueryWrapper<ScheduleConfigEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ScheduleConfigEntity::getStatus, 1);
        List<ScheduleConfigEntity> entityList = scheduleConfigMapper.selectList(wrapper);
        // 启动后开启定时任务
        entityList.forEach(i -> taskService.start(i.getId()));

    }
}
