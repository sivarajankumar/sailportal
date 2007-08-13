/**
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.telscenter.sail.webapp.presentation.web.controllers.student;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;

import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.web.servlet.ModelAndView;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.RunImpl;
import org.telscenter.sail.webapp.service.offering.RunService;

/**
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class StudentIndexControllerTest extends AbstractModelAndViewTests {
	
	private StudentIndexController studentIndexController;

    private HttpRestTransport mockHttpTransport;

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    private RunService mockRunService;
    
    private List<Run> expectedRunList;
    
    private User user;
    
    protected void setUp() throws Exception {
    	super.setUp();
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
        HttpSession mockSession = new MockHttpSession();
        this.user = new UserImpl();
        mockSession.setAttribute(User.CURRENT_USER_SESSION_KEY, this.user);
        this.request.setSession(mockSession);

        this.mockRunService = EasyMock.createMock(RunService.class);
    	
        SdsOffering sdsOffering = new SdsOffering();

        SdsCurnit curnit = new SdsCurnit();
        curnit.setSdsObjectId(1);
        sdsOffering.setSdsCurnit(curnit);

        SdsJnlp jnlp = new SdsJnlp();
        jnlp.setSdsObjectId(2);
        sdsOffering.setSdsJnlp(jnlp);

        sdsOffering.setName("test");
        sdsOffering.setSdsObjectId(3);
        
        Run run = new RunImpl();
        run.setSdsOffering(sdsOffering);
        
        this.expectedRunList = new LinkedList<Run>();
        this.expectedRunList.add(run);
        
        this.mockHttpTransport = EasyMock.createMock(HttpRestTransport.class);
        this.studentIndexController = new StudentIndexController();
        this.studentIndexController.setRunService(this.mockRunService);
        this.studentIndexController.setHttpRestTransport(this.mockHttpTransport);
    }
    
    protected void tearDown() throws Exception {
    	super.tearDown();
    	this.request = null;
    	this.response = null;
    	this.mockRunService = null;
    	this.mockHttpTransport = null;
    }
    
    @Test public void testHandleRequestInternal_WithRun() throws Exception {
    	EasyMock.expect(mockRunService.getRunList()).andReturn(
    			expectedRunList);
    	EasyMock.replay(mockRunService);
    	
    	ModelAndView modelAndView = 
    		studentIndexController.handleRequestInternal(request, response);
    	assertModelAttributeValue(modelAndView, 
    			StudentIndexController.RUN_LIST_KEY, expectedRunList);
    	assertModelAttributeValue(modelAndView, 
    			ControllerUtil.USER_KEY, user);
    	assertModelAttributeValue(modelAndView, 
    			StudentIndexController.HTTP_TRANSPORT_KEY, mockHttpTransport);
    	EasyMock.verify(mockRunService);
    }
    
    @Test public void testHandleRequestInternal_WithoutRun() throws Exception {
    	List<Run> emptyRunList = Collections.emptyList();
    	EasyMock.expect(mockRunService.getRunList()).
    	       andReturn(emptyRunList);
    	EasyMock.replay(mockRunService);
    	
    	ModelAndView modelAndView = 
    		studentIndexController.handleRequestInternal(request, response);
    	assertModelAttributeValue(modelAndView, 
    			StudentIndexController.RUN_LIST_KEY, emptyRunList);
    	assertModelAttributeValue(modelAndView, 
    			ControllerUtil.USER_KEY, user);
    	assertModelAttributeValue(modelAndView, 
    			StudentIndexController.HTTP_TRANSPORT_KEY, mockHttpTransport);
    	EasyMock.verify(mockRunService);
    }

}
