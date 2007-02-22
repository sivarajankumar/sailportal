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
package net.sf.sail.webapp.dao.sds.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.sail.webapp.dao.sds.SdsWorkgroupCreateCommand;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.apache.commons.httpclient.HttpStatus;

/**
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class SdsWorkgroupCreateCommandHttpRestImpl implements
    SdsWorkgroupCreateCommand {

  private SdsWorkgroup workgroup;

  private HttpRestTransport transport;

  /**
   * @see net.sf.sail.webapp.dao.sds.SdsWorkgroupCreateCommand#setTransport(net.sf.sail.webapp.domain.webservice.http.HttpRestTransport)
   */
  public void setTransport(HttpRestTransport transport) {
    this.transport = transport;
  }

  /**
   * @see net.sf.sail.webapp.dao.sds.SdsWorkgroupCreateCommand#setWorkgroup(net.sf.sail.webapp.domain.sds.SdsWorkgroup)
   */
  public void setWorkgroup(SdsWorkgroup workgroup) {
    this.workgroup = workgroup;
  }

  /**
   * @see net.sf.sail.webapp.dao.sds.SdsCommand#execute(net.sf.sail.webapp.domain.webservice.http.AbstractHttpRequest)
   */
  public SdsWorkgroup execute(HttpPostRequest httpRequest) {
    final Map<String, String> responseHeaders = this.transport
        .post(httpRequest);
    final String locationHeader = responseHeaders.get("Location");
    this.workgroup.setSdsObjectId(new Integer(locationHeader
        .substring(locationHeader.lastIndexOf("/") + 1)));

    return this.workgroup;
  }

  /**
   * @see net.sf.sail.webapp.dao.sds.SdsCommand#generateRequest()
   */
  @SuppressWarnings("unchecked")
  public HttpPostRequest generateRequest() {
    Map<String, String> requestHeaders = new HashMap<String, String>(1);
    requestHeaders.put("Content-Type", "application/xml");
    String name = this.workgroup.getName();
    String bodyData = "<workgroup><name>" + name + "</name><offering-id>"
        + this.workgroup.getSdsOffering().getSdsObjectId()
        + "</offering-id></workgroup>";
    final String url = "workgroup";
    HttpPostRequest request = new HttpPostRequest(requestHeaders,
        Collections.EMPTY_MAP, bodyData, url, HttpStatus.SC_CREATED);

    return request;
  }

}
