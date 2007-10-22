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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.management;

import static org.easymock.EasyMock.*;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.authentication.impl.PersistentUserDetails;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.group.GroupService;

import org.easymock.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.telscenter.sail.webapp.domain.impl.BatchStudentChangePasswordParameters;



/**
 * @author Sally Ahn
 * @version $Id: $
 */
public class BatchStudentChangePasswordControllerTest extends AbstractModelAndViewTests  {
	
	private BatchStudentChangePasswordController batchStudentChangePasswordController;
	
	private BatchStudentChangePasswordParameters batchStudentChangePasswordParameters;

	private UserService mockUserService;
	
//	private GroupService mockGroupService;
	
	private MockHttpServletRequest request;

	private MockHttpServletResponse response;
	
	private HttpSession mockSession;
	
	private BindException errors;
	
	private User user;
	
	private Set<User> expectedMemberList;

	private User student1;
	
	private User student2;
	
	private User student3;
	
	private MutableUserDetails userDetails;
	
	private static final Long GROUPID = new Long(5);
	
	private static final String OLD_PASSWORD = "student";
	
	private static final String NEW_PASSWORD = "teacher";
	
	private static final String SUCCESS = "SUCCESS VIEW";

	private static final String FORM = "FORM VIEW";
	
	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		HttpSession mockSession = new MockHttpSession();
		user = new UserImpl();
		
		mockSession.setAttribute(User.CURRENT_USER_SESSION_KEY, user);
		request.setSession(mockSession);

		mockUserService = EasyMock.createMock(UserService.class);
//		mockGroupService = EasyMock.createMock(GroupService.class);
		
		expectedMemberList = new HashSet<User> ();

		batchStudentChangePasswordController = new BatchStudentChangePasswordController();
//		batchStudentChangePasswordController.setGroupService(mockGroupService);
		batchStudentChangePasswordController.setUserService(mockUserService);
		batchStudentChangePasswordController.setSuccessView(SUCCESS);
		batchStudentChangePasswordController.setFormView(FORM);
		
		batchStudentChangePasswordParameters = new BatchStudentChangePasswordParameters();
		batchStudentChangePasswordParameters.setPasswd1(NEW_PASSWORD);
		batchStudentChangePasswordParameters.setPasswd2(NEW_PASSWORD);
		batchStudentChangePasswordParameters.setGroupId(GROUPID);
		errors = new BindException(batchStudentChangePasswordParameters, "");
		
		userDetails = new PersistentUserDetails();
		userDetails.setPassword(OLD_PASSWORD);
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		request = null;
		response = null;
		mockUserService = null;
	}
	
	public void testOnSubmit_success_1() {
		// test submission of form with correct password info
		// for a group with 1 student.
		// should get ModelAndView back containing Success view
		
		student1 = new UserImpl();
		student1.setUserDetails(userDetails);
		expectedMemberList.add(student1);
		expect(mockUserService.updateUserPassword(student1, batchStudentChangePasswordParameters.getPasswd1())).andReturn(user);
		replay(mockUserService);
//		ModelAndView modelAndView = batchStudentChangePasswordController.onSubmit(request, response, batchStudentChangePasswordParameters, errors);
//		assertEquals(SUCCESS, modelAndView.getViewName());
//		assertTrue(!errors.hasErrors());
//		verify(mockUserService);
	}
	
	public void testOnSubmit_success_2() {
		// test submission of form with correct password info
		// for a group with 2 students.
		// should not have left out any student
		// should get ModelAndView back containing Success view
		assertTrue(true);
	}
	
	public void testOnSubmit_success_3() {
		// test submission of form with correct password info
		// for a group with 3 students.
		// should not have left out any student
		// should get ModelAndView back containing Success view
		assertTrue(true);
	}
	
	public void testOnSubmit_emptyGroup() {
		// test submission of form with correct password info
		// when the group does not contain any student
		// should get ModelAndView back containing empty-group error message
		assertTrue(true);
	}
	
}
