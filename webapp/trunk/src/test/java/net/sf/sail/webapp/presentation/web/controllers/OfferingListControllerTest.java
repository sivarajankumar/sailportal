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

import javax.servlet.http.HttpSession;

import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.service.offering.OfferingService;

import org.easymock.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class OfferingListControllerTest extends AbstractModelAndViewTests {

    private OfferingListController offeringListController;

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    private OfferingService mockOfferingsService;

    private List<Offering> expectedOfferingList;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
        this.mockOfferingsService = EasyMock.createMock(OfferingService.class);
        SdsOffering sdsOffering = new SdsOffering();

        SdsCurnit curnit = new SdsCurnit();
        curnit.setSdsObjectId(1);
        sdsOffering.setSdsCurnit(curnit);

        SdsJnlp jnlp = new SdsJnlp();
        jnlp.setSdsObjectId(2);
        sdsOffering.setSdsJnlp(jnlp);

        sdsOffering.setName("test");
        sdsOffering.setSdsObjectId(3);
        Offering offering = new OfferingImpl();
        offering.setSdsOffering(sdsOffering);

        this.expectedOfferingList = new LinkedList<Offering>();
        this.expectedOfferingList.add(offering);

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
    }

    public void testHandleRequestInternal() throws Exception {
        HttpSession mockSession = new MockHttpSession();
        User user = new UserImpl();
        mockSession.setAttribute(User.CURRENT_USER_SESSION_KEY, user);
        this.request.setSession(mockSession);

        EasyMock.expect(mockOfferingsService.getOfferingList()).andReturn(
                this.expectedOfferingList);
        EasyMock.replay(mockOfferingsService);
        ModelAndView modelAndView = offeringListController
                .handleRequestInternal(request, response);
        assertModelAttributeValue(modelAndView,
                OfferingListController.OFFERING_LIST_KEY, this.expectedOfferingList);
        assertModelAttributeValue(modelAndView, OfferingListController.USER_KEY,
                user);
        EasyMock.verify(mockOfferingsService);
        EasyMock.reset(mockOfferingsService);

        List<Offering> emptyOfferingList = Collections.emptyList();
        EasyMock.expect(mockOfferingsService.getOfferingList()).andReturn(
                emptyOfferingList);
        EasyMock.replay(mockOfferingsService);
        modelAndView = offeringListController.handleRequestInternal(request,
                response);
        assertModelAttributeValue(modelAndView,
                OfferingListController.OFFERING_LIST_KEY, emptyOfferingList);
        EasyMock.verify(mockOfferingsService);
        EasyMock.reset(mockOfferingsService);
    }
}