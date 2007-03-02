/**
 * 
 */
package net.sf.sail.webapp.dao.sds.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.easymock.EasyMock;

/**
 * @author Hiroki Terashima
 *
 */
public class SdsWorkgroupModifyCommandHttpRestImplTest extends TestCase {
	private static final String HEADER_LOCATION = "Location";

	private static final String PORTAL_URL = "http://portal/url";

	private static final String WORKGROUP_DIRECTORY = "/workgroup";
	
	private static final String MEMBERSHIP_DIRECTORY = "/membership";

	private static final String SOME_NAME = "pineapples";

	private static final Integer EXPECTED_ID = new Integer(12);

	private SdsWorkgroupModifyCommandHttpRestImpl command;

	private HttpRestTransport mockTransport;

	private HttpPostRequest request;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.command = new SdsWorkgroupModifyCommandHttpRestImpl();
		this.mockTransport = EasyMock.createMock(HttpRestTransport.class);
		this.command.setTransport(this.mockTransport);
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		this.command = null;
		this.request = null;
	}

	public void testExecute_Success() throws Exception {
		Map<String, String> responseMap = new HashMap<String, String>();
		responseMap.put(HEADER_LOCATION, PORTAL_URL + WORKGROUP_DIRECTORY + "/"
				+ EXPECTED_ID + MEMBERSHIP_DIRECTORY);

		SdsWorkgroup existingWorkgroup = new SdsWorkgroup();
		existingWorkgroup.setName(SOME_NAME);
		SdsOffering existingOffering = new SdsOffering();
		Integer existingOfferingId = new Integer(42);
		existingOffering.setSdsObjectId(existingOfferingId);
		existingWorkgroup.setSdsOffering(existingOffering);

		SdsUser existingUser1 = new SdsUser();
		Integer existingUser1Id = new Integer(10);
		existingUser1.setSdsObjectId(existingUser1Id);
		SdsUser existingUser2 = new SdsUser();
		Integer existingUser2Id = new Integer(11);
		existingUser2.setSdsObjectId(existingUser2Id);
		
		Set<SdsUser> existingUserArray = new HashSet<SdsUser>();
		existingUserArray.add(existingUser1);
		existingUserArray.add(existingUser2);
		existingWorkgroup.setMembership(existingUserArray);
		this.command.setWorkgroup(existingWorkgroup);

		this.request = this.command.generateRequest();
		EasyMock.expect(this.mockTransport.post(this.request)).andReturn(
				responseMap);
		EasyMock.replay(this.mockTransport);

		SdsWorkgroup actual = this.command.execute(this.request);
		assertEquals(EXPECTED_ID, actual.getSdsObjectId());

		EasyMock.verify(this.mockTransport);
	}
	  
	public void testExecute_Exceptions() throws Exception {
		EasyMock.expect(this.mockTransport.post(this.request)).andThrow(
				new BadRequestException("exception"));
		EasyMock.replay(this.mockTransport);
		try {
			this.command.execute(this.request);
			fail("Expected BadRequestException");
		}
		catch (BadRequestException e) {
		}
		EasyMock.verify(this.mockTransport);

		EasyMock.reset(this.mockTransport);
		EasyMock.expect(this.mockTransport.post(this.request)).andThrow(
				new NetworkTransportException("exception"));
		EasyMock.replay(this.mockTransport);
		try {
			this.command.execute(this.request);
			fail("Expected NetworkTransportException");
		}
		catch (NetworkTransportException e) {
		}
		EasyMock.verify(this.mockTransport);
	}
}
