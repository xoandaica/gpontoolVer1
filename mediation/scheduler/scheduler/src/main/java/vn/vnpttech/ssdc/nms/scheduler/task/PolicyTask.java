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
package vn.vnpttech.ssdc.nms.scheduler.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

import vn.vnpttech.ssdc.nms.model.Device;
import vn.vnpttech.ssdc.nms.model.Policy;
import vn.vnpttech.ssdc.nms.scheduler.utils.BeanUtils;
import vn.vnpttech.ssdc.nms.scheduler.utils.ConfigUtils;
import vn.vnpttech.ssdc.nms.service.DeviceManager;
import vn.vnpttech.ssdc.nms.service.PolicyManager;
import vn.vnpttech.ssdc.nms.util.Constant;

/**
 *
 * @author longdq execute device task
 */
public class PolicyTask extends Thread {

    private static Logger log = Logger.getLogger(PolicyTask.class);
    private static DeviceManager deviceManager = null;
    private static PolicyManager policyManager = null;
    private Policy policy;

    public PolicyTask(String name) {
        super(name);
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    @Override
    public void run() {
        deviceManager = BeanUtils.getInstance().getBean("deviceManager", DeviceManager.class);
        policyManager = BeanUtils.getInstance().getBean("policyManager", PolicyManager.class);

        List<DeviceTask> deviceTasks = new ArrayList<DeviceTask>();

        // Lấy toàn bộ các device cần thực hiện
        List<Device> ld = null;
//        if (policy.getPolicyStatus() == Constant.POLICY_STATUS_NOT_RUN) {
//            ld = deviceManager.getDeviceOfPolicy(policy);
//        } else if (policy.getPolicyStatus() == Constant.POLICY_STATUS_RUNNING || policy.getPolicyStatus() == Constant.POLICY_STATUS_DONE) {
//            ld = deviceManager.getDeviceFailedOfPolicy(policy);
//        }
        ld = deviceManager.getDeviceNotUpdate(policy);
        log.debug("number of device: " + ld.size() + "-------");
        //update policy status to running
        policy.setPolicyStatus(1);
        policyManager.save(policy);
        if (ld != null && ld.size() > 0) {
            log.info("Devices of policy : " + policy.getName() +" = " + ld.size());
            for (Device d : ld) {
                //check firmware version
                if (!d.getFirmwareVersion().equalsIgnoreCase(policy.getFirmware().getVersion())) {
                    DeviceTask dt = new DeviceTask("Policy : " + policy.getId() + " Device : " + d.getSerialNumber());
                    dt.setDevice(d);
                    dt.setPolicy(policy);
                    deviceTasks.add(dt);
                }
            }
            log.info("Number of device will be updated firmware: " + deviceTasks.size() );
            // DeviceExecutor.DeviceExecute(deviceTasks,policy);
            try {
                int poolSize = ConfigUtils.getPoolSize();
                ExecutorService executor = Executors.newFixedThreadPool(poolSize);
                for (DeviceTask task : deviceTasks) {
                    executor.execute(task);
                }
                executor.shutdown();
                while (!executor.isTerminated()) {
                }
                //udate policy status
                Policy p = policyManager.get(policy.getId());
                p.setPolicyStatus(Constant.POLICY_STATUS_DONE);// Update Policy status
                policyManager.save(p);
                log.info("Finished all device tasks ");

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            log.info("Number of device = 0");
        }

    }

}
