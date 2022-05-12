package com.example.demo.controller;

import com.example.demo.configuration.AppSchedulerConfigurer;
import com.example.demo.taks.FileReportNotificationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class HelloController {

    @Autowired
    private FileReportNotificationTask fileReportNotificationTask;

    @Autowired
    private AppSchedulerConfigurer schedulerConfigurer;

    @GetMapping("/")
    public String hello(@RequestParam(required = false) String hrs, @RequestParam(required = false) String min) {
        schedulerConfigurer.agenda(LocalTime.of(Integer.parseInt(hrs), Integer.parseInt(min)));
        return "Hello";
    }

}
