package com.example.demo.configuration;

import com.example.demo.taks.FileReportNotificationTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class AppSchedulerConfigurer {

   public void agenda(LocalTime localTime) {
       this.agenda(localTime.atDate(LocalDate.of(2022, 5, 12)));
   }

    public void agenda(LocalDateTime localDateTime) {
        SchedulerFactory shedFact = new StdSchedulerFactory();

        try {
            Scheduler scheduler = shedFact.getScheduler();
            scheduler.start();
            JobDetail job = JobBuilder.newJob(FileReportNotificationTask.class)
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .startAt(convertToDate(localDateTime))
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch(SchedulerException e) {
            e.printStackTrace();
        }
    }

    private Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date convertToDate(LocalTime localTime) {
        return Date.from(localTime.atDate(LocalDate.of(2022, 5, 12)).
                atZone(ZoneId.systemDefault()).toInstant());
    }

}
