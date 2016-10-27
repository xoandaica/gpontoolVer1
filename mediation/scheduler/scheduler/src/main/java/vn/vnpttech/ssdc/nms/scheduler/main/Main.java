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
package vn.vnpttech.ssdc.nms.scheduler.main;

import java.sql.Timestamp;
import java.util.Date;
import org.apache.log4j.Logger;
import vn.vnpttech.ssdc.nms.model.Device;
import vn.vnpttech.ssdc.nms.model.Policy;
import vn.vnpttech.ssdc.nms.model.PolicyHistory;
import static vn.vnpttech.ssdc.nms.scheduler.job.PolicyJob.getManager;
import vn.vnpttech.ssdc.nms.scheduler.job.SchedulerTrigger;
import vn.vnpttech.ssdc.nms.scheduler.utils.BeanUtils;
import vn.vnpttech.ssdc.nms.service.DeviceManager;
import vn.vnpttech.ssdc.nms.service.PolicyHistoryManager;
import vn.vnpttech.ssdc.nms.service.PolicyManager;

/**
 *
 * @author longdq
 */
public class Main {

    private static Logger log = Logger.getLogger(SchedulerTrigger.class);
   // private static PolicyManager policyHistoryManager = null;
    private static PolicyHistoryManager policyHistoryManager = null;
    private static DeviceManager deviceManager = null;

    public static void getManager() {
        try {
            policyHistoryManager = BeanUtils.getInstance().getBean("policyHistoryManager", PolicyHistoryManager.class);
            deviceManager = BeanUtils.getInstance().getBean("deviceManager", DeviceManager.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static void main(String[] args) {

        if (policyHistoryManager == null || deviceManager == null) {
            getManager();
        }
        PolicyHistory policyHis = new PolicyHistory();
        policyHis.setStartTime(new Timestamp(new Date().getTime()));
        policyHis.setDescription("Update firmware by Policy ");
        policyHis.setDeviceSerialNumber("123456");
        policyHis.setEndTime(null);

        Policy p = new Policy();
        p.setId(1L);

        Device d = new Device();
        d.setId(100L);

        policyHis.setDevice(d);
        policyHis.setPolicy(p);
        policyHis.setFirmwareOldVersion("v1bc");
        policyHis.setStatus(2);

        policyHis = policyHistoryManager.save(policyHis);

        System.out.println(policyHis.getPolicy().getName());
    }

}
