/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.sf.sail.webapp.dao.sds;

import net.sf.sail.webapp.domain.sds.SdsWorkgroup;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

/**
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public interface SdsWorkgroupCreateCommand extends
    SdsCommand<SdsWorkgroup, HttpPostRequest> {

  /**
   * Set a new workgroup object that is used to create the SDS workgroup.
   * 
   * @param workgroup
   *          to be created on the actual SDS
   */
  public void setWorkgroup(SdsWorkgroup workgroup);

  /**
   * Set a http transport to perform the work.
   * 
   * @param transport
   *          to set
   */
  public void setTransport(HttpRestTransport transport);
}
