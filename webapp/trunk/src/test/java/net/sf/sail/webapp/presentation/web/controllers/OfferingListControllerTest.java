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
import java.util.Iterator;
import java.util.Set;

import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.service.offering.OfferingService;

import org.easymock.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Laurel Williams
 * 
 * @version $Id: OfferingListControllerTest.java 257 2007-03-30 14:59:02Z
 *          cynick $
 */
public class OfferingListControllerTest extends AbstractModelAndViewTests {

    private OfferingListController offeringListController;

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    private OfferingService mockOfferingsService;
   
    private Iterator<Offering> expectedOfferingIterator;
    

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
        this.mockOfferingsService = EasyMock.createMock(OfferingService.class);
        Set<Offering> offeringSet = new HashSet<Offering>();
        SdsOffering sdsOffering = new SdsOffering();

        SdsCurnit curnit = new SdsCurnit();
        curnit.setSdsObjectId(1);
        sdsOffering.setCurnit(curnit);

        SdsJnlp jnlp = new SdsJnlp();
        jnlp.setSdsObjectId(2);
        sdsOffering.setJnlp(jnlp);

        sdsOffering.setName("test");
        sdsOffering.setSdsObjectId(3);
        Offering offering = new OfferingImpl();
        offering.setSdsOffering(sdsOffering);
        
        offeringSet.add(offering);
        this.expectedOfferingIterator = offeringSet.iterator();
        
        this.offeringListController = new OfferingListController();
        this.offeringListController
                .setOfferingService(this.mockOfferingsService);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.request = null;
        this.response = null;
        this.mockOfferingsService = null;
        this.expectedOfferingIterator = null;
    }

    public void testHandleRequestInternal() throws Exception {
        EasyMock.expect(mockOfferingsService.getOfferingIterator()).andReturn(this.expectedOfferingIterator);
        EasyMock.replay(mockOfferingsService);
        ModelAndView modelAndView = offeringListController
                .handleRequestInternal(request, response);
        assertModelAttributeValue(modelAndView, OfferingListController.OFFERINGS_ITERATOR,
                this.expectedOfferingIterator);
        EasyMock.verify(mockOfferingsService);
        EasyMock.reset(mockOfferingsService);

        Set<Offering> emptyOfferingSet = Collections.emptySet();
        Iterator<Offering> emptyOfferingSetIterator = emptyOfferingSet.iterator();
        EasyMock.expect(mockOfferingsService.getOfferingIterator()).andReturn(
        		emptyOfferingSetIterator);
        EasyMock.replay(mockOfferingsService);
        modelAndView = offeringListController.handleRequestInternal(request,
                response);
        assertModelAttributeValue(modelAndView, OfferingListController.OFFERINGS_ITERATOR,
        		emptyOfferingSetIterator);
        EasyMock.verify(mockOfferingsService);
        EasyMock.reset(mockOfferingsService);
    }
}