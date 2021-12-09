package com.snow.schedule.demo03.utils;

import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import static com.cronutils.model.CronType.QUARTZ;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/9 15:19
 */
public class ScheduleUtil {
    private static final CronDefinition CRON_DEFINITION = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
    private static final CronParser parser = new CronParser(CRON_DEFINITION);


    /**
     * 上次执行时间
     *
     * @param executionTime
     * @param currentTime   毫秒
     * @return lastTriggerTime 毫秒
     */
    protected static Long getLastTriggerTime(ExecutionTime executionTime, Long currentTime) {
        LocalDateTime now = LocalDateTime.ofInstant(new Date(currentTime).toInstant(), ZoneOffset.of("+8"));
        Optional<ZonedDateTime> zonedDateTimeOptional = executionTime.lastExecution(ZonedDateTime.of(now, ZoneOffset.of("+8")));
        if (zonedDateTimeOptional.isPresent()) {
            ZonedDateTime zonedDateTime = zonedDateTimeOptional.get();
            return zonedDateTime.toLocalDateTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        }
        return null;
    }

    /**
     * 下次执行时间
     *
     * @param executionTime
     * @return lastTriggerTime 毫秒
     */
    protected static Long getNextTriggerTime(ExecutionTime executionTime) {
        Optional<ZonedDateTime> zonedDateTimeOptional = executionTime.nextExecution(ZonedDateTime.now());
        if (zonedDateTimeOptional.isPresent()) {
            ZonedDateTime zonedDateTime = zonedDateTimeOptional.get();
            return zonedDateTime.toLocalDateTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        }
        return null;
    }


    /**
     * 上次执行时间
     *
     * @param cron
     * @param currentTime 毫秒
     * @return lastTriggerTime 毫秒
     */
    public static Long getLastTriggerTime(String cron, Long currentTime) {
        Cron quartzCron = parser.parse(cron);
        ExecutionTime executionTime = ExecutionTime.forCron(quartzCron);
        return getLastTriggerTime(executionTime, currentTime);
    }

    /**
     * 下次执行时间
     *
     * @param cron
     * @return nextTriggerTime 毫秒
     */
    public static Long getNextTriggerTime(String cron) {
        Cron quartzCron = parser.parse(cron);
        ExecutionTime executionTime = ExecutionTime.forCron(quartzCron);
        return getNextTriggerTime(executionTime);
    }
}
