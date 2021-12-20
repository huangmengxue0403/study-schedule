package com.snow.schedule.demo04.demo03.entity;

import lombok.Data;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/18 14:41
 */
@Data
public class JobDetailEntity {
    /**
     * id
     */
    private Integer id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务描述
     */
    private String description;
    private Integer triggerId;
}
