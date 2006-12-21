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
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.util.StringUtils;

/**
 * Http Transport specifically for the Sail Data Service. This implementation is
 * using the Jakarta Commons HttpClient package. See http://jakarta.apache.org/commons/httpclient/
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HttpRestTransportImpl implements HttpRestTransport {

  private HttpClient client = null;

  /**
   * Constructs a newly allocated HttpRestTransportImpl object.
   */
  public HttpRestTransportImpl() {
    this.client = new HttpClient();
  }

  /**
   * @see net.sf.sail.webapp.domain.webservice.http.HttpRestTransport#post(net.sf.sail.webapp.domain.webservice.http.HttpPostRequest)
   */
  public Map<String, String> post(HttpPostRequest httpData) {
    Map<String, String> requestHeaders = httpData.getRequestHeaders();
    Map<String, String> requestParameters = httpData.getRequestParameters();
    String bodyData = httpData.getBodyData();
    String url = httpData.getUrl();
    Integer expectedStatusCode = httpData.getExpectedResponseStatusCode();

    // Create a method instance.
    PostMethod method = new PostMethod(url);

    // set headers
    if (requestHeaders != null && !requestHeaders.isEmpty()) {
      Set<String> keys = requestHeaders.keySet();
      for (Iterator<String> i = keys.iterator(); i.hasNext();) {
        String key = i.next();
        method.addRequestHeader(key, requestHeaders.get(key));
      }
    }

    // set body data
    if (StringUtils.hasText(bodyData)) {
      method.setRequestEntity(new StringRequestEntity(bodyData));
    }

    // set parameters
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
      int statusCode = client.executeMethod(method);

      if (statusCode != expectedStatusCode) {
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