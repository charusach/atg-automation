package org.atg.assignment.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.atg.assignment.util.PropertyManager;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

public class MainApplication {
    private static final Logger LOG = LogManager.getLogger(MainApplication.class);

    public static void main(String[] args) throws SchedulerException, IOException {
        LOG.info("Starting the application");
        String cronExpression = PropertyManager.getInstance().getCronExpression();
        LOG.info("Going to create the job for automation tests with cron expression: {}", cronExpression);
        JobDetail job1 = JobBuilder.newJob(AutomationCronJob.class)
                .withIdentity("AutomationCronJob", "Group1").build();
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger1", "Group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(PropertyManager.getInstance().getCronExpression())
                )
                .build();
        Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
        scheduler1.start();
        scheduler1.scheduleJob(job1, trigger1);
        LOG.info("Cron job scheduled for automation tests");
    }

}
