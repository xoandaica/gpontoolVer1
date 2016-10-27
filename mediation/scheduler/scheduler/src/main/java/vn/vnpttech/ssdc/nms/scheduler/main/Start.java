package vn.vnpttech.ssdc.nms.scheduler.main;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import vn.vnpttech.ssdc.nms.scheduler.job.SchedulerTrigger;
import vn.vnpttech.ssdc.nms.scheduler.job2.PolicyJob2Trigger;

/**
 * Start scheduler!
 *
 */
public class Start {

    private static Logger log = Logger.getLogger(Start.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("../etc/log4j.conf");
        log.debug("PolicyJob2Trigger starting........");
//        SchedulerTrigger.getInstance().init();
//        SchedulerTrigger.getInstance().startScheduler();
        PolicyJob2Trigger.getInstance().init();
        PolicyJob2Trigger.getInstance().startScheduler();
    }

}
