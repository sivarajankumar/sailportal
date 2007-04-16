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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.authentication.impl.TeacherUserDetails;
import org.telscenter.sail.webapp.presentation.web.TeacherAccountForm;

import net.sf.sail.webapp.presentation.web.controllers.SignupController;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;

/**
 * Signup controller for TELS teacher user
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class RegisterTeacherController extends SignupController {

	public RegisterTeacherController() {
		setValidateOnBinding(false);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		return new TeacherAccountForm();
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
			//userService.updateUser(userDetails);    // TODO: add updateUser() to UserService
		}

		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	@Override
	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
	throws Exception {

		TeacherAccountForm accountForm = (TeacherAccountForm) command;
		TeacherUserDetails userDetails = (TeacherUserDetails) accountForm.getUserDetails();
		errors.setNestedPath("userDetails");
		getValidator().validate(userDetails, errors);
		errors.setNestedPath("");

		if (accountForm.isNewAccount()) {
			if (userDetails.getPassword() == null || userDetails.getPassword().length() < 1 ||
					!userDetails.getPassword().equals(accountForm.getRepeatedPassword())) {
				errors.reject("error.passwords-mismatch",
				"Passwords did not match or were not provided. Matching passwords are required.");
			}
		}
	}
}
