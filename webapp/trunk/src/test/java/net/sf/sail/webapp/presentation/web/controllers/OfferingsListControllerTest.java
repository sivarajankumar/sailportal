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
package net.sf.sail.webapp.presentation.web.controllers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.service.offerings.OfferingsService;

import org.easymock.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Laurel Williams
 * 
 * @version $Id: OfferingsListControllerTest.java 131 2007-02-08 16:55:57Z
 *          laurel $
 */
public class OfferingsListControllerTest extends AbstractModelAndViewTests {

  private OfferingsListController offeringsListController;

  private MockHttpServletRequest request;

  private MockHttpServletResponse response;

  private OfferingsService mockOfferingsService;

  private List<SdsOffering> expectedSdsOfferingList;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception {
    super.setUp();
    this.request = new MockHttpServletRequest();
    this.response = new MockHttpServletResponse();
    this.mockOfferingsService = EasyMock.createMock(OfferingsService.class);
    this.expectedSdsOfferingList = new LinkedList<SdsOffering>();
    SdsOffering offering = new SdsOffering();
    offering.setCurnitId(1);
    offering.setJnlpId(2);
    offering.setName("test");
    offering.setSdsObjectId(3);
    this.expectedSdsOfferingList.add(offering);
    this.offeringsListController = new OfferingsListController();
    this.offeringsListController.setOfferingsService(this.mockOfferingsService);
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    super.tearDown();
    this.request = null;
    this.response = null;
    this.mockOfferingsService = null;
    this.expectedSdsOfferingList = null;
  }

  @SuppressWarnings("unchecked")
  public void testHandleRequestInternal() throws Exception {
    EasyMock.expect(mockOfferingsService.getOfferingsList()).andReturn(
        expectedSdsOfferingList);
    EasyMock.replay(mockOfferingsService);
    ModelAndView modelAndView = offeringsListController.handleRequestInternal(
        request, response);
    assertModelAttributeValue(modelAndView, "offeringslist",
        expectedSdsOfferingList);
    EasyMock.verify(mockOfferingsService);
    EasyMock.reset(mockOfferingsService);

    EasyMock.expect(mockOfferingsService.getOfferingsList()).andReturn(
        Collections.EMPTY_LIST);
    EasyMock.replay(mockOfferingsService);
    modelAndView = offeringsListController.handleRequestInternal(request,
        response);
    assertModelAttributeValue(modelAndView, "offeringslist",
        Collections.EMPTY_LIST);
    EasyMock.verify(mockOfferingsService);
    EasyMock.reset(mockOfferingsService);
  }
}