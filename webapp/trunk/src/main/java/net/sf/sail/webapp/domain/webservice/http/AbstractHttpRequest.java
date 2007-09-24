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
package net.sf.sail.webapp.domain.webservice.http;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Encapsulates Http request information.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public abstract class AbstractHttpRequest {
	
    private Log logger;

	protected Map<String, String> requestHeaders;

	protected Map<String, String> requestParameters;

	protected String relativeUrl;

	protected int expectedResponseStatusCode;

	/**
	 * Creates an AbstractHttpRequest object with all of the data required.
	 * 
	 * @param requestHeaders
	 *            is a map of HTTP request headers
	 * @param requestParameters
	 *            is a map of HTTP request parameters
	 * @param relativeUrl
	 *            is the target relative URL for this request
	 * @param expectedResponseStatusCode
	 *            is the HTTP status code that is expected to be returned by the
	 *            server
	 * @throws BadRequestException
	 *             if the request headers contain any illegal characters either
	 *             in the request field name or the request field value
	 */
	protected AbstractHttpRequest(final Map<String, String> requestHeaders,
			final Map<String, String> requestParameters,
			final String relativeUrl, final int expectedResponseStatusCode)
			throws BadRequestException {

        this.logger = LogFactory.getLog(this.getClass());
		this.checkForLegalHeaders(requestHeaders);
		this.requestHeaders = Collections.unmodifiableMap(requestHeaders);
		this.requestParameters = Collections.unmodifiableMap(requestParameters);
		this.relativeUrl = relativeUrl;
		this.expectedResponseStatusCode = expectedResponseStatusCode;
	}

	/**
	 * DO NOT USE THIS METHOD
	 */
	protected AbstractHttpRequest() {
		throw new UnsupportedOperationException();
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
	 * @return the relativeUrl
	 */
	public String getRelativeUrl() {
		return relativeUrl;
	}

	private static final Pattern ILLEGAL_HEADER_FIELD_NAME_PATTERN = Pattern
			.compile("(.*[\\p{Cntrl}\t ()<>@,;:\\\"/\u001B\u001D?={}]+.*)+");

	private static final Pattern ILLEGAL_HEADER_FIELD_VALUE_PATTERN = Pattern
			.compile("(.*[\\p{Cntrl}]+.*)+");

	protected void checkForLegalHeaders(final Map<String, String> requestHeaders)
			throws BadRequestException {
		for (String key : requestHeaders.keySet()) {
			if ("".equals(key)
					|| ILLEGAL_HEADER_FIELD_NAME_PATTERN.matcher(key).matches()) {
				throw new BadRequestException(
						"Request header field-name contains illegal characters.");
			}
			if (ILLEGAL_HEADER_FIELD_VALUE_PATTERN.matcher(
					requestHeaders.get(key)).matches()) {
				throw new BadRequestException(
						"Request header field-value contains illegal characters.");
			}
		}
	}

	public boolean isValidResponseStatus(HttpMethod method, int statusCode)
			throws IOException {
		if (statusCode != this.getExpectedResponseStatusCode()) {
			if (logger.isWarnEnabled()) {
				logger.warn(statusCode + ": " + method.getStatusText());
				logger.warn("body: " + method.getResponseBodyAsString());
			}
			if (statusCode == HttpStatus.SC_NOT_FOUND) {
				throw new BadRequestException(method.getStatusText());
			} else {
				throw new NetworkTransportException(statusCode, method
						.getStatusText());
			}
		}
		else return true;
	}
}