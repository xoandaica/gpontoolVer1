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
package vn.vnpttech.ssdc.nms.scheduler.model;

/**
 *
 * @author longdq
 */
public class UpgradeInfo {

    public String serialNumber;
    public String firmwarePath;
    public String firmwareVersion;

    public UpgradeInfo(String serialNumber, String firmwarePath, String firmwareVersion) {
        this.serialNumber = serialNumber;
        this.firmwarePath = firmwarePath;
        this.firmwareVersion = firmwareVersion;
    }

    public UpgradeInfo() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFirmwarePath() {
        return firmwarePath;
    }

    public void setFirmwarePath(String firmwarePath) {
        this.firmwarePath = firmwarePath;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    @Override
    public String toString() {
        return (this.serialNumber + " : " + this.firmwarePath + " : " + this.firmwareVersion);
    }
}
