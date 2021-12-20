package com.snow.schedule.demo04.demo03.config;

import org.quartz.JobDetail;
import org.quartz.Trigger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/18 14:20
 */
//@Configuration
public class JobConfig {
    /**
     * 存放触发器
     */
    public static Map<Integer, Trigger> triggerMap = new HashMap<>();

    /**
     * 存放jobDetail
     */
    public static Map<Integer, JobDetail> jobDetailMap = new HashMap<>();



}
