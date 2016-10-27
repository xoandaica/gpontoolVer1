/*
 * Copyright 2014 VNPT-Technology. All rights reserved.
 * VNPT-Technology PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package vn.vnpttech.ssdc.nms.mediation.stbacs.ws.model;

/**
 *
 * @author Vunb
 * @date Aug 23, 2014
 * @update Aug 23, 2014
 */
public class DeviceInterface {

    private String name; // tham so InternetGatewayDevice.WANDevice.5.WANConnectionDevice.{i}.WANIPConnection.{i}.Name
    private String ifName; // tham so InternetGatewayDevice.WANDevice.5.WANConnectionDevice.{i}.WANIPConnection.{i}.X_BROADCOM_COM_IfName
    private String ifGroupKey; // --> FilterBridgeReference
    private String ifReference;
    private String ifType;
    private String ifKey;
    private String filterKey;
    private String externalIp;
    private String connectionType;
    private String vlanId;

    public String getIfGroupKey() {
        return ifGroupKey;
    }

    public void setIfGroupKey(String ifGroupKey) {
        this.ifGroupKey = ifGroupKey;
    }

    public String getIfReference() {
        return ifReference;
    }

    public void setIfReference(String ifReference) {
        this.ifReference = ifReference;
    }

    public String getIfType() {
        return ifType;
    }

    public void setIfType(String ifType) {
        this.ifType = ifType;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public String getIfKey() {
        return ifKey;
    }

    public void setIfKey(String ifKey) {
        this.ifKey = ifKey;
    }

    public String getIfName() {
        return ifName;
    }

    public void setIfName(String ifName) {
        this.ifName = ifName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalIp() {
        return externalIp;
    }

    public void setExternalIp(String externalIp) {
        this.externalIp = externalIp;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getVlanId() {
        return vlanId;
    }

    public void setVlanId(String vlanId) {
        this.vlanId = vlanId;
    }

}
