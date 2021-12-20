package com.snow.schedule.demo04.demo03.entity;

import lombok.Data;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/18 15:10
 */
@Data
public class TriggerEntity {
    private Integer id;
    private String cron;
    private String group;
    private String name;
}
