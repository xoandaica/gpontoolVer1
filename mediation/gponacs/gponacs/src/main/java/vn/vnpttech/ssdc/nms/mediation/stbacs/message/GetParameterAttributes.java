/*
 * 
 * Copyright 2007-2012 Audrius Valunas
 * 
 * This file is part of OpenACS.

 * OpenACS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OpenACS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with OpenACS.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package vn.vnpttech.ssdc.nms.mediation.stbacs.message;

import vn.vnpttech.ssdc.nms.mediation.stbacs.Message;

import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

public class GetParameterAttributes extends Message {

    /** Creates a new instance of GetParameterAttributes */
    public GetParameterAttributes() {
        name = "GetParameterAttributes";
    }

    protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
        SOAPElement elm = body.addChildElement(spf.createName("ParameterNames"));
        elm.setAttribute(SOAP_ARRAY_TYPE, "xsd:string[" + String.valueOf(parameterNames.length) + "]");
        for (int i = 0; i < parameterNames.length; i++) {
            elm.addChildElement("string").setValue(parameterNames[i]);
        }
    }

    protected void parseBody(SOAPBodyElement body, SOAPFactory f) throws SOAPException {
    }
    public String parameterNames[];
}
