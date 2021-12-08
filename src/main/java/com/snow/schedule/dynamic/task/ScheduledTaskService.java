package com.snow.schedule.dynamic.task;

import com.snow.schedule.dynamic.entity.ScheduleConfigEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/7 15:03
 */
@Service
public interface ScheduledTaskService {

    /**
     * 列表
     *
     * @param
     */
    List<ScheduleConfigEntity> list();

    /**
     * update
     *
     * @param id
     * @param cron
     */
    void updateById(Integer id, String cron);


    /**
     * 启动
     *
     * @param id
     */
    void stop(Integer id);

    /**
     * 暂停
     *
     * @param id
     */
    void start(Integer id);
}
