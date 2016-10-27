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

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import vn.vnpttech.ssdc.nms.model.Policy;
import vn.vnpttech.ssdc.nms.scheduler.utils.BeanUtils;
import vn.vnpttech.ssdc.nms.service.DeviceManager;
import vn.vnpttech.ssdc.nms.service.PolicyManager;
import vn.vnpttech.ssdc.nms.xmpp.connector.dao.UsersDao;
import vn.vnpttech.ssdc.nms.xmpp.connector.dao.UsersDaoImpl;
import vn.vnpttech.ssdc.nms.xmpp.model.Users;

/**
 *
 * @author longdq
 */
public class Test2DB {

    private static Logger log = Logger.getLogger(Test2DB.class);
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

    public static void main(String[] args) {
        PropertyConfigurator.configure("../etc/log4j.conf");
        getManager();
        

        //using core module
        Policy lp = policyManager.get(2L);

        if (lp == null) {
            log.info("Cannot run core module");
        } else {
            log.info(lp.getName()+ "----------------------");
        }
        UsersDao ud = new UsersDaoImpl(Users.class);
        Users u = ud.getUserByUserName("vunb");
        if (u != null) {
            log.info(u.getUsername() + "------------------ usernaem");
        } else {
            log.info("Users of xmpp is null");
        }
        
//        List<Device> lp = deviceManager.getAll();
//
//        if (lp != null) {
//            log.info("Cannot run core module");
//        } else {
//            log.info(lp.size() + "----------------------");
//        }
    }
}
