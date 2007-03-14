/**
 * TODO: Copyright
 */
package org.telscenter.sail.webapp.presentation.web.controllers;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

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
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;
import org.telscenter.sail.webapp.domain.authentication.impl.StudentUserDetails;

/**
 * Test for TELS Portal signup process
 * 
 * @author Hiroki Terashima
 * 
 * @version $Id: $
 */
public class SignupControllerTest extends AbstractModelAndViewTests {

	private static final String USERNAME = "User";
	
	private static final String PASSWORD = "Pass";
	
	private static final String SUCCESS = "Wooppie!";
	
	private static final String FORM = "Form";
	
	ApplicationContext mockApplicationContext;
	
	MockHttpServletRequest request;
	
	HttpServletResponse response;
	
	MutableUserDetails userDetails;
	
	BindException errors;
	
	UserService mockUserService;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		userDetails = new StudentUserDetails();
		errors = new BindException(userDetails, "");
		mockUserService = createMock(UserService.class);
		mockApplicationContext = createMock(ApplicationContext.class);
	}
	
	public void testOnSubmit() throws Exception {
		// test submission of form with correct username and password info.
		// should get ModelAndView back containing view which is instance of
		// RedirectView, with name of success view as URL.
		
		User user = new UserImpl();
		expect(mockUserService.createUser(userDetails)).andReturn(user);
		replay(mockUserService);
		
		userDetails.setUsername(USERNAME);
		request.addParameter("username", USERNAME);
		request.addParameter("password", PASSWORD);		
		SignupController signupController = new SignupController();  
		signupController.setApplicationContext(mockApplicationContext);
		signupController.setUserService(mockUserService);
		signupController.setSuccessView(SUCCESS);
		ModelAndView modelAndView = signupController.onSubmit(request,
				response, userDetails, errors);
				
		assertTrue(modelAndView.getView() instanceof RedirectView);
		assertEquals(SUCCESS, ((RedirectView) modelAndView.getView()).getUrl());
		verify(mockUserService);
		
		// test submission of form with same username and password info which
		// would result in DuplicateUsernameException being thrown. Should get
		// back ModelAndView with original form returned with name of Form View
		// as set.
		reset(mockUserService);
		expect(mockUserService.createUser(userDetails)).andThrow(
				new DuplicateUsernameException(userDetails.getUsername()));
		replay(mockUserService);
		
		signupController.setFormView(FORM);
		modelAndView = signupController.onSubmit(request, response,
				userDetails, errors);
		
		assertViewName(modelAndView, FORM);
		assertEquals(1, errors.getErrorCount());
		assertEquals(1, errors.getFieldErrorCount("username"));
		verify(mockUserService);
		
		// test submission of form where RuntimeException is thrown.
		// should catch a RuntimeException
		reset(mockUserService);
		expect(mockUserService.createUser(userDetails)).andThrow(
				new RuntimeException());
		replay(mockUserService);
		signupController.setFormView(FORM);
		try {
		    signupController.onSubmit(request, response, userDetails, errors);  
			fail("Expected RuntimeException but it never happened");
		}
		catch (RuntimeException expected) {
		}
		verify(mockUserService);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		request = null;
		response = null;
		userDetails = null;
		errors = null;
		mockUserService = null;
	}
	
}
