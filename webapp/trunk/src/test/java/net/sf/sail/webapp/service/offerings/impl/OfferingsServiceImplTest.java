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

import java.util.LinkedList;
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

  private OfferingsServiceImpl offeringServiceImpl;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @SuppressWarnings("unchecked")
  protected void setUp() throws Exception {
    super.setUp();
    this.mockSdsOfferingDao = createMock(SdsOfferingDao.class);
    this.offeringServiceImpl = new OfferingsServiceImpl();
    this.offeringServiceImpl.setSdsOfferingDao(this.mockSdsOfferingDao);
    this.expectedSdsOfferingList = new LinkedList<SdsOffering>();
    SdsOffering offering = new SdsOffering();
    offering.setCurnitId(1);
    offering.setJnlpId(2);
    offering.setName("test");
    offering.setSdsObjectId(3);
    this.expectedSdsOfferingList.add(offering);
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    super.tearDown();
    this.offeringServiceImpl = null;
    this.mockSdsOfferingDao = null;
    this.expectedSdsOfferingList = null;
  }

  @SuppressWarnings("unchecked")
  public void testGetOfferingsList() throws Exception {
    EasyMock.expect(mockSdsOfferingDao.getList()).andReturn(
        expectedSdsOfferingList);
    EasyMock.replay(mockSdsOfferingDao);
    assertEquals(expectedSdsOfferingList, offeringServiceImpl
        .getOfferingsList());
    EasyMock.verify(mockSdsOfferingDao);
  }
}