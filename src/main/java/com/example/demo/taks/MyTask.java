package com.example.demo.taks;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class MyTask implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("notificação " + LocalTime.now().toString());
            Thread.sleep(1000);
            System.out.println("notificação " + LocalTime.now().toString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
