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

import java.util.Collections;
import java.util.Map;

/**
 * Immutable and thread-safe class to encapsulate data required for a post
 * request (headers, parameters, body, url and expected response).
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public final class HttpPostRequest {

  private Map<String, String> requestHeaders;

  private Map<String, String> requestParameters;

  private String bodyData;

  private String url;

  private int expectedResponseStatusCode;

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
   */
  public HttpPostRequest(final Map<String, String> requestHeaders,
      final Map<String, String> requestParameters, final String bodyData,
      final String url, final int expectedResponseStatusCode) {
    this.requestHeaders = Collections.unmodifiableMap(requestHeaders);
    this.requestParameters = Collections.unmodifiableMap(requestParameters);
    this.bodyData = bodyData;
    this.url = url;
    this.expectedResponseStatusCode = expectedResponseStatusCode;
  }

  /*
   * This is intentionally private - to make the HttpPostRequest object
   * immutable.
   */
  private HttpPostRequest() {
  }

  /**
   * Returns the expected response status code for this request.
   * 
   * @return the expectedResponseStatusCode
   */
  public int getExpectedResponseStatusCode() {
    return expectedResponseStatusCode;
  }

  /**
   * Returns the body data for this request.
   * 
   * @return the bodyData
   */
  public String getBodyData() {
    return bodyData;
  }

  /**
   * Returns the request headers for this request.
   * 
   * @return the requestHeaders
   */
  public Map<String, String> getRequestHeaders() {
    return requestHeaders;
  }

  /**
   * Returns the request parameters for this request.
   * 
   * @return the requestParameters
   */
  public Map<String, String> getRequestParameters() {
    return requestParameters;
  }

  /**
   * Returns the target URL for this request.
   * 
   * @return the url
   */
  public String getUrl() {
    return url;
  }
}