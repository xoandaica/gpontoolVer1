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
package vn.vnpttech.ssdc.nms.mediation.stbacs;

import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface ServicePropertyLocalHome extends EJBLocalHome {

    vn.vnpttech.ssdc.nms.mediation.stbacs.ServicePropertyLocal findByPrimaryKey(vn.vnpttech.ssdc.nms.mediation.stbacs.ServicePropertyPK key) throws FinderException;

    vn.vnpttech.ssdc.nms.mediation.stbacs.ServicePropertyLocal create(Integer serviceid, String name, String value) throws CreateException;

    Collection<ServicePropertyLocal> findByServiceId(Integer serviceid) throws FinderException;
}
