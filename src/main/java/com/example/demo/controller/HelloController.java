package com.example.demo.controller;

import com.example.demo.taks.FileReportNotificationTask;
import com.example.demo.taks.MyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class HelloController {

    @Autowired
    private FileReportNotificationTask fileReportNotificationTask;

    @Autowired
    private MyTask myTask;

    @GetMapping("/")
    public String hello(@RequestParam(required = false) String hrs, @RequestParam(required = false) String min) {
        fileReportNotificationTask.run();
        myTask.run();
        return "Hello";
    }

    @Scheduled(fixedDelay = 1000)
    public void teste() {
        System.out.println("Teste de 1 em 1 segundos " + LocalTime.now().toString());
    }

}
