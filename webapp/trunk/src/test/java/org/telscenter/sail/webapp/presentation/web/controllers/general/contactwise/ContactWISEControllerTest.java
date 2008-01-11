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

import static org.easymock.EasyMock.*;

import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.mail.IMailFacade;
import net.sf.sail.webapp.mail.JavaMailHelper;
import net.sf.sail.webapp.mail.JavaMailTest;
import net.sf.sail.webapp.service.AclService;
import net.sf.sail.webapp.service.UserService;

import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.general.contactwise.ContactWISE;
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

	private static final String NAME = "Spongebob";
	
	private static final String EMAIL = "spongebob@bikinibottom.com";
	
	private static final IssueType ISSUETYPE = IssueType.TROUBLE_LOGGING_IN;
	
	private static final OperatingSystem OPERATINGSYSTEM = OperatingSystem.MAC_OSX_LEOPARD;
	
	private static final WebBrowser WEBBROWSER = WebBrowser.FIREFOX;
	
	private static final String SUMMARY = "Blerg";
	
	private static final String DESCRIPTION = "Where is my spatula?";
	
	private static final String [] RECIPIENTS = {"geoffreykwan@gmail.com"};
	
	private static final String SUCCESS = "WooHoo";

	private static final String FORM = "Form";

	private MockHttpServletRequest request;

	private HttpServletResponse response;

	private BindException errors;
	
	private ContactWiseController contactController; 
	
	private ContactWISE contactDetails;
	
	private IMailFacade mockMail;
	
	private Properties emailListeners;
	
	private Properties uiHTMLProperties;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		
		contactDetails = new ContactWISEGeneral();
		
		errors = new BindException(contactDetails, "");
		mockMail = createMock(IMailFacade.class);
		
		emailListeners = new Properties();
		emailListeners.setProperty("trouble_logging_in", "geoffreykwan@gmail.com");
		emailListeners.setProperty("need_help_using_wise", "geoffreykwan@gmail.com");
		emailListeners.setProperty("project_problems", "geoffreykwan@gmail.com");
		emailListeners.setProperty("student_management", "geoffreykwan@gmail.com");
		emailListeners.setProperty("authoring", "geoffreykwan@gmail.com");
		emailListeners.setProperty("feedback", "geoffreykwan@gmail.com");
		emailListeners.setProperty("other", "geoffreykwan@gmail.com");

		uiHTMLProperties = new Properties();
		uiHTMLProperties.setProperty("issuetypes.TROUBLE_LOGGING_IN", "Trouble Signing In");
		uiHTMLProperties.setProperty("issuetypes.NEED_HELP_USING_WISE", "Need Help Using WISE");
		uiHTMLProperties.setProperty("issuetypes.PROJECT_PROBLEMS", "Problems with a Project");
		uiHTMLProperties.setProperty("issuetypes.STUDENT_MANAGEMENT", "Student Management");
		uiHTMLProperties.setProperty("issuetypes.AUTHORING", "Need Help with Authoring");
		uiHTMLProperties.setProperty("issuetypes.FEEDBACK", "Feedback to WISE");
		uiHTMLProperties.setProperty("issuetypes.OTHER", "Other Problem");
		uiHTMLProperties.setProperty("operatingsystems.MAC_OS9", "Mac OS 9");
		uiHTMLProperties.setProperty("operatingsystems.MAC_OSX_TIGER", "Mac OS X Tiger");
		uiHTMLProperties.setProperty("operatingsystems.MAC_OSX_LEOPARD", "Mac OS X Leopard");
		uiHTMLProperties.setProperty("operatingsystems.WINDOWS_VISTA", "Windows Vista");
		uiHTMLProperties.setProperty("operatingsystems.WINDOWS_XP_NT_2K", "Windows XP/NT/2000");
		uiHTMLProperties.setProperty("operatingsystems.LINUX", "Linux");
		uiHTMLProperties.setProperty("operatingsystems.OTHER", "Other or Not Sure");
		uiHTMLProperties.setProperty("webbrowsers.FIREFOX", "Firefox");
		uiHTMLProperties.setProperty("webbrowsers.IE", "Internet Explorer");
		uiHTMLProperties.setProperty("webbrowsers.SAFARI", "Safari");
		uiHTMLProperties.setProperty("webbrowsers.OPERA", "Opera");
		uiHTMLProperties.setProperty("webbrowsers.NETSCAPE", "Netscape");
		uiHTMLProperties.setProperty("webbrowsers.OTHER", "Other");
		
		contactDetails.setName(NAME);
		contactDetails.setEmail(EMAIL);
		contactDetails.setIssuetype(ISSUETYPE);
		contactDetails.setOperatingsystem(OPERATINGSYSTEM);
		contactDetails.setWebbrowser(WEBBROWSER);
		contactDetails.setSummary(SUMMARY);
		contactDetails.setDescription(DESCRIPTION);
		contactDetails.setEmaillisteners(emailListeners);
		
		contactController = new ContactWiseController();
		contactController.setJavaMail(mockMail);
		contactController.setUiHTMLProperties(uiHTMLProperties);
		contactController.setSuccessView(SUCCESS);
		contactController.setFormView(FORM);
	}
	
	public void testOnSubmit_success() throws Exception {
		String[] recipients = RECIPIENTS; 
		String subject = "[Contact WISE] " + ISSUETYPE + ": " + SUMMARY;
		String message = "Contact WISE Request\n" +
		 "=================\n" + 
		 "Name: " + NAME + "\n" + 
		 "Email: " + EMAIL + "\n" + 
		 "Issue Type: " + ISSUETYPE + "\n" +
		 "Operating System: " + OPERATINGSYSTEM + "\n" + 
		 "Web Browser: " + WEBBROWSER + "\n" +
		 "Summary: " + SUMMARY + "\n" + 
		 "Description: " + DESCRIPTION + "\n";
		String from = EMAIL;

		mockMail.postMail(aryEq(recipients), eq(subject), eq(message), eq(from));
		replay(mockMail);
		ModelAndView modelAndView = contactController.onSubmit(request,
				response, contactDetails, errors);
		assertEquals(SUCCESS, modelAndView.getViewName());
		verify(mockMail);
	}
}
