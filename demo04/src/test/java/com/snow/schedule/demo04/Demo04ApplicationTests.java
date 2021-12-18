package com.snow.schedule.demo04;

import com.snow.schedule.demo04.mytest.MyBean;
import com.snow.schedule.demo04.mytest.MyClass;
import com.snow.schedule.demo04.mytest.MyClassFactoryBean;
import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@SpringBootTest
class Demo04ApplicationTests {
    @Autowired
    private MyClassFactoryBean myClassFactoryBean;
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    MyBean myBean;

    @Test
    void contextLoads() {
    }

    @Test
    void myTest() throws Exception {
        MyClass myClass01 = myClassFactoryBean.getObject();
        MyClass myClass02 = myClassFactoryBean.getObject();
        System.out.println();
    }

    @Test
    void test02() {
        Scheduler scheduler01 = schedulerFactoryBean.getScheduler();
        Scheduler scheduler02 = schedulerFactoryBean.getScheduler();
        System.out.println();
    }

    @Test
    void test03() {

        myBean.getJobDetail();
        System.out.println();
    }


}
