/*
 * Copyright 2014 VNPT-Technology. All rights reserved.
 * VNPT-Technology PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package vn.vnpttech.ssdc.nms.mediation.stbacs.ws.response;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import vn.vnpttech.ssdc.nms.mediation.stbacs.myobject.WirelessObject;

/**
 *
 * @author Vunb
 * @date Oct 21, 2014
 * @update Oct 21, 2014
 */
public class WirelessObjectResponse extends TemplResponse<WirelessObject> {

    @Override
    @XmlElementWrapper(name = "Results")
    @XmlElement(name = "WirelessObject")
    public ArrayList<WirelessObject> getRetValue() {
        return super.getRetValue();
    }
}
