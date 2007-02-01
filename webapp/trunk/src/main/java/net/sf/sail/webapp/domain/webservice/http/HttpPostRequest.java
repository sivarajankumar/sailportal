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
package net.sf.sail.webapp.domain.webservice.http;

import java.util.Map;

import net.sf.sail.webapp.domain.webservice.BadRequestException;

/**
 * Immutable and thread-safe class to encapsulate data required for a post
 * request (headers, parameters, body, url and expected response).
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public final class HttpPostRequest extends AbstractHttpRequest {

  private String bodyData;

  /**
   * Creates an HttpPostRequest object with all of the data required.
   * 
   * @param requestHeaders
   *          is a map of HTTP request headers
   * @param requestParameters
   *          is a map of HTTP request parameters
   * @param bodyData
   *          is the serialized string of the body of a POST request
   * @param url
   *          is the target URL for this request
   * @param expectedResponseStatusCode
   *          is the HTTP status code that is expected to be returned by the
   *          server
   * @throws BadRequestException
   *           if the request headers contain any illegal characters either in
   *           the request field name or the request field value
   */
  public HttpPostRequest(final Map<String, String> requestHeaders,
      final Map<String, String> requestParameters, final String bodyData,
      final String url, final int expectedResponseStatusCode)
      throws BadRequestException {

    super(requestHeaders, requestParameters, url, expectedResponseStatusCode);
    this.bodyData = bodyData;
  }

  /*
   * This is intentionally private - to make the HttpPostRequest object
   * immutable.
   */
  private HttpPostRequest() {
  }

  /**
   * Returns the body data for this request.
   * 
   * @return the bodyData
   */
  public String getBodyData() {
    return bodyData;
  }
}