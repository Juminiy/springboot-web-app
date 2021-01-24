package com.hua.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync //开启异步任务
@EnableScheduling //开启定时任务
@SpringBootApplication
public class SpringTasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTasksApplication.class, args);
    }

}
