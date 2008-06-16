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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.telscenter.sail.webapp.domain.authentication.Schoollevel;
import org.telscenter.sail.webapp.domain.authentication.Curriculumsubjects;
import org.telscenter.sail.webapp.domain.authentication.impl.TeacherUserDetails;
import org.telscenter.sail.webapp.presentation.web.TeacherAccountForm;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.presentation.web.controllers.SignupController;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;

/**
 * Signup controller for TELS teacher user
 *
 * @author Hiroki Terashima
 * @version $Id: RegisterTeacherController.java 1033 2007-09-08 00:05:01Z archana $
 */
public class RegisterTeacherController extends SignupController {

	protected static final String USERNAME_KEY = "username";
	
	public RegisterTeacherController() {
		setValidateOnBinding(false);
	}
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		return new TeacherAccountForm();
	}
	
	/**
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("schoollevels", Schoollevel.values());
		model.put("curriculumsubjects",Curriculumsubjects.values());
		return model;
	}
	
	/**
	 * On submission of the signup form, a user is created and saved to the data
	 * store.
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
	throws Exception {

		TeacherAccountForm accountForm = (TeacherAccountForm) command;
		TeacherUserDetails userDetails = (TeacherUserDetails) accountForm.getUserDetails();

		if (accountForm.isNewAccount()) {
			try {
				this.userService.createUser(userDetails);
			}
			catch (DuplicateUsernameException e) {
				errors.rejectValue("username", "error.duplicate-username",
						new Object[] { userDetails.getUsername() }, "Duplicate Username.");
				return showForm(request, response, errors);
			}
		} else {
			userService.updateUserDetails(userDetails); 
		}
		
		ModelAndView modelAndView = new ModelAndView(getSuccessView());

		modelAndView.addObject(USERNAME_KEY, userDetails.getUsername());
		return modelAndView;
	}
	
	/**
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBindAndValidate(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
	throws Exception {

		TeacherAccountForm accountForm = (TeacherAccountForm) command;
		
		if (!accountForm.isLegalAcknowledged()) {
			errors.reject("error.legal-not-acknowledged", "You must agree to the terms of use");
			return;
		}
		
		TeacherUserDetails userDetails = (TeacherUserDetails) accountForm.getUserDetails();
		if (accountForm.isNewAccount()) {
			userDetails.setSignupdate(Calendar.getInstance().getTime());
		}
		errors.setNestedPath("userDetails");
		getValidator().validate(userDetails, errors);
		errors.setNestedPath("");

		if (accountForm.isNewAccount()) {
			if (!errors.hasErrors() && (userDetails.getPassword() == null || userDetails.getPassword().length() < 1 ||
					!userDetails.getPassword().equals(accountForm.getRepeatedPassword()))) {
				errors.reject("error.passwords-mismatch",
				"Passwords did not match or were not provided. Matching passwords are required.");
			}
		}
		

	}
	

}
