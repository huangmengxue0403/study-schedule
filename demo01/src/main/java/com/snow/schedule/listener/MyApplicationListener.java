package com.snow.schedule.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author huangmengxue
 * @description
 * @date 2022/1/5 15:11
 */
@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("监听到自定义的事件：" + event);
    }
}
