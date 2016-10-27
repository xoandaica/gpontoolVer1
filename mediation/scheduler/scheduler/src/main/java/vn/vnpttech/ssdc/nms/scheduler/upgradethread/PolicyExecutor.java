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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import static vn.vnpttech.ssdc.nms.scheduler.upgradethread.STBThreadPool.executor;
import vn.vnpttech.ssdc.nms.scheduler.utils.ConfigUtils;

/**
 *
 * @author longdq
 */
public class PolicyExecutor {

    private static int poolSize = 300;
    private static Logger log = Logger.getLogger(DeviceExecutor.class);

    public static void PolicyExecute(List<Runnable> deviceTasks) {
        try {
            poolSize = ConfigUtils.getPoolSize();
            ExecutorService executor = Executors.newFixedThreadPool(poolSize);
            for (Runnable task : deviceTasks) {
                executor.execute(task);
            }
            executor.shutdown();
            while (!executor.isTerminated()) {
            }

            log.info("Finished all policy tasks ");

        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
