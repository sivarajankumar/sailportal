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

import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;

import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.authentication.Gender;
import org.telscenter.sail.webapp.domain.authentication.impl.StudentUserDetails;
import org.telscenter.sail.webapp.presentation.web.StudentAccountForm;

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

	private static final String USERNAME1 = "HirokiT619";

	private static final String USERNAME2 = "HirokiT619a";

	private static final Gender GENDER = Gender.MALE;
	
	private Date birthday = null;
	
	ApplicationContext mockApplicationContext;

	MockHttpServletRequest request;

	HttpServletResponse response;

	BindException errors;
	
	StudentUserDetails studentUserDetails;
	
	StudentAccountForm studentAccountForm;

	UserService mockUserService;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		mockApplicationContext = createMock(ApplicationContext.class);
		studentUserDetails = new StudentUserDetails();
		studentAccountForm = new StudentAccountForm();
		errors = new BindException(studentUserDetails, "");
		mockUserService = createMock(UserService.class);
		Calendar cal = Calendar.getInstance();
		cal.set(1983, 6, 19);
		birthday = cal.getTime();
	}
	
	public void testOnSubmit() throws Exception {
		// test submission of form with correct username and password info.
		// should get ModelAndView back containing view which is instance of
		// RedirectView, with name of success view as URL.

		User user = new UserImpl();
		expect(mockUserService.createUser(studentUserDetails)).andReturn(user);
		replay(mockUserService);

		studentUserDetails.setFirstname(FIRSTNAME);
		studentUserDetails.setLastname(LASTNAME);
		studentUserDetails.setGender(GENDER);
		studentUserDetails.setBirthday(this.birthday);
		request.addParameter("firstname", FIRSTNAME);
		request.addParameter("lastname", LASTNAME);
		request.addParameter("password", PASSWORD);		

		studentAccountForm.setUserDetails(studentUserDetails);
		RegisterStudentController signupController = new RegisterStudentController();
		signupController.setApplicationContext(mockApplicationContext);
		signupController.setUserService(mockUserService);
		signupController.setSuccessView(SUCCESS);
		ModelAndView modelAndView = signupController.onSubmit(request,
				response, studentAccountForm, errors);

		assertTrue(modelAndView.getView() instanceof RedirectView);
		assertEquals(SUCCESS, ((RedirectView) modelAndView.getView()).getUrl());
		assertEquals(USERNAME1, studentUserDetails.getUsername());
		verify(mockUserService);

		// test submission of form with same firstname, lastname and birthday info which
		// would result in a new unique name being created. This username should have the same
		// username as before except with one additional "a" at the end. Should
		// get ModelAndView back containing view which is instance of
		// RedirectView, with name of success view as URL.
		reset(mockUserService);
		expect(mockUserService.createUser(studentUserDetails)).andThrow(
				new DuplicateUsernameException(studentUserDetails.getUsername()));
		expect(mockUserService.createUser(studentUserDetails)).andReturn(user);
		replay(mockUserService);

		modelAndView = signupController.onSubmit(request, response,
				studentAccountForm, errors);

		assertTrue(modelAndView.getView() instanceof RedirectView);
		assertEquals(SUCCESS, ((RedirectView) modelAndView.getView()).getUrl());
		assertEquals(USERNAME2, studentUserDetails.getUsername());
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
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		request = null;
		response = null;
	}
}
