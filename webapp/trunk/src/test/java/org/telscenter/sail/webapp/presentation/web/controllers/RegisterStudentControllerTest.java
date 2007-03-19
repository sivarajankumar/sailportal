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
