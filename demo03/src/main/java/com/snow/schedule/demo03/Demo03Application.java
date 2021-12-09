package com.snow.schedule.demo03;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.snow.schedule.demo03.mapper")
public class Demo03Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo03Application.class, args);
    }

}
