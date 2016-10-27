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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import vn.vnpttech.ssdc.nms.model.Policy;
import vn.vnpttech.ssdc.nms.scheduler.task.PolicyTask;
import vn.vnpttech.ssdc.nms.scheduler.upgradethread.PolicyExecutor;
import vn.vnpttech.ssdc.nms.scheduler.utils.BeanUtils;
import vn.vnpttech.ssdc.nms.service.PolicyManager;

/**
 *
 * @author longdq
 */
public class PolicyJob2 implements Job {

    private static Logger log = Logger.getLogger(PolicyJob2.class);
    private static PolicyManager policyManager = null;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        policyManager = BeanUtils.getInstance().getBean("policyManager", PolicyManager.class);
        List<Policy> lp = new ArrayList<Policy>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        lp = policyManager.getPolicyStart(dateFormat.format(date));
        if (!CollectionUtils.isEmpty(lp)) {
            log.info("Number of policy = " + lp.size());
            List<Runnable> policyTasks = new ArrayList<Runnable>();
            for (Policy p : lp) {
                PolicyTask pt = new PolicyTask("Policy : " + p.getId() + ", Name : " + p.getName());
                pt.setPolicy(p);
                policyTasks.add(pt);
            }
            PolicyExecutor.PolicyExecute(policyTasks);
        } else {
            log.info("Number of policy = 0");
        }

    }

}
