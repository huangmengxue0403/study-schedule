package com.snow.schedule;

import com.snow.schedule.listener.MyApplicationEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Demo01ApplicationTests {
    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
    }


    @Test
    void test01() {
        System.out.println();
        context.publishEvent(new MyApplicationEvent("我的监听器"));
    }

}
