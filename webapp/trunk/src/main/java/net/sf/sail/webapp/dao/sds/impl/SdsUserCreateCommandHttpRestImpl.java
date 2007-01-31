/**
 * Copyright (c) 2006 Encore Research Group, University of Toronto
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

import net.sf.sail.webapp.dao.sds.SdsCommand;
import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.apache.commons.httpclient.HttpStatus;

/**
 * The command which creates a user for the Sail Data Services (uses Http REST).
 * 
 * @author Cynick Young
 * 
 * @version $Id: SdsUserCreateCommandHttpRestImpl.java 69 2007-01-14 17:30:00Z
 *          cynick $
 * 
 */
public class SdsUserCreateCommandHttpRestImpl implements SdsCommand {

  private HttpRestTransport transport;

  private String baseUrl;

  private Integer portalId;

  private HttpPostRequest postRequest;

  private static final String SLASH = "/";

  private static final String USER_DIRECTORY = "user";

  private static final String USER_CREATE_STRING_1 = "<user><first-name>";

  private static final String USER_CREATE_STRING_2 = "</first-name><last-name>";

  private static final String USER_CREATE_STRING_3 = "</last-name></user>";

  private static final String HEADER_CONTENT_TYPE = "Content-Type";

  private static final String HEADER_CONTENT_APPLICATION_XML = "application/xml";

  /**
   * Sets the base url for the website providing the Sail Data Service (i.e.
   * http://rails.dev.concord.org/sds/portal/)
   * 
   * @param baseUrl
   *          the baseUrl to set
   */
  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  /**
   * Sets the portal id assigned to the specific portal being accessed via the
   * Sail Data Services. This is simply an integer that has been assigned by
   * creating a new portal relationship (see
   * http://www.telscenter.org/confluence/display/SAIL/REST+protocol+for+SAIL+Data+Services+(SDS))
   * 
   * @param portalId
   *          the portalId to set
   */
  public void setPortalId(Integer portalId) {
    this.portalId = portalId;
  }

  /**
   * Sets the http REST transport mechanism for the create command.
   * 
   * @param transport
   *          the transport to set
   */
  public void setTransport(HttpRestTransport transport) {
    this.transport = transport;
  }

  private static Map<String, String> REQUEST_HEADERS = new HashMap<String, String>(
      1);
  static {
    REQUEST_HEADERS.put(HEADER_CONTENT_TYPE, HEADER_CONTENT_APPLICATION_XML);
    REQUEST_HEADERS = Collections.unmodifiableMap(REQUEST_HEADERS);
  }

  /**
   * @see net.sf.sail.webapp.dao.sds.SdsCommand#generateRequest(SdsUser)
   */
  @SuppressWarnings("unchecked")
  public HttpPostRequest generateRequest(SdsUser sdsUser) {
    String bodyData = USER_CREATE_STRING_1 + sdsUser.getFirstName()
        + USER_CREATE_STRING_2 + sdsUser.getLastName() + USER_CREATE_STRING_3;

    String url = this.baseUrl + this.portalId + SLASH + USER_DIRECTORY;

    this.postRequest = new HttpPostRequest(REQUEST_HEADERS,
        Collections.EMPTY_MAP, bodyData, url, HttpStatus.SC_CREATED);

    return this.postRequest;
  }

  /**
   * @see net.sf.sail.webapp.dao.sds.SdsCommand#execute(SdsUser)
   */
  public SdsUser execute(SdsUser sdsUser) {
    if (this.postRequest == null) {
      throw new BadRequestException(
          "The request is null. Call generateRequest() method prior to execute().");
    }
    Map<String, String> responseHeaders = this.transport.post(this.postRequest);
    String locationHeader = responseHeaders.get("Location");
    sdsUser.setSdsObjectId(new Integer(locationHeader.substring(locationHeader
        .lastIndexOf(SLASH) + 1)));
    return sdsUser;
  }
}