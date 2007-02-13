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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.HttpGetRequest;
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

  private SdsOfferingListCommandHttpRestImpl command;

  private HttpRestTransport mockTransport;

  private HttpGetRequest httpRequest;

  private SdsOfferingDao sdsOfferingDao;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.command = new SdsOfferingListCommandHttpRestImpl();
    this.sdsOfferingDao = new HttpRestSdsOfferingDao();
    this.mockTransport = EasyMock.createMock(HttpRestTransport.class);
    this.command.setTransport(this.mockTransport);
    this.command.setSdsOfferingDao(this.sdsOfferingDao);
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
    this.httpRequest = null;
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

    EasyMock.expect(this.mockTransport.get(this.httpRequest)).andReturn(
        responseStream);
    EasyMock.replay(this.mockTransport);
    List<SdsOffering> expectedSdsOfferingList = new LinkedList<SdsOffering>();
    expectedSdsOfferingList.add(createOffering(1, 1, 6, "Airbag Complete"));
    expectedSdsOfferingList.add(createOffering(2, 2, 6, "Air Bag Test"));

    List<SdsOffering> actualList = this.command.execute(this.httpRequest);
    assertEquals(expectedSdsOfferingList.size(), actualList.size());
    assertEquals(expectedSdsOfferingList, actualList);
    EasyMock.verify(this.mockTransport);
  }

  public void testExecuteBadXML() throws Exception {
    InputStream responseStream = new ByteArrayInputStream(
        "<offerings></offerings>".getBytes());
    EasyMock.expect(this.mockTransport.get(this.httpRequest)).andReturn(
        responseStream);
    EasyMock.replay(this.mockTransport);
    List<SdsOffering> actualList = this.command.execute(this.httpRequest);
    assertTrue(actualList.isEmpty());
    EasyMock.verify(this.mockTransport);

    EasyMock.reset(this.mockTransport);
    responseStream = new ByteArrayInputStream("<fred></fred>".getBytes());
    EasyMock.expect(this.mockTransport.get(this.httpRequest)).andReturn(
        responseStream);
    EasyMock.replay(this.mockTransport);
    actualList = this.command.execute(this.httpRequest);
    assertTrue(actualList.isEmpty());
    EasyMock.verify(this.mockTransport);

    EasyMock.reset(this.mockTransport);
    responseStream = new ByteArrayInputStream("<offerings>".getBytes());
    EasyMock.expect(this.mockTransport.get(this.httpRequest)).andReturn(
        responseStream);
    EasyMock.replay(this.mockTransport);
    actualList = this.command.execute(this.httpRequest);
    assertTrue(actualList.isEmpty());
    EasyMock.verify(this.mockTransport);
  }

  public void testExecuteBadStream() throws Exception {
    InputStream responseStream = new ByteArrayInputStream(
        "<offerings></offerings>".getBytes());
    responseStream.close(); // this would be the bad part
    EasyMock.expect(this.mockTransport.get(this.httpRequest)).andReturn(
        responseStream);
    EasyMock.replay(this.mockTransport);
    List<SdsOffering> actualList = this.command.execute(this.httpRequest);
    assertTrue(actualList.isEmpty());
    EasyMock.verify(this.mockTransport);
  }

  public void testExecuteExceptions() throws Exception {
    EasyMock.expect(this.mockTransport.get(this.httpRequest)).andThrow(
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
    EasyMock.expect(this.mockTransport.get(this.httpRequest)).andThrow(
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