package com.snow.schedule.demo04.mytest;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author huangmengxue
 * @description
 * @date 2021/12/17 9:30
 */
@Component
public class MyClassFactoryBean implements FactoryBean<MyClass> {
    @Override
    public MyClass getObject() throws Exception {
        MyClass myClass = new MyClass();
        myClass.setAge(18);
        myClass.setId(1);
        myClass.setName("snow");
        return myClass;
    }

    @Override
    public Class<?> getObjectType() {
        return MyClass.class;
    }
}
