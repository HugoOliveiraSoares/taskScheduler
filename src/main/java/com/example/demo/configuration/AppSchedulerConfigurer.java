package com.example.demo.configuration;

import com.example.demo.taks.FileReportNotificationTask;
import com.example.demo.taks.MyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

@EnableScheduling
@Async
@Configuration
public class AppSchedulerConfigurer implements SchedulingConfigurer {

    @Autowired
    private FileReportNotificationTask fileReportNotificationTask;

    @Autowired
    private MyTask myTask;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(fileReportNotificationTask, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {

                Date date = triggerContext.lastActualExecutionTime();

                if (date == null) {
                    return getThirdLastDateOfCurrentMonth();
                } else {
                    return getThirdLastDateOfNextMonth(date);
                }

            }
        });

        taskRegistrar.addTriggerTask(myTask, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                Date date = triggerContext.lastActualExecutionTime();

                if (date == null) {
                    return getThirdLastDateOfCurrentMonth();
                } else {
                    return getThirdLastDateOfNextMonth(date);
                }
            }
        });

    }

    public Date getThirdLastDateOfCurrentMonth() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime desiredDate = now.plusMinutes(1);

        if (now.isEqual(desiredDate)) {
            return new Date();
        }
        return convertToDate(desiredDate);
    }

    public Date getThirdLastDateOfNextMonth(Date lastExecutionDate) {
        LocalDateTime lastExecutionDateTime = convertToLocalDateTime(lastExecutionDate);

        LocalDateTime desiredDate = lastExecutionDateTime.plusMinutes(2);

        return convertToDate(desiredDate);
    }

    public Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
