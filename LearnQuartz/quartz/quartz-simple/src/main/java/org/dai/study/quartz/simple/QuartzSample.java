package org.dai.study.quartz.simple;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class QuartzSample {
    public static class HelloJob implements Job{

        private String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("HelloJob is executed.");
            StringBuilder stringBuilder = new StringBuilder();

            JobKey key = jobExecutionContext.getJobDetail().getKey();
            stringBuilder.append("Instance ").append(key).append("\n");
//            JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
//            for (String s : dataMap.keySet()) {
//                stringBuilder.append(s).append(":").append(dataMap.get(s)).append("\n");
//            }
            HelloJob job = (HelloJob) jobExecutionContext.getJobInstance();
            stringBuilder.append("address:").append(job.getAddress());

            System.err.println(stringBuilder);
        }
    }

    public static class HelloJobListener implements JobListener{
        @Override
        public String getName() {
            return "hello";
        }

        @Override
        public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
            System.out.println("hello job to be executed");
        }

        @Override
        public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
            System.out.println("hello job vetoed");
        }

        @Override
        public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
            System.out.println("hello job was executed");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();



            HolidayCalendar calendar = new HolidayCalendar();
            calendar.addExcludedDate(new Date());

            scheduler.addCalendar("today", calendar, false, false);

            JobKey jobKey = new JobKey("job1", "group1");

            JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity(jobKey)
                .usingJobData("address", "job1")
                .build();

            HelloJobListener helloJobListener = new HelloJobListener();

            scheduler.getListenerManager()
                .addJobListener(
                    helloJobListener,
                    KeyMatcher.keyEquals(jobKey)
                );

            DateBuilder.futureDate(5, DateBuilder.IntervalUnit.DAY);

            Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .usingJobData("address", "trigger1")
                .modifiedByCalendar("today")
                .build();

            Trigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .usingJobData("address", "trigger2")
                .build();

            Trigger trigger3 = TriggerBuilder.newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                .forJob(jobKey)
                .usingJobData("address", "trigger3")
                .build();

            scheduler.scheduleJob(job, new HashSet<Trigger>(Arrays.asList(trigger1, trigger2, trigger3)), false);

            scheduler.start();

            Thread.sleep(10000);

            scheduler.shutdown();

        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
