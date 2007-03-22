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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.AbstractHttpRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpGetRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
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

    private static final Log LOGGER = LogFactory
            .getLog(HttpRestTransportImpl.class);

    private String baseUrl;

    private HttpClient client;

    /**
     * Constructs a newly allocated HttpRestTransportImpl object.
     */
    public HttpRestTransportImpl() {
        // Must manually release the connection by calling releaseConnection()
        // on the method, otherwise there will be a resource leak. Refer to
        // http://jakarta.apache.org/commons/httpclient/threading.html
        this.client = new HttpClient(new MultiThreadedHttpConnectionManager());
    }

    /**
     * @see net.sf.sail.webapp.domain.webservice.http.HttpRestTransport#getBaseUrl()
     */
    public String getBaseUrl() {
        return this.baseUrl;
    }

    /**
     * @param baseUrl
     *            the baseUrl to set
     */
    @Required
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @see net.sf.sail.webapp.domain.webservice.http.HttpRestTransport#get(net.sf.sail.webapp.domain.webservice.http.HttpGetRequest)
     */
    public InputStream get(final HttpGetRequest httpGetRequestData) {
        // add parameters to URL
        Map<String, String> requestParameters = httpGetRequestData
                .getRequestParameters();
        StringBuffer buffer = new StringBuffer(this.baseUrl);
        buffer.append(httpGetRequestData.getUrl());
        if (requestParameters != null && !requestParameters.isEmpty()) {
            buffer.append('?');
            Set<String> keys = requestParameters.keySet();
            for (String key : keys) {
                buffer.append(key).append('=').append(
                        requestParameters.get(key)).append('&');
            }
            buffer.deleteCharAt(buffer.length() - 1);
        }

        GetMethod method = new GetMethod(buffer.toString());

        this.setHeaders(httpGetRequestData, method);
        try {
            // Execute the method.
            logRequest(method, "");
            int statusCode = this.client.executeMethod(method);
            verifyResponseStatus(httpGetRequestData, method, statusCode);
            return new ByteArrayInputStream(method.getResponseBody());
        } catch (HttpException e) {
            logAndThrow(e);
        } catch (IOException e) {
            logAndThrow(e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    /**
     * @see net.sf.sail.webapp.domain.webservice.http.HttpRestTransport#post(net.sf.sail.webapp.domain.webservice.http.HttpPostRequest)
     */
    public Map<String, String> post(final HttpPostRequest httpPostRequestData) {
        final PostMethod method = new PostMethod(this.baseUrl
                + httpPostRequestData.getUrl());

        this.setHeaders(httpPostRequestData, method);

        // set body data
        final String bodyData = httpPostRequestData.getBodyData();
        if (StringUtils.hasText(bodyData)) {
            method.setRequestEntity(new StringRequestEntity(bodyData));
        }

        // set parameters
        final Map<String, String> requestParameters = httpPostRequestData
                .getRequestParameters();
        if (requestParameters != null && !requestParameters.isEmpty()) {
            final Set<String> keys = requestParameters.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                String key = i.next();
                method.addParameter(key, requestParameters.get(key));
            }
        }

        final Map<String, String> responseHeaders = new HashMap<String, String>();
        try {
            // Execute the method.
            logRequest(method, bodyData);
            final int statusCode = this.client.executeMethod(method);
            verifyResponseStatus(httpPostRequestData, method, statusCode);
            final Header[] headers = method.getResponseHeaders();
            for (int i = 0; i < headers.length; i++) {
                responseHeaders
                        .put(headers[i].getName(), headers[i].getValue());
            }
        } catch (HttpException e) {
            logAndThrow(e);
        } catch (IOException e) {
            logAndThrow(e);
        } finally {
            method.releaseConnection();
        }

        return responseHeaders;
    }

    private void logRequest(HttpMethod method, String bodyData) throws URIException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(method.getName() + ": " + method.getURI());
            if (bodyData != "") LOGGER.info(method.getName() + ": " + bodyData);
        }
    }

    private void logAndThrow(Exception e) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(e.getMessage(), e);
        }
        throw new NetworkTransportException(e.getMessage());
    }

    private void verifyResponseStatus(
            final AbstractHttpRequest httpRequestData, HttpMethod method,
            int statusCode) throws IOException {
        if (statusCode != httpRequestData.getExpectedResponseStatusCode()) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn(statusCode + ": " + method.getStatusText());
                LOGGER.warn("body: " + method.getResponseBodyAsString());
            }
            if (statusCode == HttpStatus.SC_NOT_FOUND) {
                throw new BadRequestException(method.getStatusText());
            } else {
                throw new NetworkTransportException(statusCode, method
                        .getStatusText());
            }
        }
    }

    private void setHeaders(final AbstractHttpRequest httpRequestData,
            HttpMethodBase method) {
        final Map<String, String> requestHeaders = httpRequestData
                .getRequestHeaders();
        if (requestHeaders != null && !requestHeaders.isEmpty()) {
            Set<String> keys = requestHeaders.keySet();
            for (String key : keys) {
                method.addRequestHeader(key, requestHeaders.get(key));
            }
        }
    }
}