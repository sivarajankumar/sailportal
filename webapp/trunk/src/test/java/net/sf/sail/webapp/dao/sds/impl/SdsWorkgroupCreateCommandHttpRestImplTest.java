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
package net.sf.sail.webapp.dao.sds.impl;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.SdsWorkgroupCreateCommand;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.easymock.EasyMock;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class SdsWorkgroupCreateCommandHttpRestImplTest extends TestCase {

  private static final String HEADER_LOCATION = "Location";

  private static final String PORTAL_URL = "http://portal/url";

  private static final String WORKGROUP_DIRECTORY = "/workgroup";

  private static final String SOME_NAME = "pineapples";

  private static final Integer EXPECTED_ID = new Integer(12);

  private SdsWorkgroupCreateCommand command;

  private HttpRestTransport mockTransport;

  private HttpPostRequest request;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.command = new SdsWorkgroupCreateCommandHttpRestImpl();
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
        + EXPECTED_ID);

    SdsWorkgroup newWorkgroup = new SdsWorkgroup();
    newWorkgroup.setName(SOME_NAME);
    SdsOffering existingOffering = new SdsOffering();
    Integer existingOfferingId = new Integer(42);
    existingOffering.setSdsObjectId(existingOfferingId);
    newWorkgroup.setSdsOffering(existingOffering);
    this.command.setWorkgroup(newWorkgroup);

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