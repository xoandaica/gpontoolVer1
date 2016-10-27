/*
 * Copyright 2015 Pivotal Software, Inc..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vn.vnpttech.ssdc.nms.scheduler.job2;

import vn.vnpttech.ssdc.nms.scheduler.job.*;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.impl.matchers.KeyMatcher;
import vn.vnpttech.ssdc.nms.scheduler.utils.ConfigUtils;

/**
 *
 * @author longdq
 */
public class PolicyJob2Trigger {

    private static Logger log = Logger.getLogger(SchedulerTrigger.class);
    private static String interval = "60";

    private static Scheduler scheduler = null;

    private static PolicyJob2Trigger instance = null;

    protected PolicyJob2Trigger() {
        // Exists only to defeat instantiation.
    }

    public static PolicyJob2Trigger getInstance() {
        if (instance == null) {
            instance = new PolicyJob2Trigger();
        }
        return instance;
    }

    public void init() {
        try {
            interval = ConfigUtils.getInterval();
            if (StringUtils.isBlank(interval)) {
                interval = "60";
            }
            scheduler = new StdSchedulerFactory().getScheduler();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void startScheduler() {
        try {
//           PropertyConfigurator.configure("etc/log4j.conf");
            JobKey jobKey = new JobKey("stbJob", "stbGroup");
            JobDetail job = JobBuilder.newJob(PolicyJob2.class)
                    .withIdentity(jobKey).build();
            int intervalInt = Integer.parseInt(interval);
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("policy-job2-trigger", "stbGroup")
                    //.withSchedule(CronScheduleBuilder.cronSchedule("0 0/" + interval + " * * * ?"))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(intervalInt).repeatForever())
                    .build();
           

            //   Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            //Listener attached to jobKey
            scheduler.getListenerManager().addJobListener(
                    new PolicyJob2Listener(), KeyMatcher.keyEquals(jobKey)
            );
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException schedulerException) {
            log.error(schedulerException.getMessage());
        }

    }

    public void stopScheduler() {

    }

}
