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

import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.context.ApplicationContext;

/**
 * The command which creates a user for the Sail Data Service (uses Http REST).
 * 
 * @author Cynick Young
 * 
 * @version $Id: SdsUserCreateCommandHttpRestImpl.java 69 2007-01-14 17:30:00Z
 *          cynick $
 * 
 */
public class SdsUserCreateCommandHttpRestImpl extends
		AbstractSdsCommandHttpRest<HttpPostRequest, SdsUser, SdsUser> {

	private static final String HEADER_CONTENT_TYPE = "Content-Type";

	private static Map<String, String> REQUEST_HEADERS = new HashMap<String, String>(
			1);
	static {
		REQUEST_HEADERS.put(HEADER_CONTENT_TYPE, APPLICATION_XML);
		REQUEST_HEADERS = Collections.unmodifiableMap(REQUEST_HEADERS);
	}

	/**
	 * @see net.sf.sail.webapp.dao.sds.SdsCommand#generateRequest(net.sf.sail.webapp.domain.sds.SdsObject)
	 */
	@SuppressWarnings("unchecked")
	public HttpPostRequest generateRequest(final SdsUser sdsUser) {
		final String bodyData = "<user><first-name>" + sdsUser.getFirstName()
				+ "</first-name><last-name>" + sdsUser.getLastName()
				+ "</last-name></user>";

		final String url = this.baseUrl + this.portalId + SLASH + "user";

		this.httpRequest = new HttpPostRequest(REQUEST_HEADERS,
				Collections.EMPTY_MAP, bodyData, url, HttpStatus.SC_CREATED);

		return this.httpRequest;
	}

	/**
	 * @see net.sf.sail.webapp.dao.sds.SdsCommand#execute(net.sf.sail.webapp.domain.sds.SdsObject)
	 */
	public SdsUser execute(final SdsUser sdsUser) {
		assert (this.httpRequest != null);
		final Map<String, String> responseHeaders = this.transport
				.post(this.httpRequest);
		final String locationHeader = responseHeaders.get("Location");
		sdsUser.setSdsObjectId(new Integer(locationHeader
				.substring(locationHeader.lastIndexOf(SLASH) + 1)));
		return sdsUser;
	}

	/**
	 * @see net.sf.sail.webapp.dao.sds.SdsCommand#execute(org.springframework.context.ApplicationContext, net.sf.sail.webapp.domain.sds.SdsObject)
	 */
	public SdsUser execute(ApplicationContext applicationContext,
			SdsUser sdsObject) {
		throw new UnsupportedOperationException();
	}
}