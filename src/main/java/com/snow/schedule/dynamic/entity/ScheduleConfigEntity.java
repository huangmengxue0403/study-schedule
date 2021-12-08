package com.snow.schedule.dynamic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/7 14:49
 */
@Data
@TableName("schedule_config")
public class ScheduleConfigEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * cron表达式
     */
    private String cron;
    /**
     * 任务描述
     */
    private String description;
    /**
     * 任务名称
     */
    private String className;
    /**
     * 任务状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 上次执行时间
     */
    private Long lastTime;
    /**
     * 下次执行时间
     */
    private Long nextTime;
}
