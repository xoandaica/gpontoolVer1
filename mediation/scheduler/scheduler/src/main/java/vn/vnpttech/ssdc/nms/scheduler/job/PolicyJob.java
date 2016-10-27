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
package vn.vnpttech.ssdc.nms.scheduler.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import vn.vnpttech.ssdc.nms.model.Device;
import vn.vnpttech.ssdc.nms.model.Policy;
import vn.vnpttech.ssdc.nms.scheduler.model.UpgradeInfo;
import vn.vnpttech.ssdc.nms.service.DeviceManager;
import vn.vnpttech.ssdc.nms.service.PolicyManager;
import vn.vnpttech.ssdc.nms.scheduler.upgradethread.STBThreadPool;
import vn.vnpttech.ssdc.nms.scheduler.upgradethread.UpgradeTask;
import vn.vnpttech.ssdc.nms.scheduler.utils.BeanUtils;

/**
 *
 * @author longdq
 */
public class PolicyJob implements Job {

    private static Logger log = Logger.getLogger(PolicyJob.class);
    private static PolicyManager policyManager = null;
    private static DeviceManager deviceManager = null;

    public static void getManager() {
        try {
            policyManager = BeanUtils.getInstance().getBean("policyManager", PolicyManager.class);
            deviceManager = BeanUtils.getInstance().getBean("deviceManager", DeviceManager.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            if (policyManager == null || deviceManager == null) {
                getManager();
            }
            log.debug("Job Policy is runing");
            //get all of policy to start
            List<Policy> lp = new ArrayList<Policy>();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            log.debug(dateFormat.format(date)); //2014/08/06 15:59:48
            Map<String, List> map = new HashMap<String, List>();
            // log.debug("Job Policy done !!!");
            lp = policyManager.getPolicyStart(dateFormat.format(date));
            List<UpgradeTask> uts = new ArrayList<UpgradeTask>();
            if (lp != null && lp.size() > 0) {
                //  get device of policy
                for (Policy p : lp) {
                    List<Device> ld = deviceManager.getDeviceOfPolicy(p);
                    if (ld != null && ld.size() > 0) {
                        for (Device d : ld) {
                            //check firmware version
                            if (!d.getFirmwareVersion().equalsIgnoreCase(p.getFirmware().getVersion())) {
                                UpgradeInfo u = new UpgradeInfo(d.getSerialNumber(), p.getFirmware().getFirmwarePath(), p.getFirmware().getVersion());
                                UpgradeTask ug = new UpgradeTask(u, p, d);
                                ug.setName(d.getSerialNumber());
                                uts.add(ug);
                                // ug.start();
                            }
                        }
                    }
                    //update policy status to running
                    p.setPolicyStatus(1);
                    policyManager.save(p); 

                }
            }

            STBThreadPool.TaskExcutor(uts);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
// my function
   
    
}
