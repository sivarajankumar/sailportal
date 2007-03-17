/**
 * TODO: replace with TELS PORTAL copyright information
 */
package org.telscenter.sail.webapp.presentation.web.controllers;

import static org.easymock.EasyMock.createMock;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Hiroki Terashima
 *
 * @version $Id: $
 */
public class RegisterStudentControllerTest extends AbstractModelAndViewTests {

	private static final String REDIRECT = "registerstudent";
	
	ApplicationContext mockApplicationContext;

	MockHttpServletRequest request;

	HttpServletResponse response;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		mockApplicationContext = createMock(ApplicationContext.class);
	}
	
	public void testHandleRequestInternal() throws Exception {
		// tests that the page redirects correctly
		
		RegisterStudentController registerStudentController = new RegisterStudentController();
		registerStudentController.setApplicationContext(mockApplicationContext);
		registerStudentController.setRedirectView(REDIRECT);

		ModelAndView modelAndView = registerStudentController.handleRequestInternal(request, response);
		assertTrue(modelAndView.getView() instanceof RedirectView);
		assertEquals(REDIRECT, ((RedirectView) modelAndView.getView()).getUrl());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		request = null;
		response = null;
	}
}
