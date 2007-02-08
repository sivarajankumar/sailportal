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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.easymock.EasyMock;

/**
 * @author Cynick Young
 * 
 * @version $Id: SdsOfferingListCommandHttpRestImplTest.java 121 2007-02-07
 *          15:15:16Z laurel $
 * 
 */
public class SdsOfferingListCommandHttpRestImplTest extends TestCase {

  private static final String BASE_URL = "base url";

  private static final Integer PORTAL_ID = new Integer(12);

  private SdsOfferingListCommandHttpRestImpl command;

  private HttpRestTransport mockTransport;

  private SdsOffering sdsOffering;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.command = new SdsOfferingListCommandHttpRestImpl();
    this.mockTransport = createMock(HttpRestTransport.class);
    this.sdsOffering = new SdsOffering();
    this.command.setTransport(mockTransport);
    this.command.setBaseUrl(BASE_URL);
    this.command.setPortalId(PORTAL_ID);
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    this.command = null;
    this.mockTransport = null;
    this.sdsOffering = null;
  }

  /**
   * Test method for
   * {@link net.sf.sail.webapp.dao.sds.impl.SdsOfferingListCommandHttpRestImpl#execute(net.sf.sail.webapp.domain.sds.SdsOffering)}.
   */
  public void testExecute() throws Exception {
    final String responseString = "<offerings><offering><name>Airbag Complete</name><curnit-id>1</curnit-id><id>1</id><jnlp-id>6</jnlp-id></offering><offering><name>Air Bag Test</name><curnit-id>2</curnit-id><id>2</id><jnlp-id>6</jnlp-id></offering></offerings>";
    final InputStream responseStream = new ByteArrayInputStream(responseString
        .getBytes());

    final byte[] streamBytes = new byte[responseString.length()];
    assertEquals(responseString.length(), responseStream.read(streamBytes));
    assertEquals(responseString, new String(streamBytes));
    responseStream.reset();

    expect(mockTransport.get(command.generateRequest(this.sdsOffering)))
        .andReturn(responseStream);
    EasyMock.replay(mockTransport);
    List<SdsOffering> expectedSdsOfferingList = new LinkedList<SdsOffering>();
    expectedSdsOfferingList.add(createOffering(1, 1, 6, "Airbag Complete"));
    expectedSdsOfferingList.add(createOffering(2, 2, 6, "Air Bag Test"));

    List<SdsOffering> actualList = command.execute(this.sdsOffering);
    assertEquals(expectedSdsOfferingList.size(), actualList.size());
    assertEquals(expectedSdsOfferingList, actualList);
    EasyMock.verify(mockTransport);
  }

  public void testExecuteBadXML() throws Exception {
    InputStream responseStream = new ByteArrayInputStream(
        "<offerings></offerings>".getBytes());
    expect(mockTransport.get(command.generateRequest(this.sdsOffering)))
        .andReturn(responseStream);
    EasyMock.replay(mockTransport);
    List<SdsOffering> actualList = command.execute(this.sdsOffering);
    assertTrue(actualList.isEmpty());
    EasyMock.reset(mockTransport);

    responseStream = new ByteArrayInputStream("<fred></fred>".getBytes());
    expect(mockTransport.get(command.generateRequest(this.sdsOffering)))
        .andReturn(responseStream);
    EasyMock.replay(mockTransport);
    actualList = command.execute(this.sdsOffering);
    assertTrue(actualList.isEmpty());
    EasyMock.reset(mockTransport);

    responseStream = new ByteArrayInputStream("<offerings>".getBytes());
    expect(mockTransport.get(command.generateRequest(this.sdsOffering)))
        .andReturn(responseStream);
    EasyMock.replay(mockTransport);
    actualList = command.execute(this.sdsOffering);
    assertTrue(actualList.isEmpty());
  }

  public void testExecuteBadStream() throws Exception {
    InputStream responseStream = new ByteArrayInputStream(
        "<offerings></offerings>".getBytes());
    responseStream.close(); // this would be the bad part
    expect(mockTransport.get(command.generateRequest(this.sdsOffering)))
        .andReturn(responseStream);
    EasyMock.replay(mockTransport);
    List<SdsOffering> actualList = command.execute(this.sdsOffering);
    assertTrue(actualList.isEmpty());
  }

  public void testExecuteExceptions() throws Exception {
    expect(mockTransport.get(command.generateRequest(this.sdsOffering)))
        .andThrow(new BadRequestException("exception"));
    EasyMock.replay(mockTransport);

    try {
      command.execute(this.sdsOffering);
      fail("Expected BadRequestException");
    }
    catch (BadRequestException e) {
    }
    EasyMock.reset(mockTransport);

    expect(mockTransport.get(command.generateRequest(this.sdsOffering)))
        .andThrow(new NetworkTransportException("exception"));
    EasyMock.replay(mockTransport);

    try {
      command.execute(this.sdsOffering);
      fail("Expected NetworkTransportException");
    }
    catch (NetworkTransportException e) {
    }
  }

  /**
   * @return
   */
  private SdsOffering createOffering(int objectId, int curnitId, int jnlpId,
      String name) {
    SdsOffering offering = new SdsOffering();
    offering.setSdsObjectId(objectId);
    offering.setCurnitId(curnitId);
    offering.setJnlpId(jnlpId);
    offering.setName(name);
    return offering;
  }

  /**
   * Not testing this since we would be essentially testing info that is hard
   * coded.
   */
  public void testGenerateRequest() {
  }
}