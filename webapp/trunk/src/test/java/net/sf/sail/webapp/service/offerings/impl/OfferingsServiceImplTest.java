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
package net.sf.sail.webapp.service.offerings.impl;

import static org.easymock.EasyMock.createMock;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.domain.sds.SdsOffering;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class OfferingsServiceImplTest extends TestCase {

  private SdsOfferingDao mockSdsOfferingDao;

  private List<SdsOffering> expectedSdsOfferingList;

  private SdsOffering offering;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @SuppressWarnings("unchecked")
  protected void setUp() throws Exception {
    super.setUp();
    mockSdsOfferingDao = createMock(SdsOfferingDao.class);
    expectedSdsOfferingList = new ArrayList<SdsOffering>();
    offering = new SdsOffering();
    offering.setCurnitId(1);
    offering.setJnlpId(2);
    offering.setName("test");
    offering.setSdsObjectId(3);
    expectedSdsOfferingList.add(offering);
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    super.tearDown();
    mockSdsOfferingDao = null;
    expectedSdsOfferingList = null;
  }

  @SuppressWarnings("unchecked")
  public void testGetOfferingsList() throws Exception {
    EasyMock.expect(mockSdsOfferingDao.createDataObject()).andReturn(
        this.offering);
    EasyMock.expect(mockSdsOfferingDao.getList(this.offering)).andReturn(
        expectedSdsOfferingList);
    EasyMock.replay(mockSdsOfferingDao);
    OfferingsServiceImpl offeringServiceImpl = new OfferingsServiceImpl();
    offeringServiceImpl.setSdsOfferingDao(mockSdsOfferingDao);
    assertEquals(expectedSdsOfferingList, offeringServiceImpl
        .getOfferingsList());
    EasyMock.verify(mockSdsOfferingDao);
  }
}