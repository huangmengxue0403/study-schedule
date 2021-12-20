package com.snow.schedule.demo04.demo03.service;

import com.snow.schedule.demo04.demo03.config.JobConfig;
import com.snow.schedule.demo04.demo03.entity.JobDetailEntity;
import com.snow.schedule.demo04.demo03.entity.TriggerEntity;
import org.quartz.*;
import org.quartz.core.QuartzScheduler;
import org.quartz.core.QuartzSchedulerResources;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/18 14:42
 */
@Service
public class JobService {
    @Autowired
    private Scheduler scheduler;


    public List<JobDetailEntity> list() {

        List<JobDetailEntity> list = new ArrayList<>();
        Map<Integer, JobDetail> jobDetailMap = JobConfig.jobDetailMap;

        for (Map.Entry<Integer, JobDetail> entry : jobDetailMap.entrySet()) {
            JobDetailEntity jobDetail = new JobDetailEntity();
            jobDetail.setId(entry.getKey());
            JobDetailImpl detail = (JobDetailImpl) entry.getValue();
            jobDetail.setJobName(detail.getName());
            jobDetail.setJobGroup(detail.getGroup());
            jobDetail.setDescription(detail.getDescription());
            if (null != detail.getJobDataMap() && detail.getJobDataMap().containsKey("triggerId")) {
                jobDetail.setTriggerId((Integer) detail.getJobDataMap().get("triggerId"));
            }
            list.add(jobDetail);
        }
        return list;
    }

    public List<TriggerEntity> triggerList() {
        List<TriggerEntity> list = new ArrayList<>();
        Map<Integer, Trigger> jobDetailMap = JobConfig.triggerMap;
        for (Map.Entry<Integer, Trigger> entry : jobDetailMap.entrySet()) {
            TriggerEntity triggerEntity = new TriggerEntity();
            triggerEntity.setId(entry.getKey());
            CronTrigger trigger = (CronTrigger) entry.getValue();
            triggerEntity.setCron(trigger.getCronExpression());
            triggerEntity.setGroup(trigger.getKey().getGroup());
            triggerEntity.setName(trigger.getKey().getName());
            list.add(triggerEntity);
        }
        return list;
    }


    public void updateJobById(Integer id, Integer triggerId) {
        Map<Integer, JobDetail> jobDetailMap = JobConfig.jobDetailMap;
        JobDetailImpl detail = (JobDetailImpl) jobDetailMap.get(id);
        JobDataMap jobDataMap = detail.getJobDataMap();
        if (null != jobDataMap) {
            jobDataMap.put("triggerId", triggerId);
        } else {
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("triggerId", triggerId);
            detail.setJobDataMap(dataMap);
        }
    }

    public void updateTriggerById(Integer id, String cron) throws Exception {
        Map<Integer, Trigger> jobDetailMap = JobConfig.triggerMap;
        CronTriggerImpl trigger = (CronTriggerImpl) jobDetailMap.get(id);
        trigger.setCronExpression(cron);
        // key = group-name
        // 由于实际存储的是clone对象，修改trigger无效，只能通过修改clone的方式
        QuartzSchedulerResources resources = getQuartzSchedulerResources();
        TriggerKey triggerKey = new TriggerKey(trigger.getName(), trigger.getGroup());
        resources.getJobStore().replaceTrigger(triggerKey, trigger);
    }

    public void start(Integer id) throws Exception {
        getQuartzSchedulerResources();
        Map<Integer, JobDetail> jobDetailMap = JobConfig.jobDetailMap;
        JobDetailImpl detail = (JobDetailImpl) jobDetailMap.get(id);
        if (null != detail.getJobDataMap() && detail.getJobDataMap().containsKey("triggerId")) {
            Integer triggerId = (Integer) detail.getJobDataMap().get("triggerId");
            Trigger trigger = JobConfig.triggerMap.get(triggerId);
            scheduler.scheduleJob(detail, trigger);
        }
    }


    public QuartzSchedulerResources getQuartzSchedulerResources() throws Exception {
        Field field = scheduler.getClass().getDeclaredField("sched");
        field.setAccessible(true);
        QuartzScheduler sched = (QuartzScheduler) field.get(scheduler);
        // 获取resource
        Field f = sched.getClass().getDeclaredField("resources");
        f.setAccessible(true);
        QuartzSchedulerResources resources = (QuartzSchedulerResources) f.get(sched);
        return resources;
    }


    public void stop(Integer id) throws SchedulerException {
        Map<Integer, JobDetail> jobDetailMap = JobConfig.jobDetailMap;
        JobDetailImpl detail = (JobDetailImpl) jobDetailMap.get(id);
        scheduler.deleteJob(new JobKey(detail.getName(), detail.getGroup()));
    }
}
