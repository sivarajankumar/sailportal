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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.SdsCommand;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.http.HttpGetRequest;

import org.easymock.EasyMock;

/**
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class HttpRestSdsOfferingDaoTest extends TestCase {

  private HttpRestSdsOfferingDao sdsOfferingDao;

  private SdsCommand<SdsOffering, List<SdsOffering>> mockCommand;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @SuppressWarnings("unchecked")
  protected void setUp() throws Exception {
    super.setUp();
    this.sdsOfferingDao = new HttpRestSdsOfferingDao();
    this.mockCommand = EasyMock.createMock(SdsCommand.class);
    this.sdsOfferingDao.setSdsOfferingListCommand(mockCommand);
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    super.tearDown();
    this.sdsOfferingDao = null;
  }

  /**
   * Test method for
   * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#getList()}.
   */
  @SuppressWarnings("unchecked")
  public void testGetList() {
    SdsOffering sdsOffering = this.sdsOfferingDao.createDataObject();
    EasyMock.expect(this.mockCommand.generateRequest(sdsOffering)).andReturn(
        new HttpGetRequest(Collections.EMPTY_MAP, Collections.EMPTY_MAP, null,
            0));
    sdsOffering.setCurnitId(1);
    sdsOffering.setJnlpId(2);
    sdsOffering.setName("test");
    sdsOffering.setSdsObjectId(3);

    List<SdsOffering> expectedSdsOfferingList = new ArrayList<SdsOffering>();
    expectedSdsOfferingList.add(sdsOffering);

    EasyMock.expect(this.mockCommand.execute(sdsOffering)).andReturn(
        expectedSdsOfferingList);
    EasyMock.replay(this.mockCommand);

    assertEquals(expectedSdsOfferingList, this.sdsOfferingDao.getList(sdsOffering));
    EasyMock.verify(this.mockCommand);
  }

  /**
   * Test method for
   * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#createDataObject()}.
   */
  public void testCreateDataObject() {
    assertTrue(this.sdsOfferingDao.createDataObject() instanceof SdsOffering);
  }

  /**
   * Test method for
   * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#delete(net.sf.sail.webapp.domain.sds.SdsOffering)}.
   */
  public void testDelete() {
    try {
      this.sdsOfferingDao.delete(null);
      fail("UnsupportedOperationException expected");
    }
    catch (UnsupportedOperationException expected) {
    }
  }

  /**
   * Test method for
   * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#retrieveByName(java.lang.String)}.
   */
  public void testRetrieveByName() {
    try {
      this.sdsOfferingDao.retrieveByName(null);
      fail("UnsupportedOperationException expected");
    }
    catch (UnsupportedOperationException expected) {
    }
  }

  /**
   * Test method for
   * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#save(net.sf.sail.webapp.domain.sds.SdsOffering)}.
   */
  public void testSave() {
    try {
      this.sdsOfferingDao.save(null);
      fail("UnsupportedOperationException expected");
    }
    catch (UnsupportedOperationException expected) {
    }
  }
}
