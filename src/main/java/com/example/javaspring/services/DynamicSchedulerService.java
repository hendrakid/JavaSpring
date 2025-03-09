package com.example.javaspring.services;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicSchedulerService {

    private final TaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledFuture;

    public DynamicSchedulerService(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    // 🟢 Start a scheduled task dynamically
    public void startTask(String cronExpression) {
        stopTask(); // Ensure only one task is running at a time
        scheduledFuture = taskScheduler.schedule(this::executeTask, new CronTrigger(cronExpression));
        System.out.println("Task scheduled with CRON: " + cronExpression);
    }

    // 🔴 Stop the running scheduled task
    public void stopTask() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(false);
            System.out.println("Scheduled task stopped.");
        }
    }

    // ✅ The actual task to be executed
    private void executeTask() {
        System.out.println("Dynamic Task Executed at: " + LocalTime.now());
    }
}
