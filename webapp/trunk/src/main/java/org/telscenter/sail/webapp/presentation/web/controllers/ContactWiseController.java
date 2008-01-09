package org.telscenter.sail.webapp.presentation.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.mail.JavaMailHelper;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;
import org.telscenter.sail.webapp.domain.general.contactwise.ContactWISE;
import org.telscenter.sail.webapp.domain.general.contactwise.IssueType;
import org.telscenter.sail.webapp.domain.general.contactwise.OperatingSystem;
import org.telscenter.sail.webapp.domain.general.contactwise.WebBrowser;
import org.telscenter.sail.webapp.domain.general.contactwise.impl.ContactWISEGeneral;

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

public class ContactWiseController extends SimpleFormController {

	protected JavaMailHelper javaMail = null;
	
	public ContactWiseController() {
		setSessionForm(true);
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
	throws Exception {
		
		ContactWISEGeneral contactWISEGeneral = (ContactWISEGeneral) command;

		String[] recipients = contactWISEGeneral.getMailRecipients();
		String subject = contactWISEGeneral.getMailSubject();
		String message = contactWISEGeneral.getMailMessage();
		String fromEmail = contactWISEGeneral.getEmail();
		
		javaMail.postMail(recipients, subject, message, fromEmail);
		
		ModelAndView modelAndView = new ModelAndView(getSuccessView());

		return modelAndView;
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		ContactWISE contactWISE = new ContactWISEGeneral();
		User user = (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);

		if (user != null) {
			MutableUserDetails telsUserDetails = (MutableUserDetails) user.getUserDetails();
			contactWISE.setName(telsUserDetails.getFirstname() + " " + telsUserDetails.getLastname());
			contactWISE.setEmail(telsUserDetails.getEmailAddress());
		}
		return contactWISE;
	}
	
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("issuetypes", IssueType.values());
		model.put("operatingsystems", OperatingSystem.values());
		model.put("webbrowsers", WebBrowser.values());
		return model;
	}

	/**
	 * @return the javaMail
	 */
	public JavaMailHelper getJavaMail() {
		return javaMail;
	}

	/**
	 * @param javaMail the javaMail to set
	 */
	public void setJavaMail(JavaMailHelper javaMail) {
		this.javaMail = javaMail;
	}

}