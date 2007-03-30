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
import java.util.HashSet;
import java.util.Set;

import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
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
 * @version $Id$
 */
public class OfferingsListControllerTest extends AbstractModelAndViewTests {

    private OfferingsListController offeringsListController;

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    private OfferingsService mockOfferingsService;

    private Set<SdsOffering> expectedSdsOfferingSet;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
        this.mockOfferingsService = EasyMock.createMock(OfferingsService.class);
        this.expectedSdsOfferingSet = new HashSet<SdsOffering>();
        SdsOffering offering = new SdsOffering();
        
        SdsCurnit curnit = new SdsCurnit();
        curnit.setSdsObjectId(1);
        offering.setCurnit(curnit);
        
        SdsJnlp jnlp = new SdsJnlp();
        jnlp.setSdsObjectId(2);
        offering.setJnlp(jnlp);
        
        offering.setName("test");
        offering.setSdsObjectId(3);
        this.expectedSdsOfferingSet.add(offering);
        this.offeringsListController = new OfferingsListController();
        this.offeringsListController
                .setOfferingsService(this.mockOfferingsService);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.request = null;
        this.response = null;
        this.mockOfferingsService = null;
        this.expectedSdsOfferingSet = null;
    }

    public void testHandleRequestInternal() throws Exception {
        EasyMock.expect(mockOfferingsService.getOfferingsList()).andReturn(
                this.expectedSdsOfferingSet);
        EasyMock.replay(mockOfferingsService);
        ModelAndView modelAndView = offeringsListController
                .handleRequestInternal(request, response);
        assertModelAttributeValue(modelAndView, "offeringslist",
                this.expectedSdsOfferingSet);
        EasyMock.verify(mockOfferingsService);
        EasyMock.reset(mockOfferingsService);

        Set<SdsOffering> EMPTY_SDS_OFFERING_SET = Collections.emptySet();
        EasyMock.expect(mockOfferingsService.getOfferingsList()).andReturn(
                EMPTY_SDS_OFFERING_SET);
        EasyMock.replay(mockOfferingsService);
        modelAndView = offeringsListController.handleRequestInternal(request,
                response);
        assertModelAttributeValue(modelAndView, "offeringslist",
                EMPTY_SDS_OFFERING_SET);
        EasyMock.verify(mockOfferingsService);
        EasyMock.reset(mockOfferingsService);
    }
}