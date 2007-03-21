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
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 * 
 */
public class SdsJnlpCreateCommandHttpRestImplTest extends TestCase {

  private static final Integer EXPECTED_ID = new Integer(1);

  private static final String EXPECTED_NAME = "Blah";

  private static final String EXPECTED_URL = "http://tels-develop.soe.berkeley.edu:8080/maven-jnlp/basic-emf.jnlp";

  private static final String HEADER_LOCATION = "Location";

  private static final String PORTAL_URL = "http://portal/url/";

  private static final String CURNIT_DIRECTORY = "curnit/";

  private SdsJnlpCreateCommandHttpRestImpl command;

  private HttpRestTransport mockTransport;

  private HttpPostRequest httpRequest;

  private SdsJnlp expectedSdsJnlp;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.command = new SdsJnlpCreateCommandHttpRestImpl();
    this.mockTransport = EasyMock.createMock(HttpRestTransport.class);
    this.expectedSdsJnlp = new SdsJnlp();
    this.expectedSdsJnlp.setName(EXPECTED_NAME);
    this.expectedSdsJnlp.setUrl(EXPECTED_URL);
    this.expectedSdsJnlp.setSdsObjectId(EXPECTED_ID);
    
    
    this.command.setTransport(this.mockTransport);
    this.command.setSdsJnlp(this.expectedSdsJnlp);
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
    this.expectedSdsJnlp = null;
    this.httpRequest = null;
  }

  /**
   * Not testing this since we would be essentially testing info that is hard
   * coded.
   */
  public void testGenerateRequest() {
  }

  public void testExecute() throws Exception {
    Map<String, String> responseMap = new HashMap<String, String>();
    responseMap.put(HEADER_LOCATION, PORTAL_URL + CURNIT_DIRECTORY + EXPECTED_ID);
    EasyMock.expect(this.mockTransport.post(this.httpRequest)).andReturn(
        responseMap);
    EasyMock.replay(this.mockTransport);
    SdsJnlp actual = this.command.execute(this.httpRequest);
    assertEquals(EXPECTED_NAME, actual.getName());
    assertEquals(EXPECTED_URL, actual.getUrl());
    assertEquals(EXPECTED_ID, actual.getSdsObjectId());
    EasyMock.verify(this.mockTransport);
  }

  public void testExecute_Exception() throws Exception {
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