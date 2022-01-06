package com.snow.schedule.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author huangmengxue
 * @description
 * @date 2022/1/5 15:16
 */
public class MyApplicationEvent extends ApplicationEvent {
    public MyApplicationEvent(Object source) {
        super(source);
    }
}
