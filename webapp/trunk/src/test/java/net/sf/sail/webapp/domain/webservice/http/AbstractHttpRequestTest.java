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

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 * 
 */
import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.impl.AbstractHttpRestCommand;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.easymock.EasyMock;

public class AbstractHttpRequestTest extends TestCase {

	private static final String URL = "/curnit";

	private AbstractHttpRequest request;

	private HttpMethod method;

	protected void setUp() throws Exception {
		super.setUp();
		request = new HttpGetRequest(
				AbstractHttpRestCommand.REQUEST_HEADERS_ACCEPT,
				AbstractHttpRestCommand.EMPTY_STRING_MAP, URL, HttpStatus.SC_OK);
		method = EasyMock.createMock(HttpMethod.class);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		request = null;
		method = null;
	}

	public void testIsValidResponseStatus() throws Exception {
		EasyMock.replay(method);
		assertTrue(request.isValidResponseStatus(method, request
				.getExpectedResponseStatusCode()));
		EasyMock.verify(method);
	}

	public void testIsValidResponseStatus_shouldThrowBadRequestException()
			throws Exception {
		EasyMock.expect(method.getStatusText()).andReturn("whatever")
				.anyTimes();
		EasyMock.expect(method.getResponseBodyAsString()).andReturn("whatever")
				.anyTimes();
		EasyMock.replay(method);
		try {
			request.isValidResponseStatus(method, HttpStatus.SC_NOT_FOUND);
			fail("Expected BadRequestException to be thrown");
		} catch (BadRequestException e) {
		}
		EasyMock.verify(method);
	}

	public void testIsValidResponseStatus_shouldThrowNetworkTransportException()
			throws Exception {
		EasyMock.expect(method.getStatusText()).andReturn("whatever")
				.anyTimes();
		EasyMock.expect(method.getResponseBodyAsString()).andReturn("whatever")
				.anyTimes();
		EasyMock.replay(method);
		try {
			request.isValidResponseStatus(method, HttpStatus.SC_BAD_GATEWAY);
			fail("Expected NetworkTransportException to be thrown");
		} catch (NetworkTransportException e) {
		}
		EasyMock.verify(method);
	}

}
