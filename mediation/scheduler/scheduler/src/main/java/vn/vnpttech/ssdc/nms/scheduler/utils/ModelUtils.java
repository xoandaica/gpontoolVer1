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

import vn.vnpttech.ssdc.nms.mediation.stbacs.services.DeviceInfo;
import vn.vnpttech.ssdc.nms.model.Device;
import vn.vnpttech.ssdc.nms.model.Policy;

/**
 *
 * @author longdq
 */
public class ModelUtils {

    public static DeviceInfo convertToServiceDevice(Device d,Policy p ) {
        DeviceInfo sd = new DeviceInfo();

        sd.setCpePassword(d.getCpePassword());
        sd.setCpu(d.getCpu());
        sd.setSerialNumber(d.getSerialNumber());
        sd.setMac(d.getMac());
        sd.setFirmwareVersion(d.getFirmwareVersion());
        sd.setRam(d.getRam());
        sd.setRom(d.getRom());
        sd.setId(d.getId());
        sd.setPolicyName(p.getName());
        return sd;
    }
}
