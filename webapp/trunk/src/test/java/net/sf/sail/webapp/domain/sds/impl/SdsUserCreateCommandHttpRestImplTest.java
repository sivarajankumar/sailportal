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
package net.sf.sail.webapp.domain.sds.impl;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.reset;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.easymock.EasyMock;

/**
 * @author Cynick Young
 * 
 * @version $Id: SdsUserCreateCommandHttpRestImplTest.java 61 2006-12-21
 *          17:49:35Z cynick $
 * 
 */
public class SdsUserCreateCommandHttpRestImplTest extends TestCase {

	private static final String BASE_URL = "base url";

	private static final Integer PORTAL_ID = 12;

	private static final Integer EXPECTED_ID = 1;

	private static final String USER_NAME = "user";

	private static final String PASSWORD = "pass";

	private static final String HEADER_LOCATION = "Location";

	private static final String PORTAL_URL = "http://rails.dev.concord.org/sds/";

	private static final String USER_DIRECTORY = "/user/";

	private SdsUserCreateCommandHttpRestImpl command = null;

	private HttpRestTransport mockTransport = null;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		command = new SdsUserCreateCommandHttpRestImpl();
		mockTransport = createMock(HttpRestTransport.class);
		command.setTransport(mockTransport);
		command.setBaseUrl(BASE_URL);
		command.setPortalId(PORTAL_ID);
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		command = null;
		mockTransport = null;
	}

	public void testCreate() throws Exception {
		MutableUserDetails userDetails = new HibernateUserDetails();
		userDetails.setUsername(USER_NAME);
		userDetails.setFirstName(USER_NAME);
		userDetails.setLastName(USER_NAME);
		userDetails.setPassword(PASSWORD);

		Map<String, String> responseMap = new HashMap<String, String>();
		responseMap.put(HEADER_LOCATION, PORTAL_URL + PORTAL_ID
				+ USER_DIRECTORY + EXPECTED_ID);
		expect(mockTransport.post(command.generateRequest(userDetails)))
				.andReturn(responseMap);
		EasyMock.replay(mockTransport);
		assertEquals(EXPECTED_ID, command.execute());
		EasyMock.verify(mockTransport);

		// TODO failure returns a HTTP/1.1 400 Bad Request, what is a bad
		// request?
	}

	public void testCreateException() throws Exception {
		MutableUserDetails userDetails = new HibernateUserDetails();
		userDetails.setUsername(USER_NAME);
		userDetails.setFirstName(USER_NAME);
		userDetails.setLastName("");
		userDetails.setPassword(PASSWORD);

		expect(mockTransport.post(command.generateRequest(userDetails)))
				.andThrow(new BadRequestException("exception"));
		EasyMock.replay(mockTransport);
		try {
			command.execute();
			fail("Expected BadRequestException");
		} catch (BadRequestException e) {
		}

		reset(mockTransport);
		expect(mockTransport.post(command.generateRequest(userDetails)))
				.andThrow(new NetworkTransportException("exception"));
		EasyMock.replay(mockTransport);
		try {
			command.execute();
			fail("Expected NetworkTransportException");
		} catch (NetworkTransportException e) {
		}

	}
}