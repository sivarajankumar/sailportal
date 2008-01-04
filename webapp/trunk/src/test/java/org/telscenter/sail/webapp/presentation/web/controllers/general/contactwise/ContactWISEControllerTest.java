/**
 * Copyright (c) 2008 Regents of the University of California (Regents). Created
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
package org.telscenter.sail.webapp.presentation.web.controllers.general.contactwise;

import static org.easymock.EasyMock.createMock;

import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.mail.JavaMailHelper;
import net.sf.sail.webapp.service.AclService;
import net.sf.sail.webapp.service.UserService;

import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.general.contactwise.IssueType;
import org.telscenter.sail.webapp.domain.general.contactwise.OperatingSystem;
import org.telscenter.sail.webapp.domain.general.contactwise.WebBrowser;
import org.telscenter.sail.webapp.domain.general.contactwise.impl.ContactWISEGeneral;
import org.telscenter.sail.webapp.presentation.web.controllers.ContactWiseController;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.student.StudentService;

/**
 * @author Hiroki Terashima
 * @author Geoffrey Kwan
 *
 * @version $Id$
 */
public class ContactWISEControllerTest extends AbstractModelAndViewTests {

	private static final String NAME = "";
	
	private static final String EMAIL = "";
	
	private static final IssueType ISSUETYPE = IssueType.BROKEN_LINK;
	
	private static final OperatingSystem OPERATINGSYSTEM = OperatingSystem.MAC_OSX_LEOPARD;
	
	private static final WebBrowser WEBBROWSER = WebBrowser.FIREFOX;
	
	private static final String DESCRIPTION = "";
	
	private static final String SUMMARY = "";
	
	
	private static final String SUCCESS = "WooHoo";

	private static final String FORM = "Form";

	private ApplicationContext mockApplicationContext;

	private MockHttpServletRequest request;

	private HttpServletResponse response;

	private BindException errors;
	
	private UserService mockUserService;
	
	private StudentService mockStudentService;
	
	private RunService mockRunService;
	
	private AclService<Run> mockAclService;
	
	//private RegisterStudentController signupController;
	
	private ContactWiseController contactController; 
	
	private ContactWISEGeneral contactDetails;
	
	private JavaMailHelper javaMail;
	
	private Properties mailProperties;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		mockApplicationContext = createMock(ApplicationContext.class);
		
		
		//studentUserDetails = new StudentUserDetails();
		//studentAccountForm = new StudentAccountForm();
		contactDetails = new ContactWISEGeneral();
		
		errors = new BindException(contactDetails, "");
		javaMail = new JavaMailHelper();
		
		mailProperties = new Properties();
		javaMail.setProperties(mailProperties);
		
		mockUserService = createMock(UserService.class);
		mockRunService = createMock(RunService.class);
		mockAclService = createMock(AclService.class);
		mockStudentService = createMock(StudentService.class);
		
		/*
		studentUserDetails.setFirstname(FIRSTNAME);
		studentUserDetails.setLastname(LASTNAME);
		studentUserDetails.setGender(GENDER);
		studentUserDetails.setBirthday(this.birthday);
		*/
		contactDetails.setName(NAME);
		contactDetails.setEmail(EMAIL);
		contactDetails.setIssuetype(ISSUETYPE);
		contactDetails.setOperatingsystem(OPERATINGSYSTEM);
		contactDetails.setWebbrowser(WEBBROWSER);
		contactDetails.setSummary(SUMMARY);
		contactDetails.setDescription(DESCRIPTION);
		
		/*
		request.addParameter("firstname", FIRSTNAME);
		request.addParameter("lastname", LASTNAME);
		request.addParameter("password", PASSWORD);				
		studentAccountForm.setUserDetails(studentUserDetails);
		studentAccountForm.setProjectCode(LEGAL_PROJECTCODE);
		*/
		
		contactController = new ContactWiseController();
		contactController.setApplicationContext(mockApplicationContext);
		contactController.setJavaMail(javaMail);
		
		/*
		contactController.setUserService(mockUserService);
		contactController.setRunService(mockRunService);
		contactController.setStudentService(mockStudentService);
		*/
		
		contactController.setSuccessView(SUCCESS);
		contactController.setFormView(FORM);
	}
	
	public void testOnSubmit_success() throws Exception {
		
		ModelAndView modelAndView = contactController.onSubmit(request,
				response, contactDetails, errors);
	}
}
