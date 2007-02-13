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

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;
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

  private static final Integer EXPECTED_ID = new Integer(1);

  private static final String EXPECTED_FIRST_NAME = "Blah";

  private static final String EXPECTED_LAST_NAME = "Last";

  private static final String HEADER_LOCATION = "Location";

  private static final String PORTAL_URL = "http://portal/url/";

  private static final String USER_DIRECTORY = "user/";

  private SdsUserCreateCommandHttpRestImpl command;

  private HttpRestTransport mockTransport;

  private HttpPostRequest httpRequest;

  private SdsUser expectedSdsUser;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.command = new SdsUserCreateCommandHttpRestImpl();
    this.mockTransport = EasyMock.createMock(HttpRestTransport.class);
    this.expectedSdsUser = new SdsUser();
    this.expectedSdsUser.setFirstName(EXPECTED_FIRST_NAME);
    this.expectedSdsUser.setLastName(EXPECTED_LAST_NAME);
    this.expectedSdsUser.setSdsObjectId(EXPECTED_ID);
    this.command.setTransport(this.mockTransport);
    this.command.setSdsUser(this.expectedSdsUser);
    this.httpRequest = this.command.generateRequest();
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    this.command = null;
    this.mockTransport = null;
    this.expectedSdsUser = null;
    this.httpRequest = null;
  }

  /**
   * Not testing this since we would be essentially testing info that is hard
   * coded.
   */
  public void testGenerateRequest() {
  }

  public void testCreate() throws Exception {
    Map<String, String> responseMap = new HashMap<String, String>();
    responseMap.put(HEADER_LOCATION, PORTAL_URL + USER_DIRECTORY + EXPECTED_ID);
    EasyMock.expect(this.mockTransport.post(this.httpRequest)).andReturn(
        responseMap);
    EasyMock.replay(this.mockTransport);
    assertEquals(this.expectedSdsUser, this.command.execute(this.httpRequest));
    EasyMock.verify(this.mockTransport);
  }

  public void testCreateException() throws Exception {
    EasyMock.expect(this.mockTransport.post(this.httpRequest)).andThrow(
        new BadRequestException("exception"));
    EasyMock.replay(this.mockTransport);
    try {
      this.command.execute(this.httpRequest);
      fail("Expected BadRequestException");
    }
    catch (BadRequestException e) {
    }
    EasyMock.verify(this.mockTransport);

    EasyMock.reset(this.mockTransport);
    EasyMock.expect(this.mockTransport.post(this.httpRequest)).andThrow(
        new NetworkTransportException("exception"));
    EasyMock.replay(this.mockTransport);
    try {
      this.command.execute(this.httpRequest);
      fail("Expected NetworkTransportException");
    }
    catch (NetworkTransportException e) {
    }
    EasyMock.verify(this.mockTransport);
  }
}