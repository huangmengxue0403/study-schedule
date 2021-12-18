package com.snow.schedule.demo04.mytest;

import lombok.Data;
import org.quartz.JobDetail;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/17 13:27
 */
@Component
@Data
public class MyBean {

    private JobDetail jobDetail;

    public MyBean(ObjectProvider<JobDetail> b) {
        this.jobDetail = b.orderedStream().findFirst().orElse(null);
    }

}
