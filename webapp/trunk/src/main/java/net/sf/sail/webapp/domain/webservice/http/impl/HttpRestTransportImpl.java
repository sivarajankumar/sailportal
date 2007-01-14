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
package net.sf.sail.webapp.domain.webservice.http.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.util.StringUtils;

/**
 * Thread-safe Http transport implementation which uses the Jakarta Commons
 * HttpClient package. See http://jakarta.apache.org/commons/httpclient/
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HttpRestTransportImpl implements HttpRestTransport {

  private HttpClient client;

  /**
   * Constructs a newly allocated HttpRestTransportImpl object.
   */
  public HttpRestTransportImpl() {
    // Must manually release the connection by calling releaseConnection() on
    // the method, otherwise there will be a resource leak.
    // Refer to http://jakarta.apache.org/commons/httpclient/threading.html
    this.client = new HttpClient(new MultiThreadedHttpConnectionManager());
  }

  /**
   * @see net.sf.sail.webapp.domain.webservice.http.HttpRestTransport#post(net.sf.sail.webapp.domain.webservice.http.HttpPostRequest)
   */
  public Map<String, String> post(HttpPostRequest httpPostRequestData) {
    // Create a method instance.
    PostMethod method = new PostMethod(httpPostRequestData.getUrl());

    // set headers
    Map<String, String> requestHeaders = httpPostRequestData
        .getRequestHeaders();
    if (requestHeaders != null && !requestHeaders.isEmpty()) {
      Set<String> keys = requestHeaders.keySet();
      for (Iterator<String> i = keys.iterator(); i.hasNext();) {
        String key = i.next();
        method.addRequestHeader(key, requestHeaders.get(key));
      }
    }

    // set body data
    String bodyData = httpPostRequestData.getBodyData();
    if (StringUtils.hasText(bodyData)) {
      method.setRequestEntity(new StringRequestEntity(bodyData));
    }

    // set parameters
    Map<String, String> requestParameters = httpPostRequestData
        .getRequestParameters();
    if (requestParameters != null && !requestParameters.isEmpty()) {
      Set<String> keys = requestParameters.keySet();
      for (Iterator<String> i = keys.iterator(); i.hasNext();) {
        String key = i.next();
        method.addParameter(key, requestParameters.get(key));
      }
    }

    Map<String, String> responseHeaders = new HashMap<String, String>();
    try {
      // Execute the method.
      int statusCode = this.client.executeMethod(method);

      if (statusCode != httpPostRequestData.getExpectedResponseStatusCode()) {
        if (statusCode == HttpStatus.SC_BAD_REQUEST) {
          throw new BadRequestException(method.getStatusText());
        }
        else {
          throw new NetworkTransportException(statusCode, method
              .getStatusText());
        }
      }
      Header[] headers = method.getResponseHeaders();
      for (int i = 0; i < headers.length; i++) {
        responseHeaders.put(headers[i].getName(), headers[i].getValue());
      }
    }
    catch (HttpException e) {
      throw new NetworkTransportException(e.getMessage());
    }
    catch (IOException e) {
      throw new NetworkTransportException(e.getMessage());
    }
    finally {
      // Release the connection.
      method.releaseConnection();
    }

    return responseHeaders;
  }
}