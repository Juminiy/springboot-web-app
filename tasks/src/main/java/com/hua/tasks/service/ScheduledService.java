package com.hua.tasks.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    @Scheduled(cron = "30 0/5 10,18 * * ?")
    public void scheduled(){
        System.out.println("scheduled is monk");
    }
}
