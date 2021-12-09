package com.snow.schedule.demo02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.snow.schedule.demo02.mapper")
public class Demo02Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo02Application.class, args);
    }

}
