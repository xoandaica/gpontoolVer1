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
package vn.vnpttech.ssdc.nms.scheduler.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author longdq
 */
public class ConfigUtils {

    private static Properties prop = new Properties();
    private final static String propFileName = "../etc/config.properties";
    private static InputStream inputStream = null;

    public ConfigUtils() {

    }

    public static void init() throws IOException {
        inputStream = new FileInputStream(propFileName);
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
    }

    public static String getInterval() throws IOException {
        // get the property value and print it out
        init();
        String interval = prop.getProperty("interval");
        if (StringUtils.isBlank(interval)) {
            interval = "40";
        }
        return interval;
    }

    public static int getPoolSize() throws IOException {
        init();
        String poolSizeStr = prop.getProperty("poolSize");
        int poolSize = 300;
        if (StringUtils.isNotBlank(poolSizeStr)) {
            poolSize = Integer.parseInt(poolSizeStr);
        }
        return poolSize;
    }

    public static String getUsernameOTA() throws IOException {
        init();
        String usernameOTA = prop.getProperty("usernameOTA");
        return usernameOTA;
    }
    public static String getPasswordOTA() throws IOException {
        init();
        String passwordOTA = prop.getProperty("passwordOTA");
        return passwordOTA;
    }

    public static void main(String[] args) {
        try {
            String interval = getInterval();
            System.out.println(interval);
        } catch (IOException iOException) {
            System.out.println(iOException.getMessage());
        }
    }
}
