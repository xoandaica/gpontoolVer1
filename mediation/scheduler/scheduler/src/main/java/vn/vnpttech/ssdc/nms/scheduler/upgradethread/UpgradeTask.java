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
package vn.vnpttech.ssdc.nms.scheduler.upgradethread;

import java.sql.Timestamp;
import java.util.Calendar;
import org.apache.log4j.Logger;
import vn.vnpttech.ssdc.nms.mediation.stbacs.services.ACSServiceImplService;
import vn.vnpttech.ssdc.nms.model.Device;
import vn.vnpttech.ssdc.nms.model.Policy;
import vn.vnpttech.ssdc.nms.model.PolicyHistory;
import vn.vnpttech.ssdc.nms.scheduler.model.UpgradeInfo;
import vn.vnpttech.ssdc.nms.service.PolicyHistoryManager;
import vn.vnpttech.ssdc.nms.service.PolicyManager;

import vn.vnpttech.ssdc.nms.scheduler.utils.BeanUtils;
import vn.vnpttech.ssdc.nms.scheduler.utils.ConfigUtils;
import vn.vnpttech.ssdc.nms.scheduler.utils.ModelUtils;

/**
 *
 * @author longdq
 */
public class UpgradeTask extends Thread {

    private Logger log = Logger.getLogger(UpgradeTask.class);
    private UpgradeInfo u = null;
    private Policy p = null;
    private Device d = null;

    private static PolicyHistoryManager phManager = BeanUtils.getInstance().getBean("policyHistoryManager", PolicyHistoryManager.class);
    private static PolicyManager pManager = BeanUtils.getInstance().getBean("policyManager", PolicyManager.class);

    public UpgradeTask() {
    }

    public UpgradeTask(String name) {
        super(name);
    }

    public UpgradeTask(UpgradeInfo u) {
        this.u = u;
    }

    public UpgradeTask(UpgradeInfo u, Policy p) {
        this.u = u;
        this.p = p;
    }

    public UpgradeTask(UpgradeInfo u, Policy p, Device d) {
        this.u = u;
        this.p = p;
        this.d = d;
    }

    public void run() {
        PolicyHistory ph = new PolicyHistory();
        try {
            // String prefix = this.getName();
            //  log.debug(p.getId());
//            log.debug(this.getName() + " : " + u.toString());
//
//            ph.setDevice(d);
//            ph.setDescription("Seiral number: " + d.getSerialNumber() + " - Policy : " + p.getName());
//
//            ph.setFirmwareNewVersion(p.getFirmware().getVersion());
//            ph.setFirmwareOldVersion(d.getFirmwareVersion());
//            ph.setPolicy(p);
//            ph.setEndTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
//            ph.setStartTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
//            ph.setStatus(2);
//            ph = phManager.save(ph);
            // just for test. Upgrade firmware function will be called here.

            log.info("Task " + this.getName() + " : Upgrade firmware starting...... ");
//            Thread.sleep(30000);
            ACSServiceImplService acs = new ACSServiceImplService();
//            acs.getACSServiceImplPort().upgradeFirmware(d.getSerialNumber(), p.getFirmware().getFirmwarePath(), p.getFirmware().getVersion(), ConfigUtils.getUsernameOTA(), ConfigUtils.getPasswordOTA());
            acs.getACSServiceImplPort().upgradeFirmwareByPolicy(d.getSerialNumber(), p.getFirmware().getFirmwarePath(), p.getFirmware().getVersion(), ConfigUtils.getUsernameOTA(), ConfigUtils.getPasswordOTA(), p.getId(), ModelUtils.convertToServiceDevice(d, p));
            log.info("Task " + this.getName() + " : Upgrade firmware command was sent. ");

//            // end of call Upgrade firmware function
//            //update policy depend on the result returned by Upgrade function.
//            this.updatePolicy(p, true);
//            //update policy history
//            this.updatePolicyHistory(ph, true);
            //           log.info("Task " + this.getName() + " Done.");
        } catch (Exception e) {
            log.error(e.getMessage());
//            this.updatePolicy(p, false);
//            if (ph != null && ph.getId() != null) {
//                this.updatePolicyHistory(ph, false);
//            }
        }
    }
//---------------------- my function------------------------------
    //update policy counter after upgrade firmware

    private synchronized void updatePolicy(Policy po, boolean successFlag) {
        int success = po.getDeviceSuccessNo();
        int failed = po.getDeviceFailedNo();
        log.debug("policy ID : " + p.getId() + ".................");
        if (successFlag) {
            po.setDeviceSuccessNo(success + 1);
        } else {
            po.setDeviceFailedNo(failed + 1);
        }
        pManager.save(po);
    }

    /// update policy history status
    private synchronized void updatePolicyHistory(PolicyHistory ph, boolean successFlag) {
        log.debug("policy history ID : " + ph.getId() + ".................");
        ph.setEndTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        if (successFlag) {
            ph.setStatus(1);
        } else {
            ph.setStatus(0);
        }
        phManager.save(ph);
    }
}
