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

import org.apache.log4j.Logger;
import vn.vnpttech.ssdc.nms.mediation.stbacs.services.ACSServiceImplService;
import vn.vnpttech.ssdc.nms.model.Device;
import vn.vnpttech.ssdc.nms.model.Policy;
import vn.vnpttech.ssdc.nms.scheduler.utils.ConfigUtils;
import vn.vnpttech.ssdc.nms.scheduler.utils.ModelUtils;

/**
 *
 * @author longdeviceq
 */
public class DeviceTask extends Thread {

    private Device device;
    private Policy policy;

    private Logger log = Logger.getLogger(DeviceTask.class);
    // private String name = "DeviceTask";

    public DeviceTask(String name) {
        super(name);
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    @Override
    public void run() {
        try {
            log.info("Task " + this.getName() + " : Upgrade firmware starting...... ");
            ACSServiceImplService acs = new ACSServiceImplService();
            acs.getACSServiceImplPort().upgradeFirmwareByPolicy(device.getSerialNumber(), policy.getFirmware().getFirmwarePath(), policy.getFirmware().getVersion(), ConfigUtils.getUsernameOTA(), ConfigUtils.getPasswordOTA(), policy.getId(), ModelUtils.convertToServiceDevice(device,policy));
            log.info("Task " + this.getName() + " : Upgrade firmware command was sent. ");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
