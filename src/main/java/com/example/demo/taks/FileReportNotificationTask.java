package com.example.demo.taks;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class FileReportNotificationTask implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("notification task " + LocalTime.now().toString());
            Thread.sleep(1000);
            System.out.println("notification task " + LocalTime.now().toString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
