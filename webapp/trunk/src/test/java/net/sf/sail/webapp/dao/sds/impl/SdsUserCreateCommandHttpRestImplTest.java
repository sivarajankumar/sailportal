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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.reset;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import net.sf.sail.webapp.domain.sds.SdsUser;
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

  private static final Integer PORTAL_ID = new Integer(12);

  private static final Integer EXPECTED_ID = new Integer(1);

  private static final String EXPECTED_FIRST_NAME = "Blah";

  private static final String EXPECTED_LAST_NAME = "Last";

  private static final String HEADER_LOCATION = "Location";

  private static final String PORTAL_URL = "http://rails.dev.concord.org/sds/";

  private static final String USER_DIRECTORY = "/user/";

  private SdsUserCreateCommandHttpRestImpl command;

  private HttpRestTransport mockTransport;

  private SdsUser sdsUser;

  private SdsUser expectedSdsUser;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    command = new SdsUserCreateCommandHttpRestImpl();
    mockTransport = createMock(HttpRestTransport.class);
    sdsUser = new SdsUser();
    expectedSdsUser = new SdsUser();
    expectedSdsUser.setFirstName(EXPECTED_FIRST_NAME);
    expectedSdsUser.setLastName(EXPECTED_LAST_NAME);
    expectedSdsUser.setSdsObjectId(EXPECTED_ID);
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
    sdsUser = null;
  }
  
  /**
   * Not testing this since we would be essentially testing info that is hard coded. 
   */
  public void testGenerateRequest(){}

  public void testCreate() throws Exception {
    Map<String, String> responseMap = new HashMap<String, String>();
    responseMap.put(HEADER_LOCATION, PORTAL_URL + PORTAL_ID + USER_DIRECTORY
        + EXPECTED_ID);
    this.sdsUser.setFirstName(EXPECTED_FIRST_NAME);
    this.sdsUser.setLastName(EXPECTED_LAST_NAME);
    expect(mockTransport.post(command.generateRequest(this.sdsUser)))
        .andReturn(responseMap);
    EasyMock.replay(mockTransport);
    assertEquals(expectedSdsUser, command.execute(this.sdsUser));
    EasyMock.verify(mockTransport);
  }

  public void testCreateException() throws Exception {
    expect(mockTransport.post(command.generateRequest(this.sdsUser))).andThrow(
        new BadRequestException("exception"));
    EasyMock.replay(mockTransport);
    try {
      command.execute(this.sdsUser);
      fail("Expected BadRequestException");
    }
    catch (BadRequestException e) {
    }

    reset(mockTransport);
    expect(mockTransport.post(command.generateRequest(this.sdsUser))).andThrow(
        new NetworkTransportException("exception"));
    EasyMock.replay(mockTransport);
    try {
      command.execute(this.sdsUser);
      fail("Expected NetworkTransportException");
    }
    catch (NetworkTransportException e) {
    }
  }
}