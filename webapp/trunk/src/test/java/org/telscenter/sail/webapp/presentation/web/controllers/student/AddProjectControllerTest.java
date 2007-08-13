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

import static org.easymock.EasyMock.createMock;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.group.impl.PersistentGroup;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.service.AclService;
import net.sf.sail.webapp.service.group.GroupService;

import org.easymock.EasyMock;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.AddProjectParameters;
import org.telscenter.sail.webapp.domain.impl.RunImpl;
import org.telscenter.sail.webapp.service.offering.RunNotFoundException;
import org.telscenter.sail.webapp.service.offering.RunService;

/**
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class AddProjectControllerTest extends AbstractModelAndViewTests {
	
	private static final String RUNCODE = "fly8978";
		
	private static final String PERIODNAME = "3";
	
	private static final String LEGAL_PROJECTCODE = RUNCODE + "-" + PERIODNAME;

	private static final String SUCCESS = "SUCCESS VIEW";

	private static final String FORM = "FORM VIEW";

	private AddProjectController addProjectController;
	
	private AddProjectParameters addProjectParameters;
	
	private RunService mockRunService;

	private GroupService mockGroupService;
	
	private AclService<Run> mockAclService;
	
	private ApplicationContext mockApplicationContext;
	
	private MockHttpServletRequest request;

	private MockHttpServletResponse response;
	
	private HttpSession mockSession;
	
	private BindException errors;
	
	private Run run;
	
	private Group group;
	
	private User user;
	
	/**
	 * @throws Exception 
	 * @see junit.framework.TestCase#setUp()
	 */
	@SuppressWarnings("unchecked")
	protected void setUp() throws Exception {
		super.setUp();
		mockApplicationContext = createMock(ApplicationContext.class);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		addProjectParameters = new AddProjectParameters();
		addProjectParameters.setProjectcode(LEGAL_PROJECTCODE);
		errors = new BindException(addProjectParameters, "");

		mockSession = new MockHttpSession();
		this.user = new UserImpl();
		mockSession.setAttribute(User.CURRENT_USER_SESSION_KEY, this.user);
		this.request.setSession(mockSession);
		
		run = new RunImpl();
		group = new PersistentGroup();
		group.setName(PERIODNAME);
		Set<Group> periods = new HashSet<Group>();
		periods.add(group);
		run.setPeriods(periods);
		
		this.mockRunService = EasyMock.createMock(RunService.class);
		this.mockGroupService = EasyMock.createMock(GroupService.class);
		this.mockAclService = EasyMock.createMock(AclService.class);
		addProjectController = new AddProjectController();
		addProjectController.setApplicationContext(mockApplicationContext);
		addProjectController.setRunService(mockRunService);
		addProjectController.setGroupService(mockGroupService);
		addProjectController.setAclService(mockAclService);
		addProjectController.setSuccessView(SUCCESS);
		addProjectController.setFormView(FORM);
	}
	
	public void testOnSubmit_success() throws Exception {
		// test submission of form with correct projectcode info.
		// should get ModelAndView back containing success view
		
		EasyMock.expect(mockRunService.retrieveRunByRuncode(RUNCODE)).andReturn(run);
		EasyMock.replay(mockRunService);
		
		Set<User> membersToAdd = new HashSet<User>();
		membersToAdd.add(user);
		mockGroupService.addMembers(group, membersToAdd);
		EasyMock.expectLastCall();
		EasyMock.replay(mockGroupService);

		ModelAndView modelAndView = addProjectController.onSubmit(request, response, addProjectParameters, errors);
		assertEquals(SUCCESS, modelAndView.getViewName());
		assertTrue(!errors.hasErrors());
		
		EasyMock.verify(mockRunService);		
		EasyMock.verify(mockGroupService);
	}
	
	public void testOnSubmit_failure_bad_runcode() throws Exception {
		// test submission of form with projectcode that has a runcode
		// that does not exist in datastore.
		// should get ModelAndView back containing form view
		String runcode_not_in_db = "abc1234";
		addProjectParameters.setProjectcode(runcode_not_in_db + "-" + PERIODNAME);
		EasyMock.expect(mockRunService.retrieveRunByRuncode(runcode_not_in_db)).andThrow(new RunNotFoundException("Run Not Found"));
		EasyMock.replay(mockRunService);
		
		ModelAndView modelAndView = addProjectController.onSubmit(request, response, addProjectParameters, errors);

		assertEquals(FORM, modelAndView.getViewName());
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getFieldErrorCount());
		
		assertNotNull(errors.getFieldError("projectcode"));
		EasyMock.verify(mockRunService);
	}
	
	public void testOnSubmit_failure_bad_periodname() throws Exception {
		// test submission of form with projectcode that has a periodname
		// that does not exist in datastore.
		// should get ModelAndView back containing form view
		String periodname_not_in_db = "thisperioddoesnotexist";
		addProjectParameters.setProjectcode(RUNCODE + "-" + periodname_not_in_db);
		EasyMock.expect(mockRunService.retrieveRunByRuncode(RUNCODE)).andReturn(run);
		EasyMock.replay(mockRunService);

		ModelAndView modelAndView = addProjectController.onSubmit(request, response, addProjectParameters, errors);
		assertEquals(FORM, modelAndView.getViewName());
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getFieldErrorCount());
		assertNotNull(errors.getFieldError("projectcode"));

		EasyMock.verify(mockRunService);		
	}

}
