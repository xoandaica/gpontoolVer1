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
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 *
 * @author longdq
 */
public class PolicyJob2Listener implements JobListener {

    public static final String LISTENER_NAME = "stbJobListener";
    private static Logger log = Logger.getLogger(PolicyJobListener.class);

    @Override
    public String getName() {
        return LISTENER_NAME; //must return a name
    }

    // Run this if job is about to be executed.
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("jobToBeExecuted");
        log.info("Job : " + jobName + " is going to start...");

    }

    // No idea when will run this?
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("jobExecutionVetoed");
    }

    //Run this after job has been executed
    @Override
    public void jobWasExecuted(JobExecutionContext context,
            JobExecutionException jobException) {
        log.debug("jobWasExecuted");
        String jobName = context.getJobDetail().getKey().toString();
        log.info("Job : " + jobName + " is finished...");

        if (jobException != null && !jobException.getMessage().equals("")) {
            log.error("Exception thrown by: " + jobName
                    + " Exception: " + jobException.getMessage());
        }

    }
}
