package com.example.demo.taks;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class FileReportNotificationTask implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("Enviando notificação " + LocalTime.now());
    }
}
