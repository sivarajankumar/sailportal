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
package org.telscenter.sail.webapp.presentation.web.controllers;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.group.impl.PersistentGroup;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.service.AclService;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.group.GroupService;

import org.easymock.EasyMock;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.authentication.Gender;
import org.telscenter.sail.webapp.domain.authentication.impl.StudentUserDetails;
import org.telscenter.sail.webapp.domain.impl.RunImpl;
import org.telscenter.sail.webapp.presentation.web.StudentAccountForm;
import org.telscenter.sail.webapp.service.offering.RunNotFoundException;
import org.telscenter.sail.webapp.service.offering.RunService;


/**
 * @author Hiroki Terashima
 *
 * @version $Id$
 */
public class RegisterStudentControllerTest extends AbstractModelAndViewTests {

	private static final String SUCCESS = "WooHoo";

	private static final String FORM = "Form";

	private static final String PASSWORD = "Pass";
	
	private static final String FIRSTNAME = "Hiroki";

	private static final String LASTNAME = "Terashima";
	
	private static final String PERIODNAME = "6";

	private static final Gender GENDER = Gender.MALE;

	private static final String PROJECTCODE = "Ruby8180-6";
	
	private static final String RUNCODE = "Ruby8180";
	
	private Date birthday = null;
	
	ApplicationContext mockApplicationContext;

	MockHttpServletRequest request;

	HttpServletResponse response;

	BindException errors;
	
	StudentUserDetails studentUserDetails;
	
	StudentAccountForm studentAccountForm;

	UserService mockUserService;
	
	GroupService mockGroupService;
	
	RunService mockRunService;
	
	AclService<Run> mockAclService;
	
	RegisterStudentController signupController;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		mockApplicationContext = createMock(ApplicationContext.class);
		studentUserDetails = new StudentUserDetails();
		studentAccountForm = new StudentAccountForm();
		errors = new BindException(studentAccountForm, "");
		mockUserService = createMock(UserService.class);
		mockGroupService = createMock(GroupService.class);
		mockRunService = createMock(RunService.class);
		mockAclService = createMock(AclService.class);
		Calendar cal = Calendar.getInstance();
		cal.set(1983, 6, 19);
		birthday = cal.getTime();
		
		studentUserDetails.setFirstname(FIRSTNAME);
		studentUserDetails.setLastname(LASTNAME);
		studentUserDetails.setGender(GENDER);
		studentUserDetails.setBirthday(this.birthday);
		request.addParameter("firstname", FIRSTNAME);
		request.addParameter("lastname", LASTNAME);
		request.addParameter("password", PASSWORD);		
		
		studentAccountForm.setUserDetails(studentUserDetails);
		studentAccountForm.setProjectCode(PROJECTCODE);
		
		signupController = new RegisterStudentController();
		signupController.setApplicationContext(mockApplicationContext);
		signupController.setUserService(mockUserService);
		signupController.setGroupService(mockGroupService);
		signupController.setRunService(mockRunService);
		signupController.setAclService(mockAclService);
		signupController.setSuccessView(SUCCESS);
	}
	
	public void testOnSubmit() throws Exception {
		// test submission of form with correct username and password info.
		// should get ModelAndView back containing view which is instance of
		// RedirectView, with name of success view as URL.

		User user = new UserImpl();
		expect(mockUserService.createUser(studentUserDetails)).andReturn(user);
		replay(mockUserService);
		
		Run run = new RunImpl();
		Group group = new PersistentGroup();
		group.setName(PERIODNAME);
		Set<Group> periods = new HashSet<Group>();
		periods.add(group);
		run.setPeriods(periods);
		expect(mockRunService.retrieveRunByRuncode(RUNCODE)).andReturn(run);
		replay(mockRunService);

		Set<User> membersToAdd = new HashSet<User>();
		membersToAdd.add(user);
		mockGroupService.addMembers(group, membersToAdd);
		EasyMock.expectLastCall();
		replay(mockGroupService);
		
		ModelAndView modelAndView = signupController.onSubmit(request,
				response, studentAccountForm, errors);

		assertEquals(SUCCESS, modelAndView.getViewName());
		verify(mockUserService);

		// test submission of form with same firstname, lastname and birthday info which
		// would result in a duplicate username
		reset(mockUserService);
		expect(mockUserService.createUser(studentUserDetails)).andThrow(
				new DuplicateUsernameException(studentUserDetails.getUsername()));
		replay(mockUserService);

		signupController.setFormView(FORM);
		modelAndView = signupController.onSubmit(request, response,
				studentAccountForm, errors);

		assertViewName(modelAndView, FORM);
		assertEquals(1, errors.getErrorCount());
		assertEquals(1, errors.getFieldErrorCount("userDetails.username"));
		verify(mockUserService);

		// test submission of form where RuntimeException is thrown.
		// should catch a RuntimeException
		reset(mockUserService);
		expect(mockUserService.createUser(studentUserDetails)).andThrow(
				new RuntimeException());
		replay(mockUserService);
		signupController.setFormView(FORM);
		try {
			signupController.onSubmit(request, response, studentAccountForm, errors);
			fail("Expected RuntimeException but it never happened.");
		} catch (RuntimeException expected) {
		}
		verify(mockUserService);
	}
	
	public void testOnSubmit_failure_bad_projectcode() throws Exception {
		// test submission of form with correct username and password info,
		// but with bad projectcode.
		// Should get ModelAndView back containing form view

		User user = new UserImpl();
		expect(mockUserService.createUser(studentUserDetails)).andReturn(user);
		replay(mockUserService);
		
		String runcode_not_in_db = "abc1234";
		studentAccountForm.setProjectCode(runcode_not_in_db + "-" + PERIODNAME);
		EasyMock.expect(mockRunService.retrieveRunByRuncode(runcode_not_in_db)).andThrow(new RunNotFoundException("Run Not Found"));
		replay(mockRunService);
		
		signupController.setFormView(FORM);
		ModelAndView modelAndView = signupController.onSubmit(request,
				response, studentAccountForm, errors);

		assertEquals(FORM, modelAndView.getViewName());
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getFieldErrorCount());
		
		assertNotNull(errors.getFieldError("projectCode"));
		verify(mockRunService);
		verify(mockUserService);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		request = null;
		response = null;
	}
}
