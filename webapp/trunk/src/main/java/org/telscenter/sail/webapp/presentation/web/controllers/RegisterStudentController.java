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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.authentication.Gender;
import org.telscenter.sail.webapp.domain.authentication.impl.StudentUserDetails;
import org.telscenter.sail.webapp.presentation.web.StudentAccountForm;

import net.sf.sail.webapp.presentation.web.controllers.SignupController;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;

/**
 * Student Signup controller for the TELS Portal
 *
 * @author Hiroki Terashima
 *
 * @version $Id$
 *
 */
public class RegisterStudentController extends SignupController {

	public RegisterStudentController() {
		setValidateOnBinding(false);
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

		StudentAccountForm accountForm = (StudentAccountForm) command;
		StudentUserDetails userDetails = accountForm.getUserDetails();

		if (accountForm.isNewAccount()) {
			String firstname        = userDetails.getFirstname();
			String lastnameInitial  = userDetails.getLastname().substring(0, 1);

			Date defaultBirthday = new Date();  
			defaultBirthday.setMonth(6);              // TODO: use Calendar instead of java.util.Date
			defaultBirthday.setDate(19);
			userDetails.setBirthday(defaultBirthday); // TODO: don't hard-code this. get it from form
			
			Calendar birthday       = Calendar.getInstance();
			birthday.setTime(userDetails.getBirthday());
			int birthmonth          = birthday.get(Calendar.MONTH);
			int birthdate           = birthday.get(Calendar.DATE);
			int index               = 0;  // extra at end to ensure username uniqueness
			String[] suffixes       = {"", "a", "b", "c", "d", "e", "f", "g", "h",
					                  "i", "j", "k", "l", "m", "n", "o", "p"};

			for (;;) {   // loop until a unique username can be found
				userDetails.setUsername(
						firstname + lastnameInitial +
						birthmonth + birthdate + suffixes[index]);
				try {
					this.userService.createUser(userDetails);
				}
				catch (DuplicateUsernameException e) {
					index++;
					continue;
				}
				if (index >= suffixes.length) {
					// if suffixes is depleted, show user an error message
					errors.rejectValue("username", "error.unavailable-username",
							new Object[] { userDetails.getUsername() }, "Duplicate Username.");
					return showForm(request, response, errors);
				}
				break;
			}
		} else {
			//userService.updateUser(userDetails);    // TODO: add updateUser() to UserService
		}

		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		return new StudentAccountForm();
	}
	
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("genders", Gender.values());
		return model;
	}
	
	@Override
	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
	throws Exception {

		StudentAccountForm accountForm = (StudentAccountForm) command;
		StudentUserDetails userDetails = accountForm.getUserDetails();
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
	
	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception
	{
	  //super.initBinder(request, binder);
	  binder.registerCustomEditor(Date.class,
	    new CustomDateEditor(new SimpleDateFormat("MM/dd"), false)
	  );
	}

}
