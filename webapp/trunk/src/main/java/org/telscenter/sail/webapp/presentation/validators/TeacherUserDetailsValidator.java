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
package org.telscenter.sail.webapp.presentation.validators;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.telscenter.sail.webapp.domain.authentication.impl.TeacherUserDetails;

import net.sf.sail.webapp.presentation.validators.UserDetailsValidator;

/**
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class TeacherUserDetailsValidator extends UserDetailsValidator {

	public static final int MAX_PASSWORD_LENGTH = 20;

	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return TeacherUserDetails.class.isAssignableFrom(clazz);
	}
	
	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object userDetailsIn, Errors errors) {
		TeacherUserDetails userDetails = (TeacherUserDetails) userDetailsIn;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
		"error.password-not-specified");
		
		if (errors.getFieldErrorCount("password") > 0) {
			return;
		}

		if (userDetails.getPassword().length() > MAX_PASSWORD_LENGTH) {
			errors.rejectValue("password", "error.password-too-long");
			return;
		}

		if (!StringUtils.isAlphanumeric(userDetails.getPassword())) {
			errors.rejectValue("password", "error.password-illegal-characters");
			return;
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", 
				"error.firstname-not-specified");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", 
				"error.lastname-not-specified");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", 
				"error.email-not-specified");
		
		// TODO: CHECK FOR ILLEGAL EMAIL ADDRESS FORMAT
		
		if (errors.hasErrors())
			userDetails.setPassword("");
	}
}
