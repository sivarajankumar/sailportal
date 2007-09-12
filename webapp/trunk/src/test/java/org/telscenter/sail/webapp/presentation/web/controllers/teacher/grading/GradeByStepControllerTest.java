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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.grading;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.web.servlet.ModelAndView;
import org.telscenter.sail.webapp.domain.gradingtool.CurnitMap;
import org.telscenter.sail.webapp.domain.gradingtool.PasActivity;
import org.telscenter.sail.webapp.domain.gradingtool.PasProject;
import org.telscenter.sail.webapp.domain.gradingtool.PasStep;
import org.telscenter.sail.webapp.presentation.web.controllers.teacher.grading.GradeByStepController;
import org.telscenter.sail.webapp.service.gradingtool.CurnitMapService;

/**
 * @author Anthony Perritano
 */
public class GradeByStepControllerTest extends AbstractModelAndViewTests {

	private GradeByStepController gradeByStepController;

	private HttpRestTransport mockHttpTransport;

	private MockHttpServletRequest request;

	private MockHttpServletResponse response;

	private CurnitMapService mockCurnitMapService;

	private WorkgroupService mockWorkgroupService;

	private List<CurnitMap> expectedRunList;

	private User user;

	private CurnitMap curnitMap;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		this.request = new MockHttpServletRequest();
		this.response = new MockHttpServletResponse();
		HttpSession mockSession = new MockHttpSession();
		this.user = new UserImpl();
		
		this.request.setParameter(GradeByStepController.RUN_ID, "343");
		mockSession.setAttribute(User.CURRENT_USER_SESSION_KEY, this.user);
		this.request.setSession(mockSession);

		this.mockCurnitMapService = EasyMock.createMock(CurnitMapService.class);
		
		curnitMap = new CurnitMap();
		
		PasProject p = new PasProject();
		p.setId("1");
		p.setTitle("Global Warming");
		
		PasActivity act1 = new PasActivity();
		act1.setId("232");
		act1.setTitle("activity 1");
		
		PasStep s1 = new PasStep();
		s1.setId("343");
		s1.setTitle("Step 1 - write something");
		
		PasStep s2 = new PasStep();
		s2.setId("23222");
		s2.setTitle("Step 2 = do something");
		
		act1.addStep(s1);
		act1.addStep(s2);
		
		PasActivity act2 = new PasActivity();
		act2.setId("65");
		act2.setTitle("activity 2");
		
		PasStep s4 = new PasStep();
		s4.setId("67");
		s4.setTitle("Step 1 - write something again");
		
		PasStep s5 = new PasStep();
		s5.setId("34");
		s5.setTitle("Step 2 = do something now");
		
		act1.addStep(s1);
		act1.addStep(s2);
		act2.addStep(s4);
		act2.addStep(s5);
		
		p.addActivity(act1);
		p.addActivity(act2);
		
		curnitMap.setProject(p);
	

		this.expectedRunList = new LinkedList<CurnitMap>();
		this.expectedRunList.add(curnitMap);

		this.mockHttpTransport = EasyMock.createMock(HttpRestTransport.class);
		this.gradeByStepController = new GradeByStepController();
		this.gradeByStepController.setCurnitMapService(this.mockCurnitMapService);
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		this.request = null;
		this.response = null;
		this.mockCurnitMapService = null;
	}

	/**
	 * Tests getting the runId from the request and looking up the appropaite curnitMap
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHandleRequestInternal_WithRunId() throws Exception {
		EasyMock.expect(mockCurnitMapService.getCurnitMapByRunId("343")).andReturn(this.curnitMap);
		EasyMock.replay(this.mockCurnitMapService);

		ModelAndView modelAndView = gradeByStepController
		.handleRequestInternal(request, response);
		assertModelAttributeValue(modelAndView,
				GradeByStepController.CURNIT_MAP,
				this.curnitMap);
		EasyMock.verify(this.mockCurnitMapService);
	}

//	public void testHandleRequestInternal_NoOfferings() throws Exception {
//		List<Run> emptyRunList = Collections.emptyList();
//		Map<Offering, List<Workgroup>> emptyWorkgroupMap = Collections
//		.emptyMap();
//		EasyMock.expect(mockCurnitMapService.getRunList()).andReturn(
//				emptyRunList);
//		EasyMock.replay(this.mockCurnitMapService);
//		EasyMock.replay(this.mockWorkgroupService);
//
//		ModelAndView modelAndView = gradeByStepController
//		.handleRequestInternal(request, response);
//		assertModelAttributeValue(modelAndView,
//				RunListController.CURRENT_RUN_LIST_KEY, emptyRunList);
//		assertModelAttributeValue(modelAndView,
//				RunListController.WORKGROUP_MAP_KEY, emptyWorkgroupMap);
//		assertModelAttributeValue(modelAndView,
//				ControllerUtil.USER_KEY, this.user);
//		assertModelAttributeValue(modelAndView,
//				RunListController.HTTP_TRANSPORT_KEY,
//				this.mockHttpTransport);
//		assertModelAttributeValue(modelAndView, RunListController.GRADING_PARAM, RunListController.FALSE);
//		EasyMock.verify(this.mockCurnitMapService);
//		EasyMock.verify(this.mockWorkgroupService);
//	}
}
